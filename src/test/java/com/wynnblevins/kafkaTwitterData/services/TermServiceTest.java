package com.wynnblevins.kafkaTwitterData.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wynnblevins.kafkaTwitterData.exception.NotFoundException;
import com.wynnblevins.kafkaTwitterData.model.Term;
import com.wynnblevins.kafkaTwitterData.repository.TermRepository;
import com.wynnblevins.kafkaTwitterData.service.TermService;
import com.wynnblevins.kafkaTwitterData.testUtils.MockDataProducer;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TermServiceTest {
    @InjectMocks
    private TermService termService;

    @Mock
    private TermRepository termRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll_TwoTermsInDb_ReturnsTwoTerms() {
        Set<Term> terms = new HashSet<>();
        Term term1 = new Term("Donald Trump");
        Term term2 = new Term("George Bush");
        terms.add(term1);
        terms.add(term2);
        when(termRepository.findAll()).thenReturn(terms);

        Set<Term> resultTerms = termService.getAll();

        assertNotNull(resultTerms);
        assertEquals(resultTerms.size(), 2);
        assertEquals(resultTerms.contains(term1), true);
        assertEquals(resultTerms.contains(term2), true);
    }

    @Test
    public void getAll_NoTermsInDb_ReturnsEmptySet() {
        Set<Term> emptyTerms = new HashSet<>();
        when(termRepository.findAll()).thenReturn(emptyTerms);

        Set<Term> resultTerms = termService.getAll();

        assertNotNull(emptyTerms);
        assertEquals(resultTerms.size(), 0);
    }

    @Test
    public void getById_TermFoundInDb_ReturnsTermWithId() throws NotFoundException {
        Term expectedTerm = new Term("Donald Trump");
        String expectedId = expectedTerm.getId();
        when(termRepository.findById(expectedId)).thenReturn(Optional.of(expectedTerm));

        Term actualTerm = termService.getById(expectedId);

        assertEquals(actualTerm.getId(), expectedTerm.getId());
    }

    @Test(expected = NotFoundException.class)
    public void getById_TermNotFoundInDb_ThrowsNotFoundException() throws Exception {
        String notFoundTermId = "NotARealId";
        termService.getById(notFoundTermId);
    }

    @Test
    public void createTerm_GivenTermWithoutID_CallsSaveOnRepositoryAndResultingTermHasID() {
        String termId = "TestTermId";
        String termValue = "TestTermValue";

        Term expectedTerm = new Term();
        expectedTerm.setId(termId);
        expectedTerm.setValue(termValue);

        Term newTerm = new Term();
        newTerm.setValue(termValue);
        when(termRepository.save(newTerm)).thenReturn(expectedTerm);

        Term resultTerm = termService.saveTerm(newTerm);

        assertNotNull(resultTerm.getId());
        assertEquals(resultTerm.getValue(), newTerm.getValue());
        assertEquals(resultTerm.getId(), expectedTerm.getId());
    }

    @Test(expected = NotFoundException.class)
    public void deleteTerm_GivenNonexistentTermID_EncountersIllegalArgumentExceptionThrowsNotFoundException()
        throws Exception {
        Term dummyTerm = MockDataProducer.createDummyTerm("NonexistentTermId", "Blah");
        doThrow(IllegalArgumentException.class).when(termRepository).delete(dummyTerm);

        termService.deleteTerm(dummyTerm);

        verify(termRepository, times(1)).delete(dummyTerm);
    }
}
