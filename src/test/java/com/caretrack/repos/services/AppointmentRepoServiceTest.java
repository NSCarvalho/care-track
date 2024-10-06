package com.caretrack.repos.services;
import com.caretrack.business.models.*;
import com.caretrack.business.repos.entities.AppointmentEntity;
import com.caretrack.business.repos.entities.DoctorEntity;
import com.caretrack.business.repos.entities.PathologyEntity;
import com.caretrack.business.repos.entities.PatientEntity;
import com.caretrack.business.repos.interfaces.*;
import com.caretrack.business.repos.services.AppointmentRepoService;
import com.caretrack.business.repos.services.SpecialtyRepoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentRepoServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private PathologyRepository pathologyRepository;
    @Mock
    private SpecialtyRepoService specialtyRepoService;
    @InjectMocks
    private AppointmentRepoService appointmentRepoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAppointmentsByPatientShouldReturnAppointmentListWhenNoException() {
        when(appointmentRepository.findByPatientId(anyLong())).thenReturn(Arrays.asList(AppointmentEntity
                .builder()
                .doctorId(1L)
                .patientId(1L)
                .id(1L)
                .build()));

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setId(1L);
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(1L);

        when(doctorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(doctorEntity));
        when(patientRepository.findById(anyLong())).thenReturn(java.util.Optional.of(patientEntity));

        List<Appointment> result = appointmentRepoService.getAppointmentsByPatient(1L, false);

        assertNotNull(result);
        assertEquals(result.get(0).getId(), 1L);
        assertEquals( result.get(0).getDoctor().getId(), 1L);
        assertEquals( result.get(0).getPatient().getId(), 1L);
    }

    @Test
    void getAppointmentsByPatientShouldThrowExceptionWhenPatientIdIsNull() {
        when(appointmentRepository.findByPatientId(any(Long.class))).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> appointmentRepoService.getAppointmentsByPatient(10L, false));
    }

    @Test
    void saveShouldReturnAppointmentWhenNoException() {
        Appointment appointment = Appointment
                .builder()
                .doctor(Doctor
                        .builder()
                        .id(1L)
                        .build())
                .patient(Patient
                        .builder()
                        .id(1L)
                        .build())
                .pathology(Pathology
                        .builder()
                        .id(1L)
                        .build())
                .build();

        var doctorEntity = new DoctorEntity();
        doctorEntity.setId(1L);
        var patientEntity = new PatientEntity();
        patientEntity.setId(1L);
        var pathologyEntity = new PathologyEntity();
        pathologyEntity.setId(1L);

        when(doctorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(doctorEntity));
        when(patientRepository.findById(anyLong())).thenReturn(java.util.Optional.of(patientEntity));
        when(pathologyRepository.findById(anyLong())).thenReturn(java.util.Optional.of(pathologyEntity));
        when(appointmentRepository.save(any(AppointmentEntity.class))).thenReturn(new AppointmentEntity());
        when(specialtyRepoService.getSpecialtyById(any(Long.class))).thenReturn(Specialty.builder().build());

        assertDoesNotThrow(() -> appointmentRepoService.save(appointment));
    }

    @Test
    void saveShouldThrowExceptionWhenDoctorIdIsNull() {
        Appointment appointment = Appointment
                .builder()
                .doctor(Doctor
                        .builder()
                        .build())
                .patient(Patient
                        .builder()
                        .id(1L)
                        .build())
                .build();

        when(doctorRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class, () -> appointmentRepoService.save(appointment));
    }

    @Test
    void saveShouldThrowExceptionWhenPatientIdIsNull() {
        Appointment appointment = Appointment
                .builder()
                .doctor(Doctor
                        .builder()
                        .id(1L)
                        .build())
                .patient(Patient
                        .builder()
                        .build())
                .build();

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setId(1L);

        when(doctorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(doctorEntity));
        when(patientRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class, () -> appointmentRepoService.save(appointment));
    }
}