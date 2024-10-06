package com.caretrack.business.services.interfaces;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.Appointment;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment) throws CareTrackException;
}
