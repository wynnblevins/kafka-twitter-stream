package com.wynnblevins.kafkaTwitterData.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wynnblevins.kafkaTwitterData.model.Term;

@Service
public class TermsCacheService {
	private Set<Term> terms = new HashSet<Term>();

	public void addTerm(Term term) {
		this.terms.add(term);
	}
	
	public Set<Term> removeTermById(String id) {
		this.terms = this.terms.stream()
			.filter(term -> !term.getId().equals(id))
			.collect(Collectors.toSet());
				
		return this.terms;
	}
	
	public Set<Term> getTerms() {
		return terms;
	}

	public void setTerms(Set<Term> terms) {
		this.terms = terms;
	}
}
