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

    private final PatientServiceOut patientServiceOut;

    @Override
    public PatientDto createPatientIn(PatientRequest request) {
        return patientServiceOut.createPatientOut(request);
    }

    @Override
    public Optional<PatientDto> findByIdIn(Long id) {
        return patientServiceOut.findByIdOut(id);
    }

    @Override
    public List<PatientDto> getAllIn() {
        return patientServiceOut.getAllOut();
    }

    @Override
    public PatientDto updateIn(Long id, PatientRequest request) {
        return patientServiceOut.updateOut(id, request);
    }

    @Override
    public PatientDto deleteIn(Long id) {
        return patientServiceOut.deleteOut(id);
    }
}
