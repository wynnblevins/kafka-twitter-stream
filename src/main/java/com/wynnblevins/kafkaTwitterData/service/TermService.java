package com.wynnblevins.kafkaTwitterData.service;

import com.wynnblevins.kafkaTwitterData.exception.NotFoundException;
import com.wynnblevins.kafkaTwitterData.model.Term;
import com.wynnblevins.kafkaTwitterData.repository.TermRepository;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermService {
    private TermRepository termRepository;

    @Autowired
    public TermService(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    public Set<Term> getAll() {
        Iterable<Term> termIterable = termRepository.findAll();
        return StreamSupport.stream(termIterable.spliterator(), false)
            .collect(Collectors.toSet());
    }

    public Term getById(String termId) throws NotFoundException {
        Optional<Term> maybeTerm = termRepository.findById(termId);
        if (maybeTerm.isPresent()) {
            return maybeTerm.get();
        }
        throw new NotFoundException(String.format("Term with ID %s not found.", termId));
    }

    public Term saveTerm(Term term) {
        return termRepository.save(term);
    }

    public void deleteTerm(Term term) throws NotFoundException {
        try {
            termRepository.delete(term);
        } catch (IllegalArgumentException iae) {
            throw new NotFoundException(iae.toString());
        }
    }
}
