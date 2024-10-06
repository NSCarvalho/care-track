package com.caretrack.services.impl;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.Appointment;
import com.caretrack.business.models.PaginatedList;
import com.caretrack.business.models.Patient;
import com.caretrack.business.repos.services.AppointmentRepoService;
import com.caretrack.business.repos.services.PatientRepoService;
import com.caretrack.business.services.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PatientServiceImplTest {

    @Mock
    private AppointmentRepoService appointmentRepoService;

    @Mock
    private PatientRepoService patientRepoService;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPatientListShouldReturnPaginatedListWhenNoException() throws CareTrackException {
        PaginatedList<Patient> patientList = new PaginatedList<>(Collections.emptyList(), 0, 0, 0, 0);
        when(patientRepoService.getPatientsPaginated(anyLong(), anyLong(), anyString(), anyString())).thenReturn(patientList);

        PaginatedList<Patient> result = patientService.getPatientList(0L, 10L, "id", "asc");

        assertEquals(patientList, result);
    }

    @Test
    void getPatientListShouldThrowCareTrackExceptionWhenRepoServiceThrowsException() {
        when(patientRepoService.getPatientsPaginated(anyLong(), anyLong(), anyString(), anyString())).thenThrow(new RuntimeException());

        assertThrows(CareTrackException.class, () -> patientService.getPatientList(0L, 10L, "id", "asc"));
    }

    @Test
    void getAppointmentByPatientShouldReturnAppointmentListWhenNoException() throws CareTrackException {
        List<Appointment> appointments = Collections.emptyList();
        when(appointmentRepoService.getAppointmentsByPatient(anyLong(), anyBoolean())).thenReturn(appointments);

        List<Appointment> result = patientService.getAppointmentByPatient(1, false);

        assertEquals(appointments, result);
    }

    @Test
    void getAppointmentByPatientShouldThrowCareTrackExceptionWhenValidatorThrowsException() {

        assertThrows(CareTrackException.class, () -> patientService.getAppointmentByPatient(null, false));
    }

    @Test
    void getAppointmentByPatientShouldThrowCareTrackExceptionWhenRepoServiceThrowsException() {
        when(appointmentRepoService.getAppointmentsByPatient(anyLong(), anyBoolean())).thenThrow(new RuntimeException());

        assertThrows(CareTrackException.class, () -> patientService.getAppointmentByPatient(1, false));
    }
}
