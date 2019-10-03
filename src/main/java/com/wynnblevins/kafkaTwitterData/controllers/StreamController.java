package com.wynnblevins.kafkaTwitterData.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wynnblevins.kafkaTwitterData.model.Stream;
import com.wynnblevins.kafkaTwitterData.service.StreamService;

@RestController
public class StreamController {
	private StreamService tweetStreamService;
	
	public StreamController(final StreamService tweetStreamService) {
		this.tweetStreamService = tweetStreamService;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/stream/{streamId}") 
	public Stream updateStream(@PathVariable("streamId") String streamId, 
			@RequestBody Stream tweetStream) {
		return tweetStreamService.updateStream(streamId, tweetStream);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/stream/{streamId}")
	public Stream getStream(@PathVariable("id") String streamId) {
		return tweetStreamService.getStreamById(streamId);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/stream")
	public Stream createStream(@RequestBody Stream tweetStream) {
		return tweetStreamService.createStream(tweetStream);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/stream/{streamId}") 
	public void deleteStream(@PathVariable("streamId") String streamId) {
		tweetStreamService.deleteStream(streamId);;
	}
}
