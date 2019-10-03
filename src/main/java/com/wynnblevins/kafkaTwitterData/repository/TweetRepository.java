package com.wynnblevins.kafkaTwitterData.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.wynnblevins.kafkaTwitterData.model.Tweet;

public interface TweetRepository extends CrudRepository<Tweet, Serializable> {

}
