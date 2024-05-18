package com.codigo.clinica.mspaciente.domain.impl;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.MedicalRecordDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.MedicalRecordRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.MedicalRecordServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicalRecordImpl implements MedicalRecordServiceIn {
    @Override
    public MedicalRecordDto createStoryIn(MedicalRecordRequest request) {
        return null;
    }

    @Override
    public Optional<MedicalRecordDto> findByIdIn(Long id) {
        return Optional.empty();
    }

    @Override
    public List<MedicalRecordDto> getAllIn() {
        return List.of();
    }

    @Override
    public MedicalRecordDto updateIn(Long id, MedicalRecordRequest request) {
        return null;
    }

    @Override
    public MedicalRecordDto deleteIn(Long id) {
        return null;
    }
}
