package com.caretrack.business.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Details about the appointment")
public class Appointment {
    @Schema(description = "Unique identifier for the appointment")
    private Long id;

    @Schema(description = "The doctor assigned to the appointment")
    private Doctor doctor;

    @Schema(description = "The patient for the appointment")
    private Patient patient;

    @Schema(description = "List of symptoms associated with the appointment")
    private List<Symptom> symptoms;

    @Schema(description = "Pathology associated with the appointment")
    private Pathology pathology;
}
