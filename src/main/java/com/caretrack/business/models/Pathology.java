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
@Schema(description = "Model representing a Pathology")
public class Pathology {

    @Schema(description = "Unique identifier for the pathology", example = "1")
    private Long id;

    @Schema(description = "Name of the pathology", example = "Hypertension")
    private String name;
}