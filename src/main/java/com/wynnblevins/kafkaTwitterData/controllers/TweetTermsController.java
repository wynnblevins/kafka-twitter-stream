package com.wynnblevins.kafkaTwitterData.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		// begins putting tweet data into kafka topic
		twitterProducer.run(this.termsCache.getTerms());
	}
	
	@GetMapping("/terms")
	public Set<Term> getTerms() {
		return this.termsCache.getTerms();
	}
	
	@PostMapping("/terms")
	public void addTerms(@RequestBody Set<Term> termSet) {
		for (Term term : termSet) {
			this.termsCache.addTerm(term);
		}
	}
	
	@DeleteMapping("/terms/{id}")
	public void deleteTerm(@PathVariable String id) {
		this.termsCache.removeTermById(id);
	} 
}
