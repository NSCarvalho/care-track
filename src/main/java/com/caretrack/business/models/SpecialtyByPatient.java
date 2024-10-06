package com.caretrack.business.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Model representing the number of patients by specialty")
public class SpecialtyByPatient {

    @Schema(description = "Name of the specialty", example = "Cardiology")
    private String specialtyName;

    @Schema(description = "Number of patients in this specialty", example = "150")
    private Long numberOfPatients;
}