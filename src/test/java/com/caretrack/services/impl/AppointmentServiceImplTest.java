package com.caretrack.services.impl;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.Appointment;
import com.caretrack.business.models.Doctor;
import com.caretrack.business.models.Patient;
import com.caretrack.business.repos.services.AppointmentRepoService;
import com.caretrack.business.services.impl.AppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepoService appointmentRepoService;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAppointmentShouldReturnAppointmentWhenNoException() throws CareTrackException {
        Appointment appointment = getAppointment();
        when(appointmentRepoService.save(any(Appointment.class))).thenReturn(appointment);

        Appointment result = appointmentService.createAppointment(appointment);

        assertEquals(appointment, result);
    }

    @Test
    void createAppointmentShouldThrowCareTrackExceptionWhenValidationFails() {
        Appointment appointment = Appointment.builder().build();

        assertThrows(CareTrackException.class, () -> appointmentService.createAppointment(appointment));
    }

    @Test
    void createAppointmentShouldThrowCareTrackExceptionWhenRepoServiceThrowsException() {
        Appointment appointment = getAppointment();
        when(appointmentRepoService.save(any(Appointment.class))).thenThrow(new RuntimeException());

        assertThrows(CareTrackException.class, () -> appointmentService.createAppointment(appointment));
    }

    private static Appointment getAppointment() {
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
                .build();
        return appointment;
    }
}