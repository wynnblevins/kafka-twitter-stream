package com.wynnblevins.kafkaTwitterData.controllers;

import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wynnblevins.kafkaTwitterData.model.Term;
import com.wynnblevins.kafkaTwitterData.service.TermsService;

@RestController
public class TweetTermsController {
	private final TermsService termsService;   
	
	public TweetTermsController(final TermsService termsService) {
		this.termsService = termsService;
	}
	
	@RequestMapping(method = RequestMethod.GET, 
			path = "/stream/{streamId}/terms")
	public Set<Term> getAllStreamTerms(@PathVariable("streamId") String streamId) {
		return this.termsService.getTerms();
	}
	
	@RequestMapping(method = RequestMethod.GET, 
			path = "/terms/{termId}")
	public Term getTermById(@PathVariable("termId") String termId) {
		return this.termsService.getTermById(termId);
	}
	
	@RequestMapping(method = RequestMethod.POST, 
			path = "/streams/{streamId}/terms")
	public Term addTerm(@PathVariable String streamId, 
			@RequestBody Term term) {
		return this.termsService.addStreamTerm(streamId, term);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, 
			path = "/terms/{termId}")
	public void deleteTermByID(@PathVariable("streamId") String streamId,
			@PathVariable("termId") String termId) {
		this.termsService.deleteTermById(termId);
	} 
	
	@RequestMapping(method = RequestMethod.DELETE, 
			path = "/stream/{streamId}/terms")
	public void emptyTermsSet(@PathVariable("streamId") String streamId) {
		this.termsService.emptyTermsForStream(streamId);
	}
	
	@RequestMapping(method = RequestMethod.PUT, 
			path = "/terms/{termId}")
	public Term updateStreamTerm(@PathVariable("streamId") String streamId,
			@PathVariable("termId") String termId,
			@RequestBody Term term) {
		return this.termsService.updateStreamTerm(streamId, term);
	}
}
