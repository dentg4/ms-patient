package com.codigo.clinica.mspaciente.infrastructure.adapters;

import com.codigo.clinica.mspaciente.domain.aggregates.constants.Constants;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.DoctorDto;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.MedicalRecordDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.MedicalRecordRequest;
import com.codigo.clinica.mspaciente.infrastructure.dao.MedicalRecordRepository;
import com.codigo.clinica.mspaciente.infrastructure.dao.PatientRepository;
import com.codigo.clinica.mspaciente.infrastructure.client.ClientMsStaff;
import com.codigo.clinica.mspaciente.infrastructure.entity.MedicalRecord;
import com.codigo.clinica.mspaciente.infrastructure.entity.Patient;
import com.codigo.clinica.mspaciente.infrastructure.mapper.MedicalRecordMapper;
import com.codigo.clinica.mspaciente.infrastructure.redis.RedisService;
import com.codigo.clinica.mspaciente.infrastructure.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MedicalRecordAdapterTest {

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
    }

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ClientMsStaff clientMsStaff;

    @Mock
    private RedisService redisService;

    @InjectMocks
    private MedicalRecordAdapter medicalRecordAdapter;

    @Test
    void createMedicalRecordOut() {
        //ARRANGE
        MedicalRecord record = new MedicalRecord();
        record.setDiagnos("Tooth sensitivity");
        record.setPatient(new Patient());
        record.setStatus(Constants.STATUS_ACTIVE);
        record.setCreatedBy(Constants.USU_ADMIN);
        record.setCreatedOn(new Timestamp(System.currentTimeMillis()));

        MedicalRecordRequest request = MedicalRecordRequest.builder()
                .diagnos("Tooth sensitivity")
                .patientId(1L)
                .doctorId(1L)
                .build();

        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(record);
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(new Patient()));
        when(clientMsStaff.getDoctorById(anyLong())).thenReturn(ResponseEntity.ok(new DoctorDto()));

        //ACT
        MedicalRecordDto medicalRecordDto = medicalRecordAdapter.createMedicalRecordOut(request);

        //ASSERT
        assertNotNull(medicalRecordDto);
        assertEquals(record.getDiagnos(), medicalRecordDto.getDiagnos());
        assertNotNull(medicalRecordDto.getStatus());
        assertNotNull(medicalRecordDto.getCreatedBy());
        assertNotNull(medicalRecordDto.getCreateOn());
        verify(medicalRecordRepository).save(any(MedicalRecord.class));
    }

    @Test
    void findByIdOutForRedis() {
        //ARRANGE
        Long id= 1L;
        MedicalRecord record = new MedicalRecord();
        record.setDiagnos("Tooth sensitivity");
        record.setPatient(new Patient());

        MedicalRecordDto medicalRecordDto = MedicalRecordMapper.fromEntity(record);

        //SIMULATE
        when(redisService.getFromRedis(anyString())).thenReturn(Util.convertToString(medicalRecordDto));

        //ACT
        Optional<MedicalRecordDto> response = medicalRecordAdapter.findByIdOut(id);

        //ASSERT
        assertNotNull(response);
        assertEquals(medicalRecordDto.getDiagnos(),
                response.orElseThrow(() -> new RuntimeException("Record not found")).getDiagnos());
    }

    @Test
    void findByIdOutForBD() {
        //ARRANGE
        Long id = 1L;
        MedicalRecord record = new MedicalRecord();
        record.setDiagnos("Tooth sensitivity");
        record.setPatient(new Patient());
        record.setDoctorId(1L);

        DoctorDto doctorDto = DoctorDto.builder().id(record.getDoctorId()).build();

        //SIMULATE
        when(redisService.getFromRedis(anyString())).thenReturn(null);
        when(medicalRecordRepository.findById(anyLong())).thenReturn(Optional.of(record));
        when(clientMsStaff.getDoctorById(anyLong())).thenReturn(ResponseEntity.ok(doctorDto));

        //ACT
        Optional<MedicalRecordDto> response = medicalRecordAdapter.findByIdOut(id);

        //ASSERT
        assertNotNull(response);
    }

    @Test
    void findByIdOutNotFound() {
        //ARRANGE
        Long id = 1L;

        //SIMULATE
        when(redisService.getFromRedis(anyString())).thenReturn(null);
        when(medicalRecordRepository.findById(anyLong())).thenReturn(Optional.empty());

        //ASSERT
        assertThrows(RuntimeException.class, () -> medicalRecordAdapter.findByIdOut(id));
        verify(redisService).getFromRedis(anyString());
        verify(medicalRecordRepository).findById(anyLong());
    }

    @Test
    void getAllOutNoList(){
        //SIMULATE
        when(medicalRecordRepository.findAll()).thenReturn(Collections.emptyList());

        //ACT
        List<MedicalRecordDto> response = medicalRecordAdapter.getAllOut();

        //ASSERT
        assertNotNull(response);
        assertTrue(response.isEmpty());
        verify(medicalRecordRepository).findAll();
    }

    @Test
    void getAllOut() {
        //ARRANGE
        MedicalRecord record = new MedicalRecord();
        record.setDiagnos("Tooth sensitivity");
        record.setPatient(new Patient());

        MedicalRecord newRecord = new MedicalRecord();
        newRecord.setDiagnos("Dental caries");
        newRecord.setPatient(new Patient());

        List<MedicalRecord> recordList = new ArrayList<>();
        recordList.add(record);
        recordList.add(newRecord);

        //SIMULATE
        when(medicalRecordRepository.findAll()).thenReturn(recordList);

        //ACT
        List<MedicalRecordDto> recordListDto = recordList.stream().map(MedicalRecordMapper::fromEntity).toList();
        List<MedicalRecordDto> response = medicalRecordAdapter.getAllOut();

        //ASSERT
        assertNotNull(response);
        assertEquals(recordListDto.size(),response.size());
    }

    @Test
    void updateOut() {
        //ARRANGE
        Long id = 1L;
        MedicalRecord record = new MedicalRecord();
        record.setDiagnos("Tooth sensitivity");
        record.setPatient(new Patient());
        record.setDoctorId(1L);

        MedicalRecordRequest request = MedicalRecordRequest.builder()
                .diagnos("Dental caries")
                .patientId(1L)
                .doctorId(1L)
                .build();

        DoctorDto doctorDto = DoctorDto.builder().id(record.getDoctorId()).build();

        //SIMULATE
        when(medicalRecordRepository.findById(anyLong())).thenReturn(Optional.of(record));
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(new Patient()));
        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(record);
        when(clientMsStaff.getDoctorById(anyLong())).thenReturn(ResponseEntity.ok(doctorDto));

        //ACT
        MedicalRecordDto recordDto = medicalRecordAdapter.updateOut(id, request);

        //ASSERT
        assertNotNull(recordDto);
        assertEquals(request.getDiagnos(), recordDto.getDiagnos());
    }

    @Test
    void deleteOut() {
        //ARRANGE
        Long id = 1L;
        MedicalRecord record = new MedicalRecord();
        record.setId(id);
        record.setDiagnos("Tooth sensitivity");
        record.setPatient(new Patient());
        record.setStatus(Constants.STATUS_INACTIVE);

        //SIMULATE
        when(medicalRecordRepository.findById(anyLong())).thenReturn(Optional.of(record));
        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(record);

        //ACT
        MedicalRecordDto recordDto = medicalRecordAdapter.deleteOut(id);

        //ASSERT
        assertNotNull(recordDto);
        assertEquals(id, recordDto.getId());
        assertEquals(record.getDiagnos(), recordDto.getDiagnos());
        assertEquals(Constants.STATUS_INACTIVE, recordDto.getStatus());
    }
}