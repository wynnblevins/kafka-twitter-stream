package com.wynnblevins.kafkaTwitterData.service;

import com.wynnblevins.kafkaTwitterData.exception.NotFoundException;
import com.wynnblevins.kafkaTwitterData.model.Stock;
import com.wynnblevins.kafkaTwitterData.repository.StockRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock getById(String id) throws NotFoundException {
        Optional<Stock> maybeStock = stockRepository.findById(id);

        if (maybeStock.isPresent()) {
            return maybeStock.get();
        }

        throw new NotFoundException(String.format("Stock with ID %s not found", id));
    }

    public List<Stock> getAll() {
        Iterable<Stock> iterableStocks = stockRepository.findAll();
        return StreamSupport.stream(iterableStocks.spliterator(), false)
            .collect(Collectors.toList());
    }
}
