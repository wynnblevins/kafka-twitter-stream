package com.wynnblevins.kafkaTwitterData.controllers;

import com.wynnblevins.kafkaTwitterData.exception.NotFoundException;
import com.wynnblevins.kafkaTwitterData.model.Stock;
import com.wynnblevins.kafkaTwitterData.service.StockService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(path = "/stocks/{id}")
    public Stock getById(@RequestParam("id") String id) throws NotFoundException {
        return stockService.getById(id);
    }
}
