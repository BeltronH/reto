package com.kruger.reto.services;

import com.kruger.reto.dto.UpdateEmployeeRequest;
import com.kruger.reto.entities.Employee;
import java.util.List;


public interface IEmployeeService {
    List<Employee> findAll();
    Employee findById(Integer id);
    Employee deleteById(Integer id);
    Employee updateEmployee(UpdateEmployeeRequest request, Integer id);
}
