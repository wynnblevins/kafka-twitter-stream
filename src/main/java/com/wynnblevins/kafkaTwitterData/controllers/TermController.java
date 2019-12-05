package com.wynnblevins.kafkaTwitterData.controllers;

import com.wynnblevins.kafkaTwitterData.model.Term;
import com.wynnblevins.kafkaTwitterData.service.TermService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TermController {
    private TermService termService;

    @Autowired
    public TermController(TermService termService) {
        this.termService = termService;
    }

    @RequestMapping(path="/terms")
    public Set<Term> getAll() {
        return termService.getAll();
    }
}
