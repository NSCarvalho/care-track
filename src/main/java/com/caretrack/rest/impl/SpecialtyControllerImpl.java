package com.caretrack.rest.impl;

import com.caretrack.business.exceptions.CareTrackException;
import com.caretrack.business.models.SpecialtyByPatient;
import com.caretrack.business.services.interfaces.SpecialtyService;
import com.caretrack.rest.interfaces.SpecialtyController;
import com.caretrack.rest.utils.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecialtyControllerImpl implements SpecialtyController {
    private final SpecialtyService specialtyService;

    @Autowired
    public SpecialtyControllerImpl(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Operation(summary = "Retrieve specialties with at least a specified number of unique patients",
            description = "Fetches a list of specialties that have at least a minimum number of unique patients.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request process was successful; however, it may return business errors"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/specialties/unique-patients")
    public RestResponse<List<SpecialtyByPatient>> getSpecialtiesWithAtLeastUniquePatients(
            @Parameter(description = "Minimum number of unique patients to filter specialties",
                    example = "1")
            @RequestParam(required = false, defaultValue = "1") Long minPatients) {

        try {
            return RestResponse.success(this.specialtyService.getSpecialtiesWithAtLeastUniquePatients(minPatients),
                    "Specialty list retrieved successfully");
        } catch (CareTrackException e) {
            return RestResponse.error(e.getMessage());
        }
    }
}