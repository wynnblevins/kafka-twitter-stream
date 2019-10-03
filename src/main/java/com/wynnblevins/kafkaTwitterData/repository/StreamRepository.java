package com.wynnblevins.kafkaTwitterData.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wynnblevins.kafkaTwitterData.model.Stream;

@Repository
public interface StreamRepository extends CrudRepository<Stream, Serializable>{

}
