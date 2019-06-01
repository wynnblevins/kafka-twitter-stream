package com.wynnblevins.kafkaTwitterData.model;

import java.util.Date;

public class Tweet extends IDEntity {
	private Date date;
	private String text;
	
	public Tweet() {}
	
	public Tweet(String text) {
		this.date = null;
		this.text = text;
	}
	
	public Tweet(String text, Date date) {
		this.date = date;
		this.text = text;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
