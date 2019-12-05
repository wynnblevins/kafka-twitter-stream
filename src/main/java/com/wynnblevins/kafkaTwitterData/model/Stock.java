package com.wynnblevins.kafkaTwitterData.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "company")
    private String company;

    @Column(name = "share_value")
    private BigDecimal shareValue;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getCompany() { return company; }

    public void setCompany(String company) { this.company = company; }

    public BigDecimal getShareValue() { return shareValue; }

    public void setShareValue(BigDecimal shareValue) { this.shareValue = shareValue; }

    public String getTicker() { return ticker; }

    public void setTicker(String ticker) { this.ticker = ticker; }
}
