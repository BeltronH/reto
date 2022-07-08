package com.kruger.reto.services;

import com.kruger.reto.dto.CreateVaccineEmployeeRequest;
import com.kruger.reto.entities.VaccineEmployee;

import java.util.List;

public interface IVaccineEmployeeService {
    List<VaccineEmployee> findAll();
    void deleteById(Integer id);
    VaccineEmployee findById(Integer id);
    VaccineEmployee updateVaccineEmployee(CreateVaccineEmployeeRequest request, Integer id);
    VaccineEmployee saveVaccineEmployee(CreateVaccineEmployeeRequest request, Integer id);

}
