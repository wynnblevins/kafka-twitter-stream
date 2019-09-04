package com.wynnblevins.kafkaTwitterData.producer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.twitter.hbc.core.Client;
import com.wynnblevins.kafkaTwitterData.factory.ClientFactory;
import com.wynnblevins.kafkaTwitterData.factory.SafeProducerFactory;
import com.wynnblevins.kafkaTwitterData.model.Term;
import com.wynnblevins.kafkaTwitterData.model.Tweet;
import com.wynnblevins.kafkaTwitterData.producer.*;

import org.apache.kafka.clients.producer.*;

@Component
public class TwitterProducer {
    private Logger logger = LoggerFactory.getLogger(TwitterProducer.class.getName());
    
    private SafeProducerFactory<String, String> producerFactory = new SafeProducerFactory<String, String>();
    
    @Autowired
    private ClientFactory clientFactory;
        
    public TwitterProducer() {}

    public void run(Set<Term> set) {
        logger.info("Setup...");

        /** Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(1000);

        // create a twitter client and attempt to establish a connection.
        Client client = clientFactory.createTwitterClient(msgQueue, set);
        client.connect();
        
        // create a kafka producer
        KafkaProducer<String, String> producer = producerFactory.createSafeKafkaProducer();

        // add a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("stopping application...");
            logger.info("shutting down client from twitter...");
            client.stop();
            logger.info("closing producer...");
            producer.close();
            logger.info("done!");
        }));

        List<Tweet> tweets = new ArrayList<Tweet>();
        
        // loop to send tweets to kafka
        // on a different thread, or multiple different threads....
        while (!client.isDone()) {
            String msg = null;
            try {
                msg = msgQueue.poll(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("Ooopsie woopsie... error encountered...");
                client.stop();
            }
            if (msg != null){
                // tweets are going to a kafka topic called twitter tweets
                producer.send(new ProducerRecord<>("tweets", null, msg), new Callback() {
                	
                	@Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (e != null) {
                            logger.error("Encountered error", e);
                        }
                    }
                });
                
                Gson gson = new Gson();
            	Map<String,String> tweetMap = new HashMap<String,String>();
            	tweetMap = (Map<String,String>) gson.fromJson(msg, tweetMap.getClass());
            		
				Tweet tweet = new Tweet(tweetMap.get("text"));
				logger.info(tweet.getText());
            }
        }
        logger.info("End of application");
    }
}