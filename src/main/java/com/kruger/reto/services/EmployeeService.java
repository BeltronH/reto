package com.kruger.reto.services;

import com.kruger.reto.dto.UpdateEmployeeRequest;
import com.kruger.reto.entities.Employee;
import com.kruger.reto.repositories.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findByStatus();
    }

    @Override
    public Employee deleteById(Integer id) {
        Employee employees = employeeRepository
                .findById(id)
                .orElse(null);
        assert employees != null;
        employees.setStatus("0");
        employeeRepository.save(employees);
        return employees;
    }

    @Override
    public Employee updateEmployee(UpdateEmployeeRequest request, Integer id) {
        Employee employeeData = employeeRepository.findById(id).orElse(null);
        employeeData.setDateOfBirth(request.getDateOfBirth());
        employeeData.setHomeAddress(request.getHomeAddress());
        employeeData.setPhone(request.getPhone());
        employeeData.setVaccineStatus(request.getVaccineStatus());
        employeeRepository.save(employeeData);
        return employeeData;
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }


}
