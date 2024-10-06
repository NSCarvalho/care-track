package com.caretrack.business.repos.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SpecialtyPatientCount {
    private String specialtyName;
    private Long numberOfPatients;
}
