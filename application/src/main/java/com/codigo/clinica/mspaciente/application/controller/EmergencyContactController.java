package com.codigo.clinica.mspaciente.application.controller;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDTO;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.EmergencyContactServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emergency/contact")
@AllArgsConstructor
public class EmergencyContactController {
    private final EmergencyContactServiceIn emergencyContactServiceIn;

    @PostMapping
    public ResponseEntity<EmergencyContactDTO> createEmergencyContact(@RequestBody EmergencyContactRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(emergencyContactServiceIn.crearEmergencyContactIn(request));
    }
    @GetMapping
    public ResponseEntity<List<EmergencyContactDTO>> getAll(){
        return ResponseEntity.ok(emergencyContactServiceIn.obtenerTodosIn());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmergencyContactDTO> getEmergencyContactById(@PathVariable Long id){
        return ResponseEntity.ok(emergencyContactServiceIn.buscarPorIdIn(id).orElseThrow());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmergencyContactDTO> updateEmergencyContact(@PathVariable Long id,
                                                                      @RequestBody EmergencyContactRequest request){
        return ResponseEntity.ok(emergencyContactServiceIn.actualizarIn(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmergencyContactDTO> deleteEmergencyContact(@PathVariable Long id){
        return ResponseEntity.ok(emergencyContactServiceIn.deleteIn(id));
    }

}
