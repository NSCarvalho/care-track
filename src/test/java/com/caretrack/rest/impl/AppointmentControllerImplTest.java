package com.caretrack.rest.impl;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.Appointment;
import com.caretrack.business.services.interfaces.AppointmentService;
import com.caretrack.rest.utils.RestResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class AppointmentControllerImplTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentControllerImpl appointmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAppointmentShouldReturnSuccessResponseWhenNoException() throws CareTrackException {
        Appointment appointment = Appointment
                .builder()
                .build();
        when(appointmentService.createAppointment(any(Appointment.class))).thenReturn(appointment);

        RestResponse<Appointment> response = appointmentController.createAppointment(appointment);

        assertTrue(response.isSuccess());
        assertEquals("Appointment created successfully", response.getMessage());
        assertEquals(appointment, response.getData());
    }

    @Test
    void createAppointmentShouldReturnErrorResponseWhenExceptionOccurs() throws CareTrackException {
        when(appointmentService.createAppointment(any(Appointment.class))).thenThrow(new CareTrackException("Error occurred"));

        RestResponse<Appointment> response = appointmentController.createAppointment(Appointment
                .builder()
                .build());

        assertFalse(response.isSuccess());
        assertEquals("Error occurred", response.getMessage());
        assertNull(response.getData());
    }
}