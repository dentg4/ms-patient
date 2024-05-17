package com.codigo.clinica.mspaciente.domain.ports.in;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;

import java.util.List;
import java.util.Optional;

public interface EmergencyContactServiceIn {
    EmergencyContactDto createEmergencyContactIn(EmergencyContactRequest request);
    Optional<EmergencyContactDto> findByIdIn(Long id);
    List<EmergencyContactDto> getAllIn();
    EmergencyContactDto updateIn(Long id, EmergencyContactRequest request);
    EmergencyContactDto deleteIn(Long id);
}
