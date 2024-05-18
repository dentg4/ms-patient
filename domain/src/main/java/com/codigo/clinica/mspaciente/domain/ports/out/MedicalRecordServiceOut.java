package com.codigo.clinica.mspaciente.domain.ports.out;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.MedicalRecordDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.MedicalRecordRequest;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordServiceOut {
    MedicalRecordDto createMedicalRecordOut(MedicalRecordRequest request);
    Optional<MedicalRecordDto> findByIdOut(Long id);
    List<MedicalRecordDto> getAllOut();
    MedicalRecordDto updateOut(Long id, MedicalRecordRequest request);
    MedicalRecordDto deleteOut(Long id);
}
