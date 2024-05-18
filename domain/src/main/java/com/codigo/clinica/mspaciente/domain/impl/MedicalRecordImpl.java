package com.codigo.clinica.mspaciente.domain.impl;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.MedicalRecordDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.MedicalRecordRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.MedicalRecordServiceIn;
import com.codigo.clinica.mspaciente.domain.ports.out.MedicalRecordServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicalRecordImpl implements MedicalRecordServiceIn {

    private final MedicalRecordServiceOut medicalRecordServiceOut;

    @Override
    public MedicalRecordDto createMedicalRecordIn(MedicalRecordRequest request) {
        return medicalRecordServiceOut.createMedicalRecordOut(request);
    }

    @Override
    public Optional<MedicalRecordDto> findByIdIn(Long id) {
        return medicalRecordServiceOut.findByIdOut(id);
    }

    @Override
    public List<MedicalRecordDto> getAllIn() {
        return medicalRecordServiceOut.getAllOut();
    }

    @Override
    public MedicalRecordDto updateIn(Long id, MedicalRecordRequest request) {
        return medicalRecordServiceOut.updateOut(id, request);
    }

    @Override
    public MedicalRecordDto deleteIn(Long id) {
        return medicalRecordServiceOut.deleteOut(id);
    }
}
