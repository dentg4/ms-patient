package com.codigo.clinica.mspaciente.application.controller;


import com.codigo.clinica.mspaciente.domain.aggregates.dto.PatientDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.PatientRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.PatientServiceIn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "API Rest de mantenimiento de Pacientes.",
        description = "Incluye EndPoints para realizar el mantenimiento de un Paciente."
)
@RestController
@RequestMapping("/api/v1/ms-patient/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientServiceIn patientServiceIn;

    @Operation(summary = "Crear una Paciente.",
            description = "Para usar este EndPoint, debes enviar un objeto Paciente que será guardado en base de datos, previa validacion.")
    @ApiResponse(responseCode = "200", description = "Paciente creado con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))})
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    @PostMapping("/create")
    public ResponseEntity<PatientDto> create(@Valid @RequestBody PatientRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(patientServiceIn.createPatientIn(request));
    }

    @Operation(summary = "Buscar todos los registros de Pacientes.",
            description = "EndPoint que lista todos los registros de Pacientes de la base de datos.")
    @ApiResponse(responseCode = "200", description = "Pacientes encontradas con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))})
    @ApiResponse(responseCode = "404", description = "Pacientes no encontradas.", content = { @Content(schema = @Schema()) })
    @GetMapping("/all")
    public ResponseEntity<List<PatientDto>> getAll(){
        return ResponseEntity.ok(patientServiceIn.getAllIn());
    }

    @Operation(summary = "Buscar un Paciente por su Id.",
            description = "Para usar este EndPoint, debes enviar el Id de la Paciente a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "id", description = "Id de búsqueda.", required = true, example = "1")
            })
    @ApiResponse(responseCode = "200", description = "Paciente encontrado con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))})
    @ApiResponse(responseCode = "404", description = "Paciente no encontrado.", content = { @Content(schema = @Schema()) })
    @GetMapping("/find/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id){
        return patientServiceIn.findByIdIn(id).map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Operation(summary = "Actualizar un Paciente.",
            description = "Para usar este EndPoint, debes enviar un objeto Paciente (sus cambios) que será guardado en base de datos, previa validacion.",
            parameters = {
                    @Parameter(name = "id", description = "Id de Paciente.", required = true, example = "1"),
            })
    @ApiResponse(responseCode = "200", description = "Paciente actualizado con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))})
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    @PutMapping("/update/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id,
                                                                      @Valid @RequestBody PatientRequest request){
        return ResponseEntity.ok(patientServiceIn.updateIn(id, request));
    }

    @Operation(summary = "Eliminar un Paciente por su Id.",
            description = "Para usar este EndPoint, enviar el Id de la Paciente a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "id", description = "Id para eliminación.", required = true, example = "1")
            })
    @ApiResponse(responseCode = "200", description = "Paciente eliminado con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))})
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PatientDto> deletePatient(@PathVariable Long id){
        return ResponseEntity.ok(patientServiceIn.deleteIn(id));
    }

}