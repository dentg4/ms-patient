package com.codigo.clinica.mspaciente.application.controller;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.TeatmentDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.TeatmentRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.TeatmentServiceIn;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
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
        name = "API Rest de mantenimiento de tratamiento.",
        description = "Incluye EndPoints para realizar el mantenimiento de un tratamiento."
)
@RestController
@RequestMapping("/api/v1/ms-patient/teatment")
@AllArgsConstructor
public class TeatmentController {

    private final TeatmentServiceIn teatmentServiceIn;

    @Operation(summary = "Crear un tratamiento.",
            description = "Para usar este EndPoint, debes enviar un objeto tratamiento que será guardado en base de datos, previa validacion.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tratamiento creada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TeatmentDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/create")
    public ResponseEntity<TeatmentDto> create(@RequestBody TeatmentRequest teatmentRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(teatmentServiceIn.createTeatmentIn(teatmentRequest));
    }


    @Operation(summary = "Buscar un tratamiento por su Id.",
            description = "Para usar este EndPoint, debes enviar el Id del tratamiento a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "id", description = "Id de búsqueda.", required = true, example = "1")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tratamiento encontrado con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TeatmentDto.class))}),
            @ApiResponse(responseCode = "404", description = "Tratamiento no encontradO.", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<TeatmentDto> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(teatmentServiceIn.findByIdIn(id).orElseThrow(()-> new RuntimeException("Tratamiento no no encontrado.")));
    }


    @Operation(summary = "Buscar todos los registros de tratamiento.",
            description = "EndPoint que lista todos los registros tratamiento de la base de datos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tratamientos encontrados con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TeatmentDto.class))}),
            @ApiResponse(responseCode = "404", description = "TratamientoS no encontrados.", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/all")
    public ResponseEntity<List<TeatmentDto>> findAll(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(teatmentServiceIn.getAllIn());
    }


    @Operation(summary = "Actualizar un tratamiento.",
            description = "Para usar este EndPoint, debes enviar un objeto tratamiento (sus cambios) que será guardado en base de datos, previa validacion.",
            parameters = {
                    @Parameter(name = "id", description = "Id de tratamiento.", required = true, example = "1"),
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tratamiento actualizado con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TeatmentDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<TeatmentDto> update(@PathVariable Long id, @RequestBody TeatmentRequest teatmentRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(teatmentServiceIn.updateIn(id, teatmentRequest));
    }


    @Operation(summary = "Eliminar un tratamiento por su Id.",
            description = "Para usar este EndPoint, enviar el Id del tratamiento a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "id", description = "Id para eliminación.", required = true, example = "1")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tratamiento eliminado con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TeatmentDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TeatmentDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(teatmentServiceIn.deleteIn(id));
    }
}
