package com.caretrack.business.services.interfaces;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.SpecialtyByPatient;

import java.util.List;

public interface SpecialtyService {

    List<SpecialtyByPatient> getSpecialtiesWithAtLeastUniquePatients(Long minPatients) throws CareTrackException;
}
