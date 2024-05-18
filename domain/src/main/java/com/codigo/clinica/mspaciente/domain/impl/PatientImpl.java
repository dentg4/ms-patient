package com.codigo.clinica.mspaciente.domain.impl;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.PatientDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.PatientRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.PatientServiceIn;
import com.codigo.clinica.mspaciente.domain.ports.out.PatientServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientImpl implements PatientServiceIn {

    //private final PatientServiceOut patientServiceOut;

    @Override
    public PatientDto createPatienIn(PatientRequest request) {
        return null;
    }

    @Override
    public Optional<PatientDto> findByIdIn(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PatientDto> getAllIn() {
        return List.of();
    }

    @Override
    public PatientDto updateIn(Long id, PatientRequest request) {
        return null;
    }

    @Override
    public PatientDto deleteIn(Long id) {
        return null;
    }
}
