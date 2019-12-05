package com.wynnblevins.kafkaTwitterData.service;

import com.wynnblevins.kafkaTwitterData.exception.NotFoundException;
import com.wynnblevins.kafkaTwitterData.model.Stock;
import com.wynnblevins.kafkaTwitterData.model.Term;
import com.wynnblevins.kafkaTwitterData.model.TweetStream;
import com.wynnblevins.kafkaTwitterData.repository.TweetStreamRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetStreamService {
    private TweetStreamRepository tweetStreamRepository;

    @Autowired
    public TweetStreamService(TweetStreamRepository tweetStreamRepository) {
        this.tweetStreamRepository = tweetStreamRepository;
    }

    public List<TweetStream> getAll() {
        Iterable<TweetStream> termStreamsIterable = tweetStreamRepository.findAll();
        return StreamSupport.stream(termStreamsIterable.spliterator(), false)
            .collect(Collectors.toList());
    }

    public TweetStream getById(String id) throws NotFoundException {
        Optional<TweetStream> maybeTweetStream = tweetStreamRepository.findById(id);
        if (maybeTweetStream.isPresent()) {
            return maybeTweetStream.get();
        }
        throw new NotFoundException(String.format("Tweet stream with ID %s not found", id));
    }

    public TweetStream create(TweetStream tweetStream) {
        return tweetStreamRepository.save(tweetStream);
    }

    public TweetStream create(Term term, Stock stock) {
        TweetStream newTweetStream = new TweetStream(term, stock);
        return tweetStreamRepository.save(newTweetStream);
    }
}
