package com.codigo.clinica.mspaciente.domain.ports.in;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDTO;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;

import java.util.List;
import java.util.Optional;

public interface EmergencyContactServiceIn {
    EmergencyContactDTO crearEmergencyContactIn(EmergencyContactRequest request);
    Optional<EmergencyContactDTO> buscarPorIdIn(Long id);
    List<EmergencyContactDTO> obtenerTodosIn();
    EmergencyContactDTO actualizarIn(Long id, EmergencyContactRequest request);
    EmergencyContactDTO deleteIn(Long id);
}
