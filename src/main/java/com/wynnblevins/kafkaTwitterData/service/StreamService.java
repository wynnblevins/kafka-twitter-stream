package com.wynnblevins.kafkaTwitterData.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wynnblevins.kafkaTwitterData.model.Stream;
import com.wynnblevins.kafkaTwitterData.repository.StreamRepository;

@Service
public class StreamService {
	@Autowired
	private StreamRepository streamRepository;
	
	public Stream getStreamById(String id) {
		return streamRepository.findOne(id);
	}
	
	public Stream updateStream(String id, Stream tweetStreet) {
		// find the tweet stream record in the database
		
		// update it
		
		// save the updated tweet stream 
		return streamRepository.save(tweetStreet);
	}
	
	public Stream createStream(Stream stream) {
		return streamRepository.save(stream);
	}
	
	public void deleteStream(String id) {
		streamRepository.delete(id);
	}
}
