package com.wynnblevins.kafkaTwitterData.repository;

import com.wynnblevins.kafkaTwitterData.model.TweetStream;
import org.springframework.data.repository.CrudRepository;

public interface TweetStreamRepository extends CrudRepository<TweetStream, String> {

}
