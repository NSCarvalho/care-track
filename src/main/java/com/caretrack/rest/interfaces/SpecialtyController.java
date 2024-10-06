package com.caretrack.rest.interfaces;

import com.caretrack.business.models.SpecialtyByPatient;
import com.caretrack.rest.utils.RestResponse;

import java.util.List;

public interface SpecialtyController {

    RestResponse<List<SpecialtyByPatient>> getSpecialtiesWithAtLeastUniquePatients(Long minPatients);
}
