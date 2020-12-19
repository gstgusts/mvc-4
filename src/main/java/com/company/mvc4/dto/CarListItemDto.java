package com.company.mvc4.dto;

import com.company.mvc4.data.Car;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarListItemDto {
    private Car car;
    private int orderNumber;
}
