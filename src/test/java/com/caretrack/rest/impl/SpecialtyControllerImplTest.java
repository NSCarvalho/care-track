package com.caretrack.rest.impl;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.SpecialtyByPatient;
import com.caretrack.business.services.interfaces.SpecialtyService;
import com.caretrack.rest.utils.RestResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

class SpecialtyControllerImplTest {

    @Mock
    private SpecialtyService specialtyService;

    @InjectMocks
    private SpecialtyControllerImpl specialtyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSpecialtiesWithAtLeastUniquePatientsShouldReturnSuccessResponseWhenNoException() throws CareTrackException {
        List<SpecialtyByPatient> specialties = Collections.emptyList();
        when(specialtyService.getSpecialtiesWithAtLeastUniquePatients(anyLong())).thenReturn(specialties);

        RestResponse<List<SpecialtyByPatient>> response = specialtyController
                .getSpecialtiesWithAtLeastUniquePatients(1L);

        assertTrue(response.isSuccess());
        assertEquals("Specialty list retrieved successfully", response.getMessage());
        assertEquals(specialties, response.getData());
    }

    @Test
    void getSpecialtiesWithAtLeastUniquePatientsShouldReturnErrorResponseWhenExceptionOccurs() throws CareTrackException {
        when(specialtyService.getSpecialtiesWithAtLeastUniquePatients(anyLong()))
                .thenThrow(new CareTrackException("Error occurred"));

        RestResponse<List<SpecialtyByPatient>> response = specialtyController
                .getSpecialtiesWithAtLeastUniquePatients(1L);

        assertFalse(response.isSuccess());
        assertEquals("Error occurred", response.getMessage());
        assertNull(response.getData());
    }
}