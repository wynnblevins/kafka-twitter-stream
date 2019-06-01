package com.wynnblevins.kafkaTwitterData.model;

public class Term extends IDEntity {
	private String value;
	
	public Term() {}
	
	public Term(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
