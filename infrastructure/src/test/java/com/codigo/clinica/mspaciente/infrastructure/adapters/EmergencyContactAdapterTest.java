package com.codigo.clinica.mspaciente.infrastructure.adapters;

import com.codigo.clinica.mspaciente.domain.aggregates.constants.Constants;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;
import com.codigo.clinica.mspaciente.infrastructure.dao.EmergencyContactRepository;
import com.codigo.clinica.mspaciente.infrastructure.dao.PatientRepository;
import com.codigo.clinica.mspaciente.infrastructure.entity.EmergencyContact;
import com.codigo.clinica.mspaciente.infrastructure.entity.Patient;
import com.codigo.clinica.mspaciente.infrastructure.mapper.EmergencyContactMapper;
import com.codigo.clinica.mspaciente.infrastructure.redis.RedisService;
import com.codigo.clinica.mspaciente.infrastructure.util.Util;
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


class EmergencyContactAdapterTest {

    @Mock
    private EmergencyContactRepository emergencyContactRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private RedisService redisService;

    @InjectMocks
    private EmergencyContactAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEmergencyContactOut() {
        EmergencyContactRequest request =  EmergencyContactRequest.builder()
                .name("Jhon").phone("956940085").relation("hijo").patientId(5l).build();

        Patient patient = new Patient();
        patient.setId(request.getPatientId());

        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
        EmergencyContact emergencyContact = adapter.propertyCreate(request);

        when(emergencyContactRepository.save(any())).thenReturn(emergencyContact);

        EmergencyContactDto response= adapter.createEmergencyContactOut(request);
        assertNotNull(response);
        assertEquals(emergencyContact.getId(), response.getId());
        assertEquals(emergencyContact.getName(), response.getName());
        assertEquals(emergencyContact.getPhone(), response.getPhone());
        assertEquals(emergencyContact.getRelation(), response.getRelation());
        assertEquals(emergencyContact.getPatient().getId(), response.getPatientId());
    }

    @Test
    void findByIdOutForRedis() {
        Long id = 1l;
        EmergencyContact contact = new EmergencyContact();
        contact.setId(id);
        contact.setName("Jhon");
        contact.setPhone("956940085");
        EmergencyContactDto dto = EmergencyContactMapper.fromEntity(contact);

        when(redisService.getFromRedis(anyString())).thenReturn(Util.convertToString(dto));

        Optional<EmergencyContactDto> response = adapter.findByIdOut(id);
        assertTrue(response.isPresent());
        assertEquals(contact.getId(), response.get().getId());
        assertEquals(contact.getName(), response.get().getName());
        assertEquals(contact.getPhone(), response.get().getPhone());
    }
    @Test
    void findByIdOutForBD() {
        Long id = 1l;
        EmergencyContact contact = new EmergencyContact();
        contact.setId(id);
        contact.setName("Jhon ");
        contact.setPhone("73103894");

        when(redisService.getFromRedis(anyString())).thenReturn(null);
        when((emergencyContactRepository.findById(anyLong()))).thenReturn(Optional.of(contact));

        Optional<EmergencyContactDto> response = adapter.findByIdOut(id);
        assertTrue(response.isPresent());
        assertEquals(contact.getId(),response.get().getId());
        assertEquals(contact.getName(),response.get().getName());
        assertEquals(contact.getPhone(),response.get().getPhone());
    }

    @Test
    void getAllOutNoList() {
        when(emergencyContactRepository.findAll()).thenReturn(Collections.emptyList());

        List<EmergencyContactDto> response = adapter.getAllOut();
        assertNotNull(response);
        assertTrue(response.isEmpty());

    }
    @Test
    void getAllOut() {
        EmergencyContact contact = new EmergencyContact();
        contact.setId(1l);
        List<EmergencyContact> list = new ArrayList<>();
        list.add(contact);

        when(emergencyContactRepository.findAll()).thenReturn(list);
        List<EmergencyContactDto> reponse = adapter.getAllOut();
        assertNotNull(reponse);
        assertFalse(reponse.isEmpty());
        assertEquals(contact.getId(), reponse.get(0).getId());
    }

    @Test
    void updateOut() {
        Long id = 1l;
        Patient patient = new Patient();
        patient.setId(1l);

        EmergencyContact contact = new EmergencyContact();
        contact.setName("Jhon");
        contact.setPhone("956940085");
        contact.setRelation("Hijo");
        contact.setPatient(patient);

        EmergencyContactRequest request = EmergencyContactRequest.builder()
                .name("nuevo nombre").phone("12345678").relation("Padre").patientId(patient.getId()).build();

        when(emergencyContactRepository.findById(anyLong())).thenReturn(Optional.of(contact));
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
        EmergencyContact contact1 = adapter.propertyUpdate(contact,request);
        when(emergencyContactRepository.save(any(EmergencyContact.class))).thenReturn(contact1);

        EmergencyContactDto response = adapter.updateOut(id, request);
        assertNotNull(response);
        assertEquals(request.getName(),response.getName());
        assertEquals(request.getPhone(),request.getPhone());
        assertEquals(request.getRelation(),request.getRelation());
        assertNotNull(response.getUpdatedBy());
        assertNotNull(response.getUpdatedOn());
    }

    @Test
    void deleteOut() {
        Long id = 1l;
        EmergencyContact contact = new EmergencyContact();
        contact.setName("Jhon");

        when(emergencyContactRepository.findById(anyLong())).thenReturn(Optional.of(contact));
        when(emergencyContactRepository.save(any(EmergencyContact.class))).thenReturn(adapter.propertyDelete(contact));

        EmergencyContactDto response = adapter.deleteOut(id);
        assertNotNull(response);
        assertEquals(contact.getName(),response.getName());
        assertEquals(response.getStatus(), Constants.STATUS_INACTIVE);
        assertNotNull(response.getDeletedOn());
        assertNotNull(response.getDeletedBy());
    }
}