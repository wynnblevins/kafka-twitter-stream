package com.wynnblevins.kafkaTwitterData.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wynnblevins.kafkaTwitterData.model.Term;
import com.wynnblevins.kafkaTwitterData.producer.TwitterProducer;
import com.wynnblevins.kafkaTwitterData.service.TermsCacheService;

@RestController
public class TweetTermsController {
	@Autowired
	private TermsCacheService termsCache;
	
	@Autowired
	private TwitterProducer twitterProducer;
	
	public TermsCacheService getTermsCache() {
		return termsCache;
	}

	public void setTermsCache(TermsCacheService termsCache) {
		this.termsCache = termsCache;
	}
	
	@GetMapping("/tweets") 
	public void startTweetsFeed() {
		twitterProducer.stop();
		
		Set<Term> terms = this.termsCache.getTerms();
		
		// begins putting tweet data into kafka topic
		twitterProducer.run(terms);
	}
	
	@GetMapping("/terms")
	public Set<Term> getTerms() {
		return this.termsCache.getTerms();
	}
	
	@PostMapping("/terms")
	public void addTerms(@RequestBody Set<Term> termSet) {
		boolean restartNeeded = this.twitterProducer.isRunning();
		
		if (this.twitterProducer.isRunning()) {
			this.twitterProducer.stop();
		}
		
		for (Term term : termSet) {
			this.termsCache.addTerm(term);
		}
		
		if (restartNeeded) {
			this.twitterProducer.run(termSet);
		}
	}
	
	@DeleteMapping("/terms/{id}")
	public void deleteTerm(@PathVariable String id) {
		boolean restartNeeded = this.twitterProducer.isRunning();
		
		if (this.twitterProducer.isRunning()) {
			this.twitterProducer.stop();
		}
		
		Set<Term> termSet = this.termsCache.removeTermById(id);
		
		if (restartNeeded) {
			this.twitterProducer.run(termSet);
		}
	} 
	
	@DeleteMapping("/terms")
	public void emptyTermsSet() {
		if (this.twitterProducer.isRunning()) {
			this.twitterProducer.stop();
		}
		
		this.termsCache.empty();
	}
}
