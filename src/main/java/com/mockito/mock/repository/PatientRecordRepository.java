package com.mockito.mock.repository;

import com.mockito.mock.entity.PatientRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecord, Long> {
    List<PatientRecord> findAll();
}
