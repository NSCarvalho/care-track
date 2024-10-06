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
@Schema(description = "Model representing a Patient")
public class Patient {

    @Schema(description = "Unique identifier of the patient", example = "1")
    private Long id;

    @Schema(description = "Age of the patient", example = "45")
    private Integer age;

    @Schema(description = "Name of the patient", example = "Jane Doe")
    private String name;

    @Schema(description = "List of pathologies the patient has. This field is not available in this version.")
    private List<Pathology> pathologyList;
}