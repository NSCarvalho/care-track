package com.caretrack.business.services.validators;

import com.caretrack.business.exceptions.CareTrackException;

public class AppointmentByPatientValidator {

    private AppointmentByPatientValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static void applyValidations(Integer patientId) throws CareTrackException {

        if (patientId == null)
            throw new CareTrackException("Patient ID is required");
    }
}
