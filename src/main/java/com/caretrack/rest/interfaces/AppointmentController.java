package com.caretrack.rest.interfaces;

import com.caretrack.business.models.Appointment;
import com.caretrack.rest.utils.RestResponse;

public interface AppointmentController {

    RestResponse<Appointment> createAppointment(Appointment appointment);
}
