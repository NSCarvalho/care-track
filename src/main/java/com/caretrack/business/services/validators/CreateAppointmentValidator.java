package com.caretrack.business.services.validators;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.Appointment;
import com.caretrack.business.models.Doctor;
import com.caretrack.business.models.Patient;
import org.springframework.util.ObjectUtils;

public class CreateAppointmentValidator {

    private CreateAppointmentValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static void applyValidations(Appointment appointment) throws CareTrackException {

        if (ObjectUtils.isEmpty(appointment))
            throw new CareTrackException("Appointment is required");

        if (!ObjectUtils.isEmpty(appointment.getId()))
            throw new CareTrackException("Appointment ID should not be provided");

        if (isDoctorEmpty(appointment.getDoctor()))
            throw new CareTrackException("Doctor data is required");

        if (isPatientEmpty(appointment.getPatient()))
            throw new CareTrackException("Patient data is required");
    }

    private static boolean isDoctorEmpty(Doctor doctor) {
        return ObjectUtils.isEmpty(doctor) ||
                ObjectUtils.isEmpty(doctor.getId());
    }

    private static boolean isPatientEmpty(Patient patient) {
        return ObjectUtils.isEmpty(patient) ||
                ObjectUtils.isEmpty(patient.getId());
    }
}
