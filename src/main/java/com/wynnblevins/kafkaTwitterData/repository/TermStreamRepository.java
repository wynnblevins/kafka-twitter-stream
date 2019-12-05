package com.wynnblevins.kafkaTwitterData.repository;

import com.wynnblevins.kafkaTwitterData.model.TermStream;
import org.springframework.data.repository.CrudRepository;

public interface TermStreamRepository extends CrudRepository<TermStream, String> {

}
