package com.wynnblevins.kafkaTwitterData.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TweetDataStreamRepository extends CrudRepository<TweetDataStream, String> {
    List<TweetDataStream> findAllByRunning();
}
