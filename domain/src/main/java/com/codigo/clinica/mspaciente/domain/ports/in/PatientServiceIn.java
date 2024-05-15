package com.codigo.clinica.mspaciente.domain.ports.in;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.PatientDTO;
import com.codigo.clinica.mspaciente.domain.aggregates.request.PatientRequest;

import java.util.List;
import java.util.Optional;

public interface PatientServiceIn {
    PatientDTO crearPatienIn(PatientRequest empresaRequest);
    Optional<PatientDTO> buscarPorIdIn(Long id);
    List<PatientDTO> obtenerTodosIn();
    PatientDTO actualizarIn(Long id, PatientRequest empresaRequest);
    PatientDTO deleteIn(Long id);
}
