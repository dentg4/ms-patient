package com.codigo.clinica.mspaciente.domain.ports.out;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.PatientDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.PatientRequest;

import java.util.List;
import java.util.Optional;

public interface PatientServiceOut {
    PatientDto createPatientOut(PatientRequest request);
    Optional<PatientDto> findByIdOut(Long id);
    List<PatientDto> getAllOut();
    PatientDto updateOut(Long id, PatientRequest request);
    PatientDto deleteOut(Long id);
}
