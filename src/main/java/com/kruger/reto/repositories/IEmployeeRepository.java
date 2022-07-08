package com.kruger.reto.repositories;

import com.kruger.reto.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface IEmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query(value="SELECT employees.* FROM employees WHERE employees.status = '1'",nativeQuery = true)
    List<Employee> findByStatus();

    @Query(value="SELECT persons.*, employees.employee_id, employees.phone,employees.date_of_birth, employees.home_address, employees.vaccine_status FROM persons INNER JOIN employees ON persons.person_id=employees.person_id INNER JOIN vaccines_employees AS ve ON ve.employee_id = employees.employee_id WHERE employees.vaccine_status = :status",nativeQuery = true)
    List<Map<String, Object>> findByState(@Param("status") Integer status);

    @Query(value="SELECT persons.*, employees.employee_id, employees.phone,employees.date_of_birth, employees.home_address, employees.vaccine_status, ve.vaccines_employees_id, ve.date_vaccine, ve.name_vaccine, ve.number_of_dosis  FROM persons INNER JOIN employees ON persons.person_id=employees.person_id INNER JOIN vaccines_employees AS ve ON ve.employee_id = employees.employee_id WHERE ve.name_vaccine = :name",nativeQuery = true)
    List<Map<String, Object>> findByVaccineName(@Param("name") String name);

    @Query(value="SELECT persons.*, employees.employee_id, employees.phone,employees.date_of_birth, " +
            "employees.home_address, employees.vaccine_status, ve.vaccines_employees_id, ve.date_vaccine, " +
            "ve.name_vaccine, ve.number_of_dosis  " +
            "FROM persons INNER JOIN employees ON persons.person_id=employees.person_id " +
            "INNER JOIN vaccines_employees AS ve ON ve.employee_id = employees.employee_id " +
            "WHERE ve.date_vaccine BETWEEN ':datein' AND ':dateout'",nativeQuery = true)
    List<Map<String, Object>> findByVaccineDate(@Param("datein") Date datein, @Param("dateout") Date dateout);


}
