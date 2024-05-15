package com.codigo.clinica.mspaciente.infraestructure.dao;

import com.codigo.clinica.mspaciente.infraestructure.entity.EmergencyContactsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContactsEntity, Long> {
}
