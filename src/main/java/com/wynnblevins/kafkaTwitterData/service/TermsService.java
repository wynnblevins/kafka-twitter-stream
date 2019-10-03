package com.wynnblevins.kafkaTwitterData.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wynnblevins.kafkaTwitterData.model.Term;
import com.wynnblevins.kafkaTwitterData.repository.TermRepository;

@Service
public class TermsService {
	@Autowired
	private TermRepository termRepository;
	
	public Set<Term> getTerms() {
		return (Set<Term>) termRepository.findAll();
	}
	
	public Set<Term> getStreamTerms(String streamId) {
		return null;
	}
	
	public Term getTermById(String termId) {
		return termRepository.findOne(termId);
	}
	
	public Term addStreamTerm(String streamId, Term term) {
		return null;
	}
	
	public Term updateStreamTerm(String streamId, Term term) {
		return null;
	}
	
	public void deleteTermById(String termId) {
		termRepository.delete(termId);
	}
	
	public void emptyTermsForStream(String streamId) {
		
	}
	
	public void emptyAllTermsForAllStreams() {
		termRepository.deleteAll();
	}
}
