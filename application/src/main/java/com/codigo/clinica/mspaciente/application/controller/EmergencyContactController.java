package com.codigo.clinica.mspaciente.application.controller;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.EmergencyContactServiceIn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "API Rest de mantenimiento de Contactos de Emergencia.",
        description = "Incluye EndPoints para realizar el mantenimiento de los contactos de emergencia."
)
@RestController
@RequestMapping("/api/v1/ms-patient/contact")
@AllArgsConstructor
public class EmergencyContactController {

    private final EmergencyContactServiceIn emergencyContactServiceIn;

    @Operation(summary = "Crear un Contacto de Emergencia.",
            description = "Para usar este EndPoint, debes enviar un objeto Contacto de Emergencia que será guardado en base de datos, previa validacion.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contacto de Emergencia creado con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EmergencyContactDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/create")
    public ResponseEntity<EmergencyContactDto> create(@RequestBody EmergencyContactRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(emergencyContactServiceIn.createEmergencyContactIn(request));
    }

    @Operation(summary = "Buscar todos los registros de Contactos de Emergencia.",
            description = "EndPoint que lista todos los registros de Contactos de Emergencia de la base de datos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contactos de Emergencia encontradas con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EmergencyContactDto.class))}),
            @ApiResponse(responseCode = "404", description = "Contactos de Emergencia no encontradas.", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/all")
    public ResponseEntity<List<EmergencyContactDto>> getAll(){
        return ResponseEntity.ok(emergencyContactServiceIn.getAllIn());
    }

    @Operation(summary = "Buscar un Contacto de Emergencia por su Id.",
            description = "Para usar este EndPoint, debes enviar el Id del Contacto de Emergencia a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "id", description = "Id de búsqueda.", required = true, example = "1")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contacto de Emergencia encontrado con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EmergencyContactDto.class))}),
            @ApiResponse(responseCode = "404", description = "Contacto de Emergencia no encontrada.", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<EmergencyContactDto> getEmergencyContactById(@PathVariable Long id){
        return ResponseEntity.ok(emergencyContactServiceIn.findByIdIn(id).orElseThrow());
    }

    @Operation(summary = "Actualizar un Contacto de Emergencia.",
            description = "Para usar este EndPoint, debes enviar un objeto Contacto de Emergencia (sus cambios) que será guardado en base de datos, previa validacion.",
            parameters = {
                    @Parameter(name = "id", description = "Id de Contacto de Emergencia.", required = true, example = "1"),
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contacto de Emergencia actualizada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EmergencyContactDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<EmergencyContactDto> updateEmergencyContact(@PathVariable Long id,
                                                                      @RequestBody EmergencyContactRequest request){
        return ResponseEntity.ok(emergencyContactServiceIn.updateIn(id, request));
    }

    @Operation(summary = "Eliminar un Contacto de Emergencia por su Id.",
            description = "Para usar este EndPoint, enviar el Id del Contacto de Emergencia a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "id", description = "Id para eliminación.", required = true, example = "1")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contacto de Emergencia eliminado con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EmergencyContactDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmergencyContactDto> deleteEmergencyContact(@PathVariable Long id){
        return ResponseEntity.ok(emergencyContactServiceIn.deleteIn(id));
    }

}
