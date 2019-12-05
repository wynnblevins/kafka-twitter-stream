package com.wynnblevins.kafkaTwitterData.exception;

public class NotFoundException extends Exception {
    public NotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
