package com.caretrack.business.services.impl;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.Appointment;
import com.caretrack.business.models.PaginatedList;
import com.caretrack.business.models.Patient;
import com.caretrack.business.repos.services.AppointmentRepoService;
import com.caretrack.business.repos.services.PatientRepoService;
import com.caretrack.business.services.interfaces.PatientService;
import com.caretrack.business.services.validators.AppointmentByPatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class PatientServiceImpl implements PatientService {

    private static final String ORDER_BY_DEFAULT = "id";
    private static final String DIRECTION_DEFAULT = "asc";
    private final AppointmentRepoService appointmentRepoService;
    private final PatientRepoService patientRepoService;

    @Autowired
    public PatientServiceImpl(AppointmentRepoService appointmentRepoService,
                              PatientRepoService patientRepoService) {
        this.appointmentRepoService = appointmentRepoService;
        this.patientRepoService = patientRepoService;
    }

    @Override
    public PaginatedList<Patient> getPatientList(Long page,
                                                 Long size,
                                                 String orderBy,
                                                 String direction) throws CareTrackException {
        try {
            var pageOrDefault = Objects.requireNonNullElse(page, 0L);
            var sizeOrDefault = Objects.requireNonNullElse(size, 0L);
            var orderByOrDefault = StringUtils.isEmpty(orderBy) ? ORDER_BY_DEFAULT : orderBy;
            var directionOrDefault = StringUtils.isEmpty(direction) ? DIRECTION_DEFAULT : direction;

            return patientRepoService.getPatientsPaginated(pageOrDefault, sizeOrDefault, orderByOrDefault, directionOrDefault);
        } catch (Exception e) {
            throw new CareTrackException("Error occurred while creating appointment");
        }
    }

    @Override
    public List<Appointment> getAppointmentByPatient(Integer patientId, Boolean includeSymptoms) throws CareTrackException {
        try {
            AppointmentByPatientValidator.applyValidations(patientId);
            return appointmentRepoService.getAppointmentsByPatient(patientId.longValue(), includeSymptoms != null && includeSymptoms);
        } catch (CareTrackException e) {
            throw e;
        } catch (Exception e) {
            throw new CareTrackException("Error occurred while creating appointment");
        }
    }
}
