package com.caretrack.business.repos.services;

import com.caretrack.business.models.Specialty;
import com.caretrack.business.models.SpecialtyByPatient;
import com.caretrack.business.repos.interfaces.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class SpecialtyRepoService {

    private final SpecialtyRepository symptomRepository;

    @Autowired
    public SpecialtyRepoService(SpecialtyRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    public List<SpecialtyByPatient> getSpecialtiesWithAtLeastUniquePatients(Long minPatients) {
        var specialties = symptomRepository.getSpecialtiesWithAtLeastUniquePatients(minPatients);
        if (CollectionUtils.isEmpty(specialties)) {
            return Collections.emptyList();
        }

        return symptomRepository.getSpecialtiesWithAtLeastUniquePatients(minPatients)
                .stream()
                .map(entity -> SpecialtyByPatient
                        .builder()
                        .specialtyName(entity.getSpecialtyName())
                        .numberOfPatients(entity.getNumberOfPatients())
                        .build())
                .toList();
    }

    public Specialty getSpecialtyById(Long id) {
        return symptomRepository.findById(id)
                .map(entity -> Specialty
                        .builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .build()).orElse(null);
    }
}
