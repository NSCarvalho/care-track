package com.caretrack.business.repos.services;

import com.caretrack.business.models.PaginatedList;
import com.caretrack.business.models.Patient;
import com.caretrack.business.repos.entities.PatientEntity;
import com.caretrack.business.repos.interfaces.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PatientRepoService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientRepoService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    private static PaginatedList<Patient> convertEntityToModel(Page<PatientEntity> patientEntity) {
        List<Patient> patients = new LinkedList<>();
        patientEntity.forEach(patient -> patients.add(Patient
                .builder()
                .id(patient.getId())
                .name(patient.getName())
                .age(patient.getAge())
                .build()));

        return new PaginatedList<>(patients, patientEntity.getNumber(),
                patientEntity.getSize(), patientEntity.getTotalElements(), patientEntity.getTotalPages());
    }

    public PaginatedList<Patient> getPatientsPaginated(Long page, Long size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page.intValue(), size.intValue(), sort);
        return convertEntityToModel(patientRepository.findAll(pageable));
    }
}
