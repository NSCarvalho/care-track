package com.caretrack.services.validators;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.services.validators.AppointmentByPatientValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentByPatientValidatorTest {

    @Test
    void applyValidationsShouldThrowExceptionWhenPatientIdIsNull() {
        Integer patientId = null;

        assertThrows(CareTrackException.class, () -> AppointmentByPatientValidator.applyValidations(patientId));
    }

    @Test
    void applyValidationsShouldNotThrowExceptionWhenPatientIdIsNotNull() {
        Integer patientId = 1;

        assertDoesNotThrow(() -> AppointmentByPatientValidator.applyValidations(patientId));
    }
}
