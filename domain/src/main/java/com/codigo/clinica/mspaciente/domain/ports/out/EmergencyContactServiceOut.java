package com.codigo.clinica.mspaciente.domain.ports.out;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;

import java.util.List;
import java.util.Optional;

public interface EmergencyContactServiceOut {
    EmergencyContactDto createEmergencyContactOut(EmergencyContactRequest request);
    Optional<EmergencyContactDto> findByIdOut(Long id);
    List<EmergencyContactDto> getAllOut();
    EmergencyContactDto updateOut(Long id, EmergencyContactRequest request);
    EmergencyContactDto deleteOut(Long id);
}
