package com.codigo.clinica.mspaciente.domain.ports.in;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.PatientDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.PatientRequest;

import java.util.List;
import java.util.Optional;

public interface PatientServiceIn {
    PatientDto createPatientIn(PatientRequest request);
    Optional<PatientDto> findByIdIn(Long id);
    List<PatientDto> getAllIn();
    PatientDto updateIn(Long id, PatientRequest request);
    PatientDto deleteIn(Long id);
}
