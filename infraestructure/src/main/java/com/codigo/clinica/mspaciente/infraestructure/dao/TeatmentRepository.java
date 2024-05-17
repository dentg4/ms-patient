package com.codigo.clinica.mspaciente.infraestructure.dao;

import com.codigo.clinica.mspaciente.infraestructure.entity.Teatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeatmentRepository extends JpaRepository<Teatment, Long> {
}
