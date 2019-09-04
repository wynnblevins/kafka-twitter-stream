package com.wynnblevins.kafkaTwitterData.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class TwitterConfig {
	@Value("${consumerKey}")
	private String consumerKey;
	
	@Value("${consumerKeySecret}")
	private String consumerKeySecret;
	
	@Value("${accessToken}")
	private String accessToken;
	
	@Value("${accessTokenSecret}")
	private String accessTokenSecret;
	
	public String getConsumerKey() {
		return consumerKey;
	}
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
	public String getConsumerKeySecret() {
		return consumerKeySecret;
	}
	public void setConsumerKeySecret(String consumerKeySecret) {
		this.consumerKeySecret = consumerKeySecret;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}
	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}
}
