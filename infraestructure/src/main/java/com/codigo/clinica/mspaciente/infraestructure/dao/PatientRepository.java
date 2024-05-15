package com.codigo.clinica.mspaciente.infraestructure.dao;

import com.codigo.clinica.mspaciente.infraestructure.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
}
