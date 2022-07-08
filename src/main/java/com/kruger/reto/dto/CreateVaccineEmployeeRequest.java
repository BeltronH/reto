package com.kruger.reto.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateVaccineEmployeeRequest {

    private String nameVaccine;
    private Date dateVaccine;
    private Integer numberOfDosis;
    private Integer employeeId;
    private String status;

}
