package com.codigo.clinica.mspaciente.domain.aggregates.constants;

public class Constants {
    private Constants() {
    }

    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_INACTIVE = 0;
    public static final String USU_ADMIN = "QHUAMANI";
    public static final String REDIS_GET_PATIENT = "MS:PATIENT:PATIENT:";
    public static final String REDIS_GET_MEDICAL_REC = "MS:PATIENT:MEDICAL_REC:";
    public static final String REDIS_GET_TEATMENT = "MS:PATIENT:TEATMENT:";
    public static final String REDIS_GET_EMERG_CONTACT = "MS:PATIENT:EMERG_CONTACT:";

}
