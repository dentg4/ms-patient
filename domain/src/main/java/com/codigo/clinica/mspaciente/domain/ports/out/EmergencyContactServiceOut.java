package com.codigo.clinica.mspaciente.domain.ports.out;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDTO;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;

import java.util.List;
import java.util.Optional;

public interface EmergencyContactServiceOut {
    EmergencyContactDTO crearEmergencyContactOut(EmergencyContactRequest request);
    Optional<EmergencyContactDTO> buscarPorIdOut(Long id);
    List<EmergencyContactDTO> obtenerTodosOut();
    EmergencyContactDTO actualizarOut(Long id, EmergencyContactRequest request);
    EmergencyContactDTO deleteOut(Long id);
}
