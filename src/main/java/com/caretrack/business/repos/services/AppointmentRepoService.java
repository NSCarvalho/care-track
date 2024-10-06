package com.caretrack.business.repos.services;

import com.caretrack.business.models.*;
import com.caretrack.business.repos.entities.*;
import com.caretrack.business.repos.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AppointmentRepoService {

    private final AppointmentRepository appointmentRepository;
    private final SymptomRepository symptomRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PathologyRepository pathologyRepository;
    private final SpecialtyRepoService SpecialtyRepoService;

    @Autowired
    public AppointmentRepoService(AppointmentRepository appointmentRepository,
                                  SymptomRepository symptomRepository,
                                  DoctorRepository doctorRepository,
                                  PatientRepository patientRepository,
                                  PathologyRepository pathologyRepository,
                                  SpecialtyRepoService SpecialtyRepoService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.symptomRepository = symptomRepository;
        this.pathologyRepository = pathologyRepository;
        this.SpecialtyRepoService = SpecialtyRepoService;
    }

    private static List<Symptom> getSymptoms(Boolean includeSymptoms, AppointmentEntity appointmentEntity) {
        return includeSymptoms ? appointmentEntity.getAppointmentSymptoms().stream()
                .map(appointmentSymptom ->
                        Symptom.builder().description(appointmentSymptom.getSymptom().getDescription()).id(appointmentSymptom.getSymptom().getId()).build())
                .toList() : Collections.emptyList();
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId, Boolean includeSymptoms) {
        return appointmentRepository.findByPatientId(patientId).stream()
                .map(appointmentEntity -> {

                    // Retrieve doctor and patient details
                    var doctor = getDoctor(appointmentEntity.getDoctorId());
                    var patient = getPatient(appointmentEntity.getPatientId());

                    // Build the appointment object
                    return Appointment.builder()
                            .id(appointmentEntity.getId())
                            .doctor(Doctor.builder()
                                    .id(doctor.getId())
                                    .name(doctor.getName())
                                    .specialty(getSpecialtyById(doctor))
                                    .build())
                            .patient(Patient.builder()
                                    .id(patient.getId())
                                    .name(patient.getName())
                                    .age(patient.getAge())
                                    .build())
                            .symptoms(getSymptoms(includeSymptoms, appointmentEntity)) // Handle symptoms inclusion
                            .build();
                })
                .toList();
    }

    public Appointment save(Appointment appointment) {

        var doctor = getDoctor(appointment.getDoctor().getId());
        var patient = getPatient(appointment.getPatient().getId());
        var pathology = getPathology(appointment.getPathology().getId());

        var appointmentEntity = AppointmentEntity.builder()
                .doctorId(doctor.getId())
                .patientId(patient.getId())
                .pathologyId(pathology.getId())
                .build();

        appointmentEntity.setAppointmentSymptoms(getAppointmentSymptomList(appointment, appointmentEntity));

        var savedEntity = appointmentRepository.save(appointmentEntity);
        return Appointment.builder()
                .id(savedEntity.getId())
                .doctor(Doctor
                        .builder()
                        .id(doctor.getId())
                        .name(doctor.getName())
                        .specialty(getSpecialtyById(doctor))
                        .build())
                .patient(Patient
                        .builder()
                        .id(patient.getId())
                        .name(patient.getName())
                        .build())
                .symptoms(appointment.getSymptoms())
                .pathology(Pathology
                        .builder()
                        .id(pathology.getId())
                        .name(pathology.getName())
                        .build())
                .build();
    }

    private Specialty getSpecialtyById(DoctorEntity doctor) {
        return SpecialtyRepoService.getSpecialtyById(doctor.getSpecialtyId());
    }

    private DoctorEntity getDoctor(Long id) {
        var doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return doctor;
    }

    private PatientEntity getPatient(Long id) {
        var patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patient;
    }

    private PathologyEntity getPathology(Long id) {
        var pathology = pathologyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return pathology;
    }

    private List<AppointmentSymptomEntity> getAppointmentSymptomList(Appointment appointment, AppointmentEntity appointmentEntity) {
        if (appointment.getSymptoms() == null) {
            return Collections.emptyList();
        }
        // Create a list to hold appointment symptoms
        List<AppointmentSymptomEntity> appointmentSymptoms = new ArrayList<>();
        for (Symptom newSymptom : appointment.getSymptoms()) {
            SymptomEntity symptom = symptomRepository.findById(newSymptom.getId())
                    .orElseThrow(() -> new RuntimeException("Symptom not found"));

            // Create AppointmentSymptom instance
            AppointmentSymptomEntity appointmentSymptom = new AppointmentSymptomEntity();
            appointmentSymptom.setAppointment(appointmentEntity); // Set the appointment reference
            appointmentSymptom.setSymptom(symptom); // Set the symptom reference

            // Add to the list
            appointmentSymptoms.add(appointmentSymptom);
        }
        return appointmentSymptoms;
    }
}
