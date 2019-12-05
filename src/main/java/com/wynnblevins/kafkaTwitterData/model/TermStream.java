package com.wynnblevins.kafkaTwitterData.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "term_stream")
public class TermStream {
    public TermStream() {}

    public TermStream(Term term, Stock stock) {
        this.term = term;
        this.stock = stock;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @JoinColumn(name = "stock_id")
    private Stock stock;

    @JoinColumn(name = "term_id")
    private Term term;

    @Column(name = "running")
    private boolean isRunning;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public boolean isRunning() { return isRunning; }

    public void setRunning(boolean running) { isRunning = running; }

    public Stock getStock() { return stock; }

    public void setStock(Stock stock) { this.stock = stock; }

    public Term getTerm() { return term; }

    public void setTerm(Term term) { this.term = term; }
}
