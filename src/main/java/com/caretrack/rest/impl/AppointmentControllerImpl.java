package com.caretrack.rest.impl;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.Appointment;
import com.caretrack.business.services.interfaces.AppointmentService;
import com.caretrack.rest.interfaces.AppointmentController;
import com.caretrack.rest.utils.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentControllerImpl implements AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentControllerImpl(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "Create a new appointment",
            description = "Creates a new appointment with the given details.\n\n " +
                    "In this version the patient, the doctor, the symptoms, and the pathology must exist in the system. " +
                    "In the next version, we will allow the creation of these entities if they do not exist.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request process was successful; however, it may return business errors"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    @PostMapping("/appointments")
    public RestResponse<Appointment> createAppointment(
            @Parameter(description = "The appointment details to create", required = true)
            @RequestBody Appointment appointment) {
        try {
            return RestResponse.success(this.appointmentService
                            .createAppointment(appointment),
                    "Appointment created successfully");
        } catch (CareTrackException e) {
            return RestResponse.error(e.getMessage());
        }
    }
}
