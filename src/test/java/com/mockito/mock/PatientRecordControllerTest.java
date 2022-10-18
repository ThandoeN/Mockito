package com.mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockito.mock.controller.PatientRecordController;
import com.mockito.mock.entity.PatientRecord;
import com.mockito.mock.repository.PatientRecordRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientRecordController.class)
public class PatientRecordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    PatientRecordRepository patientRecordRepository;

    PatientRecord RECORD_1 = new PatientRecord(1l, "Rayven Yor", 23, "Cebu Philippines");
    PatientRecord RECORD_2 = new PatientRecord(2l, "David Landup", 27, "New York USA");
    PatientRecord RECORD_3 = new PatientRecord(3l, "Jane Doe", 31, "New York USA");

    @Test
    public void getAllRecords_success() throws Exception {
        List<PatientRecord> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(patientRecordRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(3)))
                .andExpect((ResultMatcher) jsonPath("$[2].name", is("Jane Doe")));
    }

    @Test
    public void getPatientById_success() throws Exception {
        Mockito.when(patientRecordRepository.findById(RECORD_1.getPatientId())).thenReturn(java.util.Optional.of(RECORD_1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.name", is("Rayven Yor")));
    }


}
