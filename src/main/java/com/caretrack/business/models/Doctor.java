package com.caretrack.business.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Model representing a Doctor")
public class Doctor {

    @Schema(description = "Unique identifier of the doctor", example = "1")
    private Long id;

    @Schema(description = "Name of the doctor", example = "Dr. John Doe")
    private String name;

    @Schema(description = "Specialty of the doctor")
    private Specialty specialty;
}