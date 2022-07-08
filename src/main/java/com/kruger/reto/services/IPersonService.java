package com.kruger.reto.services;

import com.kruger.reto.dto.CreatePersonRequest;
import com.kruger.reto.dto.LoginRequest;
import com.kruger.reto.entities.Person;

import java.util.List;

public interface IPersonService {
    List<Person> findAll();
    Person savePerson(CreatePersonRequest request);
    Person deleteById(Integer id);
    Person checkEmailPassword(LoginRequest request);
}
