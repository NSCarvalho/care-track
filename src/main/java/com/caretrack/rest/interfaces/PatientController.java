package com.caretrack.rest.interfaces;

import com.caretrack.business.models.Appointment;
import com.caretrack.business.models.PaginatedList;
import com.caretrack.business.models.Patient;
import com.caretrack.rest.utils.RestResponse;

import java.util.List;

public interface PatientController {

    RestResponse<PaginatedList<Patient>> getPatientList(Long page,
                                                        Long size,
                                                        String orderBy,
                                                        String direction);

    RestResponse<List<Appointment>> getAppointmentByPatient(Integer patientId,
                                                            Boolean includeSymptoms);
}
