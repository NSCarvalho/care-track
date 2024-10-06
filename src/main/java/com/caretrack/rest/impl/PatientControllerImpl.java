package com.caretrack.rest.impl;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.Appointment;
import com.caretrack.business.models.PaginatedList;
import com.caretrack.business.models.Patient;
import com.caretrack.business.services.interfaces.PatientService;
import com.caretrack.rest.interfaces.PatientController;
import com.caretrack.rest.utils.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientControllerImpl implements PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientControllerImpl(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Retrieve a list of patients",
            description = "Fetches a paginated list of patients with optional sorting.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request process was successful; however, it may return business errors"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    @GetMapping("/patients")
    public RestResponse<PaginatedList<Patient>> getPatientList(
            @Parameter(description = "The page number to retrieve.\nDefault is '0'.", example = "0")
            @RequestParam(required = false, defaultValue = "0") Long page,

            @Parameter(description = "The number of patients per page.\nDefault is '10'.", example = "10")
            @RequestParam(required = false, defaultValue = "10") Long size,

            @Parameter(description = "Field by which to sort the results.\nDefault is 'id'.")
            @RequestParam(defaultValue = "id") String orderBy,

            @Parameter(description = "Direction of sorting: 'asc' or 'desc'.\nDefault is 'asc'.")
            @RequestParam(defaultValue = "asc") String direction) {
        try {
            return RestResponse.success(this
                            .patientService
                            .getPatientList(page, size, orderBy, direction),
                    "Patient list retrieved successfully");
        } catch (CareTrackException e) {
            return RestResponse.error(e.getMessage());
        }
    }

    @Operation(summary = "Retrieve appointments for a specific patient",
            description = "Fetches a list of appointments for a patient by their ID, with an option to include symptoms.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request process successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    @GetMapping("/patients/{patientId}/appointments")
    public RestResponse<List<Appointment>> getAppointmentByPatient(
            @Parameter(description = "The ID of the patient", required = true)
            @PathVariable Integer patientId,
            @Parameter(description = "Include symptoms in the appointment data")
            @RequestParam(required = false) Boolean includeSymptoms) {
        try {
            return RestResponse.success(this
                            .patientService
                            .getAppointmentByPatient(patientId, includeSymptoms),
                    "Appointment list retrieved successfully");
        } catch (CareTrackException e) {
            return RestResponse.error(e.getMessage());
        }
    }
}
