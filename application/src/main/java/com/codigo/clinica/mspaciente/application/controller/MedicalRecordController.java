package com.codigo.clinica.mspaciente.application.controller;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.MedicalRecordDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.MedicalRecordRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.MedicalRecordServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "API Rest de mantenimiento de historias.",
        description = "Incluye EndPoints para realizar el mantenimiento de una historias."
)
@RestController
@RequestMapping("/api/v1/ms-paciente/historias")
@AllArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordServiceIn medicalRecordServiceIn;

    @Operation(summary = "Crear una historia.",
            description = "Para usar este EndPoint, debes enviar un objeto historia que será guardado en base de datos, previa validacion.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historia creada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MedicalRecordDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/crear")
    public ResponseEntity<MedicalRecordDto> create(@RequestBody MedicalRecordRequest medicalRecordRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicalRecordServiceIn.createMedicalRecordIn(medicalRecordRequest));
    }


    @Operation(summary = "Buscar una historia por su Id.",
            description = "Para usar este EndPoint, debes enviar el Id de la historia a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "id", description = "Id de búsqueda.", required = true, example = "1")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historia encontrada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MedicalRecordDto.class))}),
            @ApiResponse(responseCode = "404", description = "Historia no encontrada.", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<MedicalRecordDto> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicalRecordServiceIn.findByIdIn(id).orElseThrow(()-> new RuntimeException("Historial no no encontrado.")));
    }


    @Operation(summary = "Buscar todos los registros de historia.",
            description = "EndPoint que lista todos los registros historia de la base de datos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historias encontradas con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MedicalRecordDto.class))}),
            @ApiResponse(responseCode = "404", description = "Historias no encontradas.", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/buscartodos")
    public ResponseEntity<List<MedicalRecordDto>> findAll(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicalRecordServiceIn.getAllIn());
    }


    @Operation(summary = "Actualizar una historia.",
            description = "Para usar este EndPoint, debes enviar un objeto historia (sus cambios) que será guardado en base de datos, previa validacion.",
            parameters = {
                    @Parameter(name = "id", description = "Id de historia.", required = true, example = "1"),
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historia actualizada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MedicalRecordDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<MedicalRecordDto> update(@PathVariable Long id, @RequestBody MedicalRecordRequest medicalRecordRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicalRecordServiceIn.updateIn(id, medicalRecordRequest));
    }


    @Operation(summary = "Eliminar una historia por su Id.",
            description = "Para usar este EndPoint, enviar el Id de la historia a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "id", description = "Id para eliminación.", required = true, example = "1")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historia eliminada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MedicalRecordDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<MedicalRecordDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicalRecordServiceIn.deleteIn(id));
    }
}
