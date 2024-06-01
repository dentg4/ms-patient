package com.codigo.clinica.mspaciente.infrastructure.adapters;

import com.codigo.clinica.mspaciente.domain.aggregates.constants.Constants;
import com.codigo.clinica.mspaciente.domain.aggregates.request.TeatmentRequest;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.TeatmentDto;
import com.codigo.clinica.mspaciente.infrastructure.dao.PatientRepository;
import com.codigo.clinica.mspaciente.infrastructure.dao.TeatmentRepository;
import com.codigo.clinica.mspaciente.infrastructure.entity.Patient;
import com.codigo.clinica.mspaciente.infrastructure.entity.Teatment;
import com.codigo.clinica.mspaciente.infrastructure.mapper.TeatmentMapper;
import com.codigo.clinica.mspaciente.infrastructure.redis.RedisService;
import com.codigo.clinica.mspaciente.infrastructure.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TeatmentAdapterTest {

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
    }

    @Mock
    private TeatmentRepository teatmentRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private RedisService redisService;

    @InjectMocks
    private TeatmentAdapter teatmentAdapter;

    @Test
    void createTeatment() {
        //ARRANGE
        Teatment teatment = new Teatment();
        teatment.setDescription("Orthodontics");
        teatment.setCost(5000L);
        teatment.setStartDate(new GregorianCalendar(2024, Calendar.JANUARY, 1).getTime());
        teatment.setEndDate(new GregorianCalendar(2024, Calendar.DECEMBER, 31).getTime());
        teatment.setPatient(new Patient());
        teatment.setStatus(Constants.STATUS_ACTIVE);
        teatment.setCreatedBy(Constants.USU_ADMIN);
        teatment.setCreatedOn(new Timestamp(System.currentTimeMillis()));

        TeatmentRequest request = TeatmentRequest.builder()
                .description("Orthodontics")
                .patientId(1L)
                .build();

        //SIMULATE
        when(teatmentRepository.save(any(Teatment.class))).thenReturn(teatment);
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(new Patient()));

        //ACT
        TeatmentDto teatmentDto = teatmentAdapter.createTeatment(request);

        //ASSERT
        assertNotNull(teatmentDto);
        assertEquals(teatment.getDescription(),teatmentDto.getDescription());
        assertEquals(teatment.getCost(),teatmentDto.getCost());
        assertNotNull(teatmentDto.getStatus());
        assertNotNull(teatmentDto.getCreatedBy());
        assertNotNull(teatmentDto.getCreateOn());
        verify(teatmentRepository).save(any(Teatment.class));
    }

    @Test
    void findByIdOutForRedis() {
        //ARRANGE
        Long id= 1L;
        Teatment teatment = new Teatment();
        teatment.setDescription("Orthodontics");
        teatment.setPatient(new Patient());
        TeatmentDto prescriptionDto = TeatmentMapper.fromEntity(teatment);

        //SIMULATE
        when(redisService.getFromRedis(anyString())).thenReturn(Util.convertToString(prescriptionDto));

        //ACT
        Optional<TeatmentDto> response = teatmentAdapter.findByIdOut(id);

        //ASSERT
        assertNotNull(response);
        assertEquals(prescriptionDto.getDescription(),response.get().getDescription());
    }

    @Test
    void findByIdOutForBD() {
        //ARRANGE
        Long id= 1L;
        Teatment teatment = new Teatment();
        teatment.setDescription("Orthodontics");
        teatment.setPatient(new Patient());

        //SIMULATE
        when(redisService.getFromRedis(anyString())).thenReturn(null);
        when(teatmentRepository.findById(anyLong())).thenReturn(Optional.of(teatment));

        //ACT
        Optional<TeatmentDto> response = teatmentAdapter.findByIdOut(id);

        //ASSERT
        assertNotNull(response);
    }

    @Test
    void findByIdOutNotFound() {
        //ARRANGE
        Long id = 1L;

        //SIMULATE
        when(redisService.getFromRedis(anyString())).thenReturn(null);
        when(teatmentRepository.findById(anyLong())).thenReturn(Optional.empty());

        //ASSERT
        assertThrows(RuntimeException.class, () -> teatmentAdapter.findByIdOut(id));
        verify(redisService).getFromRedis(anyString());
        verify(teatmentRepository).findById(anyLong());
    }

    @Test
    void getAllOutNoList(){
        //SIMULATE
        when(teatmentRepository.findAll()).thenReturn(Collections.emptyList());

        //ACT
        List<TeatmentDto> response = teatmentAdapter.getAllOut();

        //ASSERT
        assertNotNull(response);
        assertTrue(response.isEmpty());
        verify(teatmentRepository).findAll();
    }

    @Test
    void getAllOut() {
        //ARRANGE
        Teatment teatment = new Teatment();
        teatment.setDescription("Orthodontics");
        teatment.setPatient(new Patient());

        Teatment newTeatment = new Teatment();
        newTeatment.setDescription("Teeth whitening");
        newTeatment.setPatient(new Patient());

        List<Teatment> teatmentList = new ArrayList<>();
        teatmentList.add(teatment);
        teatmentList.add(newTeatment);

        //SIMULATE
        when(teatmentRepository.findAll()).thenReturn(teatmentList);

        //ACT
        List<TeatmentDto> teatmentListDto = teatmentList.stream().map(TeatmentMapper::fromEntity).toList();
        List<TeatmentDto> response = teatmentAdapter.getAllOut();

        //ASSERT
        assertNotNull(response);
        assertEquals(teatmentListDto.size(),response.size());
    }

    @Test
    void updateOut() {
        //ARRANGE
        Long id = 1L;
        Teatment teatment = new Teatment();
        teatment.setDescription("Orthodontics");
        teatment.setPatient(new Patient());

        TeatmentRequest request = TeatmentRequest.builder()
                .description("Teeth whitening")
                .patientId(1L)
                .build();

        //SIMULATE
        when(teatmentRepository.findById(anyLong())).thenReturn(Optional.of(teatment));
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(new Patient()));
        when(teatmentRepository.save(any(Teatment.class))).thenReturn(teatment);

        //ACT
        TeatmentDto teatmentDto = teatmentAdapter.updateOut(id, request);

        //ASSERT
        assertNotNull(teatmentDto);
        assertEquals(request.getDescription(), teatmentDto.getDescription());
    }

    @Test
    void deleteOut() {
        //ARRANGE
        Long id = 1L;
        Teatment teatment = new Teatment();
        teatment.setId(id);
        teatment.setDescription("Orthodontics");
        teatment.setPatient(new Patient());
        teatment.setStatus(Constants.STATUS_INACTIVE);

        //SIMULATE
        when(teatmentRepository.findById(anyLong())).thenReturn(Optional.of(teatment));
        when(teatmentRepository.save(any(Teatment.class))).thenReturn(teatment);

        //ACT
        TeatmentDto teatmentDto= teatmentAdapter.deleteOut(id);

        //ASSERT
        assertNotNull(teatmentDto);
        assertEquals(id, teatmentDto.getId());
        assertEquals(teatment.getDescription(), teatmentDto.getDescription());
        assertEquals(Constants.STATUS_INACTIVE, teatmentDto.getStatus());
    }
}