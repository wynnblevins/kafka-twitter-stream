package com.wynnblevins.kafkaTwitterData.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="term")
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column
    private String value;

    public Term() {}

    public Term(String value) { this.value = value; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }
}
