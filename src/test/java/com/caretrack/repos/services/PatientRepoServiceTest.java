package com.caretrack.repos.services;

import com.caretrack.business.models.PaginatedList;
import com.caretrack.business.models.Patient;
import com.caretrack.business.repos.entities.PatientEntity;
import com.caretrack.business.repos.interfaces.PatientRepository;
import com.caretrack.business.repos.services.PatientRepoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class PatientRepoServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientRepoService patientRepoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPatientsPaginatedShouldReturnPaginatedListWhenNoException() {

        when(
                patientRepository.findAll(any(Pageable.class))
        ).thenReturn(new PageImpl<>(Arrays.asList(
                PatientEntity.builder().id(1L).name("John Doe").age(30).build(),
                PatientEntity.builder().id(2L).name("Jane Doe").age(25).build()
        )));


        PaginatedList<Patient> result = patientRepoService.getPatientsPaginated(0L, 10L, "id", "asc");

        assertEquals(0, result.getPageNumber());
        assertEquals(2, result.getPageSize());
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertNotNull(result.getContent());
        assertEquals(1L, result.getContent().get(0).getId());
        assertEquals(2L, result.getContent().get(1).getId());
        assertEquals(30, result.getContent().get(0).getAge());
        assertEquals(25, result.getContent().get(1).getAge());
    }

    @Test
    void getPatientsPaginatedShouldThrowExceptionWhenRepoServiceThrowsException() {
        when(patientRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> patientRepoService.getPatientsPaginated(0L, 10L, "id", "asc"));
    }
}