package com.caretrack.business.services.interfaces;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.Appointment;
import com.caretrack.business.models.PaginatedList;
import com.caretrack.business.models.Patient;

import java.util.List;

public interface PatientService {

    PaginatedList<Patient> getPatientList(Long page,
                                                Long size,
                                                String orderBy,
                                                String direction) throws CareTrackException;

    List<Appointment> getAppointmentByPatient(Integer patientId,
                                              Boolean includeSymptoms) throws CareTrackException;
}
