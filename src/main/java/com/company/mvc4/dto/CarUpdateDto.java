package com.company.mvc4.dto;

import lombok.Data;

@Data
public class CarUpdateDto {
    private String number;
    private String vinNumber;
    private int ownerId;
}
