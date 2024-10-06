package com.caretrack.business.repos.entities;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DOCTOR")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SPECIALTY_ID", nullable = false)
    private Long specialtyId;

    @Column(nullable = false)
    private String name;
}
