package com.wynnblevins.kafkaTwitterData.repository;

import com.wynnblevins.kafkaTwitterData.model.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, String> {

}
