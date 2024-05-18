package com.codigo.clinica.mspaciente.domain.ports.in;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.MedicalRecordDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.MedicalRecordRequest;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordServiceIn {
    MedicalRecordDto createStoryIn(MedicalRecordRequest request);
    Optional<MedicalRecordDto> findByIdIn(Long id);
    List<MedicalRecordDto> getAllIn();
    MedicalRecordDto updateIn(Long id, MedicalRecordRequest request);
    MedicalRecordDto deleteIn(Long id);
}
