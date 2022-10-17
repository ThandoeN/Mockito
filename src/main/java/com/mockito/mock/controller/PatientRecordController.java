package com.mockito.mock.controller;

import com.mockito.mock.entity.PatientRecord;
import com.mockito.mock.repository.PatientRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/patient")
public class PatientRecordController {

    @Autowired
    PatientRecordRepository patientRecordRepository;

    @GetMapping
    public List<PatientRecord> getAllRecords() {
        return patientRecordRepository.findAll();
    }

    @GetMapping(value = "{patientId}")
    public PatientRecord getPatientById(@PathVariable(value="patientId") Long patientId) {
        return patientRecordRepository.findById(patientId).get();
    }

}
