package com.company.mvc4.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarSearchDto {
    @JsonProperty("number")
    private String number;

    @JsonProperty("vin_number")
    private String vinNumber;
}
