package com.caretrack.services.impl;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.SpecialtyByPatient;
import com.caretrack.business.repos.services.SpecialtyRepoService;
import com.caretrack.business.services.impl.SpecialtyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpecialtyServiceImplTest {

    @Mock
    private SpecialtyRepoService specialtyRepoService;

    @InjectMocks
    private SpecialtyServiceImpl specialtyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSpecialtiesWithAtLeastUniquePatientsShouldReturnSpecialtyListWhenNoException() throws CareTrackException {
        List<SpecialtyByPatient> specialties = Collections.emptyList();
        when(specialtyRepoService.getSpecialtiesWithAtLeastUniquePatients(anyLong())).thenReturn(specialties);

        List<SpecialtyByPatient> result = specialtyService.getSpecialtiesWithAtLeastUniquePatients(1L);

        assertEquals(specialties, result);
    }

    @Test
    void getSpecialtiesWithAtLeastUniquePatientsShouldThrowCareTrackExceptionWhenMinPatientsIsLessThanOne() {
        assertThrows(CareTrackException.class, () -> specialtyService.getSpecialtiesWithAtLeastUniquePatients(0L));
    }

    @Test
    void getSpecialtiesWithAtLeastUniquePatientsShouldThrowCareTrackExceptionWhenRepoServiceThrowsException() {
        when(specialtyRepoService.getSpecialtiesWithAtLeastUniquePatients(anyLong())).thenThrow(new RuntimeException());

        assertThrows(CareTrackException.class, () -> specialtyService.getSpecialtiesWithAtLeastUniquePatients(1L));
    }
}