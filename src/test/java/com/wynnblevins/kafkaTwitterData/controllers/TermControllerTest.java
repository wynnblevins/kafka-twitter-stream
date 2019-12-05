package com.wynnblevins.kafkaTwitterData.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wynnblevins.kafkaTwitterData.MockMvcTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class TermControllerTest extends MockMvcTest {
    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(TermController.class).build();
    }

    @Test
    public void getTerms_ThreeTermsInDB_ReturnsThreeTerms() throws Exception {
        this.mvc.perform(get("/streams"))
            .andExpect(status().isOk());
    }
}
