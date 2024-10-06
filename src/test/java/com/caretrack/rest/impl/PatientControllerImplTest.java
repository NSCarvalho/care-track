package com.caretrack.rest.impl;
import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.Appointment;
import com.caretrack.business.models.PaginatedList;
import com.caretrack.business.models.Patient;
import com.caretrack.business.services.interfaces.PatientService;
import com.caretrack.rest.utils.RestResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientControllerImplTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientControllerImpl patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPatientListShouldReturnSuccessResponseWhenNoException() throws CareTrackException {
        PaginatedList<Patient> patientList = new PaginatedList<>(Collections.emptyList(),
                0, 0, 0, 0);
        when(patientService.getPatientList(anyLong(), anyLong(), anyString(), anyString())).thenReturn(patientList);

        RestResponse<PaginatedList<Patient>> response = patientController.getPatientList(0L, 10L, "id", "asc");

        assertTrue(response.isSuccess());
        assertEquals("Patient list retrieved successfully", response.getMessage());
        assertEquals(patientList, response.getData());
    }

    @Test
    void getPatientListShouldReturnErrorResponseWhenExceptionOccurs() throws CareTrackException {
        when(patientService.getPatientList(anyLong(), anyLong(), anyString(), anyString())).thenThrow(new CareTrackException("Error occurred"));

        RestResponse<PaginatedList<Patient>> response = patientController.getPatientList(0L, 10L, "id", "asc");

        assertFalse(response.isSuccess());
        assertEquals("Error occurred", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    void getAppointmentByPatientShouldReturnSuccessResponseWhenNoException() throws CareTrackException {
        List<Appointment> appointments = Collections.emptyList();
        when(patientService.getAppointmentByPatient(anyInt(), anyBoolean())).thenReturn(appointments);

        RestResponse<List<Appointment>> response = patientController.getAppointmentByPatient(1, false);

        assertTrue(response.isSuccess());
        assertEquals("Appointment list retrieved successfully", response.getMessage());
        assertEquals(appointments, response.getData());
    }

    @Test
    void getAppointmentByPatientShouldReturnErrorResponseWhenExceptionOccurs() throws CareTrackException {
        when(patientService.getAppointmentByPatient(anyInt(), anyBoolean())).thenThrow(new CareTrackException("Error occurred"));

        RestResponse<List<Appointment>> response = patientController.getAppointmentByPatient(1, false);

        assertFalse(response.isSuccess());
        assertEquals("Error occurred", response.getMessage());
        assertNull(response.getData());
    }
}