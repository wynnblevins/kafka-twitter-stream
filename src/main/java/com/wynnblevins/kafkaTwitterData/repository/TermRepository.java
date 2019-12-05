package com.wynnblevins.kafkaTwitterData.repository;

import com.wynnblevins.kafkaTwitterData.model.Term;
import org.springframework.data.repository.CrudRepository;

public interface TermRepository extends CrudRepository<Term, String> {

}
