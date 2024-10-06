package com.caretrack.business.services.impl;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.SpecialtyByPatient;
import com.caretrack.business.repos.services.SpecialtyRepoService;
import com.caretrack.business.services.interfaces.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    private static final long MIN_PATIENTS_DEFAULT = 1L;
    private final SpecialtyRepoService specialtyRepoService;

    @Autowired
    public SpecialtyServiceImpl(SpecialtyRepoService specialtyRepoService) {
        this.specialtyRepoService = specialtyRepoService;
    }

    private static void validateMinPatients(Long minPatientsOrDefault) throws CareTrackException {
        if (minPatientsOrDefault < 1) {
            throw new CareTrackException("Minimum number of patients must be at least 1");
        }
    }

    private static Long getMinPatientsOrDefault(Long minPatients) {
        var minPatientsOrDefault = minPatients == null ? Long.valueOf(MIN_PATIENTS_DEFAULT) : minPatients;
        return minPatientsOrDefault;
    }

    @Override
    public List<SpecialtyByPatient> getSpecialtiesWithAtLeastUniquePatients(Long minPatients) throws CareTrackException {
        try {
            Long minPatientsOrDefault = getMinPatientsOrDefault(minPatients);
            validateMinPatients(minPatientsOrDefault);
            return specialtyRepoService.getSpecialtiesWithAtLeastUniquePatients(minPatientsOrDefault);
        } catch (CareTrackException e) {
            throw e;
        } catch (Exception e) {
            throw new CareTrackException("Error occurred while retrieving specialties");
        }
    }
}
