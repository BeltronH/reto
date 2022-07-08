package com.kruger.reto.services;

import com.kruger.reto.dto.CreateVaccineEmployeeRequest;
import com.kruger.reto.entities.Employee;
import com.kruger.reto.entities.VaccineEmployee;
import com.kruger.reto.repositories.IEmployeeRepository;
import com.kruger.reto.repositories.IVaccineEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VaccineEmployeeService implements IVaccineEmployeeService{

    @Autowired
    private IVaccineEmployeeRepository vaccineEmployeeRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<VaccineEmployee> findAll() {
        return (List<VaccineEmployee>) vaccineEmployeeRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
    }

    @Override
    public VaccineEmployee findById(Integer id) {
        return vaccineEmployeeRepository.findById(id).orElse(null);
    }

    @Override
    public VaccineEmployee updateVaccineEmployee(CreateVaccineEmployeeRequest request, Integer id) {
        return null;
    }

    @Override
    public VaccineEmployee saveVaccineEmployee(CreateVaccineEmployeeRequest request, Integer id) {
        Employee employeeData = employeeRepository.findById(id).orElse(null);

        if(employeeData.getVaccineStatus()== 1){
            VaccineEmployee vaccineEmployee = VaccineEmployee.builder()
                    .nameVaccine(request.getNameVaccine())
                    .dateVaccine(request.getDateVaccine())
                    .numberOfDosis(request.getNumberOfDosis())
                    .status("1")
                    .employeeId(id)
                    .build();
            vaccineEmployeeRepository.save(vaccineEmployee);
            return vaccineEmployee;
        }else{
            return null;
        }
    }

}
