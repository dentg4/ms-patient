package com.codigo.clinica.mspaciente.infraestructure.adapters;

import com.codigo.clinica.mspaciente.domain.aggregates.constants.Constants;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.PatientDto;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.ReniecDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.PatientRequest;
import com.codigo.clinica.mspaciente.infraestructure.client.ClientReniec;
import com.codigo.clinica.mspaciente.infraestructure.dao.PatientRepository;
import com.codigo.clinica.mspaciente.infraestructure.entity.Patient;
import com.codigo.clinica.mspaciente.infraestructure.redis.RedisService;
import com.codigo.clinica.mspaciente.infraestructure.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientAdapterTest {

    @Mock
    private PatientRepository patientRepository;
    @Mock
    private RedisService redisService;
    @Mock
    private ClientReniec clientReniec;

    @InjectMocks
    private PatientAdapter patientAdapter;

    @BeforeEach
    void up(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPatientOut() {

        PatientRequest request= PatientRequest.builder()
                .identificationType("DNI").identificationNumber("73103894")
                .gender("Masculino").phone("956940085")
                .email("quispejhonn021@gmail.com").build();
        ReniecDto reniecDto = ReniecDto.builder().nombres("Jhon")
                .apellidoMaterno("Huamani").apellidoPaterno("Quispe")
                .tipoDocumento("2").numeroDocumento(request.getIdentificationNumber())
                .digitoVerificador("").build();

        when(clientReniec.getInfoReniec(anyString(),anyString())).thenReturn(reniecDto);
        Patient patient = patientAdapter.propertyCreate(request);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        PatientDto response = patientAdapter.createPatientOut(request);
        assertNotNull(response);
        assertNotNull(response.getCreateOn());
        assertNotNull(response.getCreatedBy());
        assertEquals(response.getStatus(), Constants.STATUS_ACTIVE);
        assertEquals(patient.getIdentificationNumber(),response.getIdentificationNumber());
        assertEquals(patient.getEmail(),response.getEmail());
        assertEquals(patient.getPhone(), response.getPhone());

    }

    @Test
    void findByIdOutForRedis() {
        Long id=1l;
        PatientDto patientDto=PatientDto.builder()
                .idPatient(id).gender("Masculino").phone("95694085")
                        .name("Jhon").surname("surname").identificationType("DNI")
                        .identificationNumber("73103894").build();
        when(redisService.getFromRedis(anyString())).thenReturn(Util.convertToString(patientDto));

        PatientDto reponse = patientAdapter.findByIdOut(id).orElseThrow();
        assertNotNull(reponse);
        assertEquals(patientDto.getIdPatient(), reponse.getIdPatient());
        assertEquals(patientDto.getEmail(),reponse.getEmail());
        assertEquals(patientDto.getGender(), reponse.getGender());
        assertEquals(patientDto.getPhone(),reponse.getPhone());

    }
    @Test
    void findByIdOutForBD() {
        Long id=1l;
        Patient patient = new Patient();
        patient.setId(1l);
        patient.setGender("Masculino");
        patient.setPhone("95694085");
        patient.setName("Jhon");
        patient.setSurname("surname");
        patient.setIdentificationType("DNI");
        patient.setIdentificationNumber("73103894");
        patient.setEmail("example201@gmail.com");

        when(redisService.getFromRedis(anyString())).thenReturn(null);
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));

        PatientDto reponse = patientAdapter.findByIdOut(id).orElseThrow();
        assertNotNull(reponse);
        assertEquals(patient.getId(), reponse.getIdPatient());
        assertEquals(patient.getGender(), reponse.getGender());
        assertEquals(patient.getPhone(), reponse.getPhone());
        assertEquals(patient.getName(), reponse.getName());
        assertEquals(patient.getSurname(), reponse.getSurname());
        assertEquals(patient.getIdentificationType(),reponse.getIdentificationType());
        assertEquals(patient.getIdentificationNumber(), reponse.getIdentificationNumber());
        assertEquals(patient.getEmail(), reponse.getEmail());
    }

    @Test
    void getAllOutNoList() {

        when(patientRepository.findAll()).thenReturn(Collections.emptyList());
        List<PatientDto> reponse = patientAdapter.getAllOut();
        assertNotNull(reponse);
        assertTrue(reponse.isEmpty());
    }
    @Test
    void getAllOut() {
        Patient patient = new Patient();
        patient.setId(1l);
        List<Patient> list = new ArrayList<>();
        list.add(patient);

        when(patientRepository.findAll()).thenReturn(list);
        List<PatientDto> reponse = patientAdapter.getAllOut();
        assertNotNull(reponse);
        assertFalse(reponse.isEmpty());
        assertEquals(patient.getId(), reponse.get(0).getIdPatient());
    }

    @Test
    void updateOut() {
        Long id = 1l;

        Patient patient = new Patient();
        patient.setId(id);
        patient.setIdentificationNumber("73103894");
        patient.setName("Jhon Kevin");
        patient.setGender("Masculino");

        PatientRequest request = PatientRequest.builder()
                .identificationNumber("12345678")
                .gender("femenino").build();

        ReniecDto reniecDto = ReniecDto.builder().numeroDocumento(request.getIdentificationNumber())
                .nombres("nuevo nombre").build();
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        when(clientReniec.getInfoReniec(anyString(),anyString())).thenReturn(reniecDto);
        Patient patient1 = patientAdapter.propertyUpdate(patient,request);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient1);
        PatientDto reponse = patientAdapter.updateOut(id,request);
        assertNotNull(reponse);
        assertEquals(patient.getId(), reponse.getIdPatient());
        assertEquals(request.getGender(), reponse.getGender());
        assertEquals(request.getIdentificationNumber(), reponse.getIdentificationNumber());
        assertEquals(reniecDto.getNombres(), reponse.getName());
        assertNotNull(reponse.getUpdatedBy());
        assertNotNull(reponse.getUpdatedOn());

    }

    @Test
    void deleteOut() {
        Long id = 1l;
        Patient patient = new Patient();
        patient.setId(id);
        patient.setGender("Masculino");

        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        PatientDto reponse = patientAdapter.deleteOut(id);
        assertNotNull(reponse);
        assertEquals(patient.getId(), reponse.getIdPatient());
        assertEquals(patient.getGender(), reponse.getGender());
        assertEquals(reponse.getStatus(), Constants.STATUS_INACTIVE);
        assertNotNull(reponse.getDeletedBy());
        assertNotNull(reponse.getDeletedOn());
    }
}