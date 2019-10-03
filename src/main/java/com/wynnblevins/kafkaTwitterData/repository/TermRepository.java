package com.wynnblevins.kafkaTwitterData.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.wynnblevins.kafkaTwitterData.model.Term;

public interface TermRepository extends CrudRepository<Term, Serializable> {
	
}
