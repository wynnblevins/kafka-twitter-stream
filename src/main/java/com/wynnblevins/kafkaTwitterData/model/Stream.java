package com.wynnblevins.kafkaTwitterData.model;

import java.util.Set;

public class Stream {
	private Set<Term> terms;
	private boolean isRunning;
	
	public Set<Term> getTerms() {
		return terms;
	}
	
	public void setTerms(Set<Term> terms) {
		this.terms = terms;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}
