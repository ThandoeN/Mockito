package com.mockito.mock;

import com.mockito.mock.controller.PatientRecordController;
import com.mockito.mock.entity.PatientRecord;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PatientRecordController.class)
public class PatientRecordControllerTest {

    @Autowired
    MockMvc mvc;


    @MockBean
    PatientRecordController  patientRecordController;


    @Test
    public void getAllRecords_success() throws Exception {

        PatientRecord patientRecord = new PatientRecord();
        patientRecord.setPatientId(2L);
        patientRecord.setName("David Landup");
        patientRecord.setAge(27);
        patientRecord.setAddress("New York USA");

        List<PatientRecord> records = singletonList(patientRecord);

        given (patientRecordController.getAllRecords()).willReturn(records);

        mvc.perform(get("/patient")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Is.is(patientRecord.getName())));
    }

    @Test
    public void getPatientById_success() throws Exception {


        PatientRecord patientRecord = new PatientRecord();
        patientRecord.setPatientId(2L);
        patientRecord.setName("David Landup");
        patientRecord.setAge(27);
        patientRecord.setAddress("New York USA");

        given(patientRecordController.getPatientById(patientRecord.getPatientId())).willReturn(patientRecord);

        mvc.perform(get("/patient")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name", Is.is(patientRecord.getName())));
    }


}
