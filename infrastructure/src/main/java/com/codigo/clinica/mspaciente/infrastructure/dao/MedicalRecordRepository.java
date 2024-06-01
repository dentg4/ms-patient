package com.codigo.clinica.mspaciente.infrastructure.dao;

import com.codigo.clinica.mspaciente.infrastructure.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
