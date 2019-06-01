package com.wynnblevins.kafkaTwitterData.model;

import java.util.UUID;

public abstract class IDEntity {
	private String id;
	
	public IDEntity() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
