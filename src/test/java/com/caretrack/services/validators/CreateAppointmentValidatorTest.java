package com.caretrack.services.validators;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.Appointment;
import com.caretrack.business.models.Doctor;
import com.caretrack.business.models.Patient;
import com.caretrack.business.services.validators.CreateAppointmentValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateAppointmentValidatorTest {

    @Test
    void applyValidationsShouldThrowExceptionWhenAppointmentIsNull() {
        Appointment appointment = null;

        assertThrows(CareTrackException.class, () -> CreateAppointmentValidator.applyValidations(appointment));
    }

    @Test
    void applyValidationsShouldThrowExceptionWhenAppointmentIdIsNotNull() {
        Appointment appointment = Appointment
                .builder()
                .build();
        appointment.setId(1L);

        assertThrows(CareTrackException.class, () -> CreateAppointmentValidator.applyValidations(appointment));
    }

    @Test
    void applyValidationsShouldThrowExceptionWhenDoctorDataIsNull() {
        Appointment appointment = Appointment
                .builder()
                .doctor(null)
                .build();

        assertThrows(CareTrackException.class, () -> CreateAppointmentValidator.applyValidations(appointment));
    }

    @Test
    void applyValidationsShouldThrowExceptionWhenDoctorIdIsNull() {
        Appointment appointment = Appointment
                .builder()
                .doctor(Doctor
                        .builder()
                        .id(null)
                        .build())
                .build();

        assertThrows(CareTrackException.class, () -> CreateAppointmentValidator.applyValidations(appointment));
    }

    @Test
    void applyValidationsShouldThrowExceptionWhenPatientDataIsNull() {
        Appointment appointment = Appointment
                .builder()
                .patient(null)
                .build();

        assertThrows(CareTrackException.class, () -> CreateAppointmentValidator.applyValidations(appointment));
    }

    @Test
    void applyValidationsShouldThrowExceptionWhenPatientIdIsNull() {
        Appointment appointment = Appointment
                .builder()
                .doctor(Doctor
                        .builder()
                        .id(1L)
                        .build())
                .patient(Patient
                        .builder()
                        .id(null)
                        .build())
                .build();

        assertThrows(CareTrackException.class, () -> CreateAppointmentValidator.applyValidations(appointment));
    }

    @Test
    void applyValidationsShouldNotThrowExceptionWhenValidAppointment() {
        Appointment appointment = Appointment
                .builder()
                .doctor(Doctor
                        .builder()
                        .id(1L)
                        .build())
                .patient(Patient
                        .builder()
                        .id(1L)
                        .build())
                .build();

        assertDoesNotThrow(() -> CreateAppointmentValidator.applyValidations(appointment));
    }

}
