package com.wynnblevins.kafkaTwitterData.service;

import com.wynnblevins.kafkaTwitterData.exception.NotFoundException;
import com.wynnblevins.kafkaTwitterData.repository.TweetDataStreamRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetDataStreamService {
    private TweetDataStreamRepository tweetDataStreamRepository;

    @Autowired
    public TweetDataStreamService(TweetDataStreamRepository tweetDataStreamRepository) {
        this.tweetDataStreamRepository = tweetDataStreamRepository;
    }

    public Set<TweetDataStream> getAll() {
        Iterable<TweetDataStream> tweetDataStreamIterable = tweetDataStreamRepository.findAll();
        return StreamSupport.stream(tweetDataStreamIterable.spliterator(), false)
            .collect(Collectors.toSet());
    }

    public TweetDataStream getById(String tweetDataStreamId) throws NotFoundException {
        Optional<TweetDataStream> maybeDataStream = tweetDataStreamRepository.findById(tweetDataStreamId);
        if (maybeDataStream.isPresent()) {
            maybeDataStream.get();
        }

        throw new NotFoundException(String.format("Tweet data stream with ID %s not found",
            tweetDataStreamId));
    }

    public List<TweetDataStream> getAllRunningStreams() {
        return tweetDataStreamRepository.findAllByRunning();
    }
}
