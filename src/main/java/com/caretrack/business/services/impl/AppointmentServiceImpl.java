package com.caretrack.business.services.impl;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.Appointment;
import com.caretrack.business.repos.services.AppointmentRepoService;
import com.caretrack.business.services.interfaces.AppointmentService;
import com.caretrack.business.services.validators.CreateAppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepoService appointmentRepoService;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepoService appointmentRepoService) {
        this.appointmentRepoService = appointmentRepoService;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) throws CareTrackException {

        try {
            CreateAppointmentValidator.applyValidations(appointment);
            return appointmentRepoService.save(appointment);
        } catch (CareTrackException e) {
            throw e;
        } catch (Exception e) {
            throw new CareTrackException("Error occurred while creating appointment");
        }
    }
}
