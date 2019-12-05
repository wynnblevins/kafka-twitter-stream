package com.wynnblevins.kafkaTwitterData.controllers;

import com.wynnblevins.kafkaTwitterData.model.TweetStream;
import com.wynnblevins.kafkaTwitterData.service.TweetStreamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetStreamController {
    private TweetStreamService tweetStreamService;

    @Autowired
    public TweetStreamController(TweetStreamService tweetStreamService) {
        this.tweetStreamService = tweetStreamService;
    }

    @RequestMapping(path = "/tweetStreams")
    public List<TweetStream> getAll() {
        return tweetStreamService.getAll();
    }
}
