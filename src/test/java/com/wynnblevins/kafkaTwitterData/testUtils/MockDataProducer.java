package com.wynnblevins.kafkaTwitterData.testUtils;

import com.wynnblevins.kafkaTwitterData.model.Stock;
import com.wynnblevins.kafkaTwitterData.model.Term;
import com.wynnblevins.kafkaTwitterData.model.TweetStream;
import java.math.BigDecimal;

public class MockDataProducer {
    public static Term createDummyTerm(String id, String value) {
        Term term = new Term();
        term.setId(id);
        term.setValue(value);
        return term;
    }

    public static Stock createDummyStock(String ticker, String companyName) {
        Stock dummyStock = new Stock();
        dummyStock.setCompany(companyName);
        dummyStock.setTicker(ticker);
        dummyStock.setShareValue(new BigDecimal(30.0));
        return dummyStock;
    }

    public static TweetStream createTermStream(String termId, String termValue, String ticker,
        String companyName) {
        TweetStream dummyTweetStream = new TweetStream();
        dummyTweetStream.setId("00000000-0000-0000-0000-000000000000");
        dummyTweetStream.setStock(createDummyStock(ticker, companyName));
        dummyTweetStream.setTerm(createDummyTerm(termId, termValue));
        return dummyTweetStream;
    }
}
