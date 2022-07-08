package com.kruger.reto.repositories;

import com.kruger.reto.entities.VaccineEmployee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVaccineEmployeeRepository extends CrudRepository<VaccineEmployee, Integer> {
}
