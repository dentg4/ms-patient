package com.codigo.clinica.mspaciente.infraestructure.adapters;

import com.codigo.clinica.mspaciente.domain.aggregates.constants.Constants;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDTO;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;
import com.codigo.clinica.mspaciente.domain.ports.out.EmergencyContactServiceOut;
import com.codigo.clinica.mspaciente.infraestructure.dao.EmergencyContactRepository;
import com.codigo.clinica.mspaciente.infraestructure.dao.PatientRepository;
import com.codigo.clinica.mspaciente.infraestructure.entity.EmergencyContact;
import com.codigo.clinica.mspaciente.infraestructure.entity.Patient;
import com.codigo.clinica.mspaciente.infraestructure.mapper.EmergencyContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmergencyContactAdapter implements EmergencyContactServiceOut {

    private final EmergencyContactRepository emergencyContactRepository;
    private final PatientRepository patientRepository;
    @Override
    public EmergencyContactDTO crearEmergencyContactOut(EmergencyContactRequest request) {
        EmergencyContact emergencyContacts=getEmergencyContactCreate(request);

        return EmergencyContactMapper.fromEntity(emergencyContactRepository.save(emergencyContacts));
    }

    @Override
    public Optional<EmergencyContactDTO> buscarPorIdOut(Long id) {
        EmergencyContact emergencyContact=emergencyContactRepository.findById(id).orElseThrow(()-> new RuntimeException("Contacto de emergencia no encontrado."));
        return Optional.of(EmergencyContactMapper.fromEntity(emergencyContact));
    }

    @Override
    public List<EmergencyContactDTO> obtenerTodosOut() {
        return null;
    }

    @Override
    public EmergencyContactDTO actualizarOut(Long id, EmergencyContactRequest request) {
        return null;
    }

    @Override
    public EmergencyContactDTO deleteOut(Long id) {
        return null;
    }

    private EmergencyContact getEmergencyContactCreate(EmergencyContactRequest request){
        EmergencyContact entity= new EmergencyContact();
        getEntity(entity, request);
        entity.setStatus(Constants.STATUS_ACTIVE);
        entity.setCreatedBy(Constants.USU_ADMIN);
        entity.setCreatedOn(getTimestamp());
        return entity;
    }
    private void getEntity(EmergencyContact entity,EmergencyContactRequest request) {

        Patient patient = patientRepository.findById(request.getId_patient()).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        entity.setName(request.getName());
        entity.setPhone(request.getPhone());
        entity.setRelation(request.getRelation());
        entity.setPatient(patient);

    }
    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }
}
