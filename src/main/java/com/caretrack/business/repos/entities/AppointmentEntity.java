package com.caretrack.business.repos.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "APPOINTMENT")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DOCTOR_ID")
    private Long doctorId;
    @Column(name = "PATIENT_ID")
    private Long patientId;
    @JoinColumn(name = "PATHOLOGY_ID", nullable = true)
    private Long pathologyId;
    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentSymptomEntity> appointmentSymptoms;
}
