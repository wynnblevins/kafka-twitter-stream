package com.wynnblevins.kafkaTwitterData.service;


import com.wynnblevins.kafkaTwitterData.model.Stock;
import com.wynnblevins.kafkaTwitterData.model.Term;
import com.wynnblevins.kafkaTwitterData.model.TermStream;
import com.wynnblevins.kafkaTwitterData.repository.TermStreamRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermStreamService {
    private TermStreamRepository termStreamRepository;

    @Autowired
    public TermStreamService(TermStreamRepository termStreamRepository) {
        this.termStreamRepository = termStreamRepository;
    }

    public List<TermStream> getAll() {
        Iterable<TermStream> termStreamsIterable = termStreamRepository.findAll();
        return StreamSupport.stream(termStreamsIterable.spliterator(), false)
            .collect(Collectors.toList());
    }

    public TermStream create(TermStream termStream) {
        return termStreamRepository.save(termStream);
    }

    public TermStream create(Term term, Stock stock, TweetDataStream tweetDataStream) {
        TermStream newTermStream = new TermStream(term, stock, tweetDataStream);
        return termStreamRepository.save(newTermStream);
    }


}
