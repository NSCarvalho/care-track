package com.caretrack.repos.services;

import com.caretrack.business.models.Specialty;
import com.caretrack.business.models.SpecialtyByPatient;
import com.caretrack.business.repos.entities.SpecialtyEntity;
import com.caretrack.business.repos.entities.SpecialtyPatientCount;
import com.caretrack.business.repos.interfaces.SpecialtyRepository;
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
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

class SpecialtyRepoServiceTest {

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialtyRepoService specialtyRepoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSpecialtiesWithAtLeastUniquePatientsShouldReturnSpecialtyListWhenNoException() {
        List<SpecialtyPatientCount> specialties = Arrays.asList(SpecialtyPatientCount
                .builder()
                .specialtyName("Test")
                .numberOfPatients(1L)
                .build());
        when(specialtyRepository.getSpecialtiesWithAtLeastUniquePatients(anyLong())).thenReturn(specialties);

        List<SpecialtyByPatient> result = specialtyRepoService.getSpecialtiesWithAtLeastUniquePatients(1L);

        assertNotNull(result);
        assertEquals(specialties.size(), result.size());
        assertEquals(specialties.get(0).getSpecialtyName(), result.get(0).getSpecialtyName());
        assertEquals(specialties.get(0).getNumberOfPatients(), result.get(0).getNumberOfPatients());
    }

    @Test
    void getSpecialtiesWithAtLeastUniquePatientsShouldReturnEmptyListWhenRepoServiceReturnsNull() {
        when(specialtyRepository.getSpecialtiesWithAtLeastUniquePatients(anyLong())).thenReturn(null);

        List<SpecialtyByPatient> result = specialtyRepoService.getSpecialtiesWithAtLeastUniquePatients(1L);

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void getSpecialtyByIdShouldReturnSpecialtyWhenNoException() {
        Specialty specialty = Specialty.builder().id(1L).name("Test").build();
        SpecialtyEntity specialtyEntity = SpecialtyEntity.builder().id(1L).name("Test").build();
        when(specialtyRepository.findById(anyLong())).thenReturn(java.util.Optional.of(specialtyEntity));

        Specialty result = specialtyRepoService.getSpecialtyById(1L);

        assertNotNull(result);
        assertEquals(specialty.getId(), result.getId());
        assertEquals(specialty.getName(), result.getName());
    }

    @Test
    void getSpecialtyByIdShouldReturnNullWhenRepoServiceReturnsNull() {
        when(specialtyRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        Specialty result = specialtyRepoService.getSpecialtyById(1L);

        assertNull(result);
    }
}
