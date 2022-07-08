package com.kruger.reto.services;

import com.kruger.reto.dto.CreatePersonRequest;
import com.kruger.reto.dto.LoginRequest;
import com.kruger.reto.entities.Employee;
import com.kruger.reto.entities.Person;
import com.kruger.reto.repositories.IEmployeeRepository;
import com.kruger.reto.repositories.IPersonRepository;
import com.kruger.reto.validations.Validation;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.SchemaOutputResolver;
import java.util.List;
@Service
public class PersonService implements IPersonService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    IPersonRepository personRepository;

    @Autowired
    IEmployeeRepository employeeRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.findByStatus();
    }

    @Override
    public Person savePerson(CreatePersonRequest request) {

        Validation validation = new Validation();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, request.getIdentification());

        if(request.getLastName().isEmpty() || request.getFirstName().isEmpty() || request.getEmail().isEmpty() || request.getIdentification().isEmpty()) {
            System.out.println("Empty fields");
            return null;
        }else if(!validation.requireNumbers(request.getIdentification())) {
            System.out.println("only identifier numbers");
            return null;
        }else if(!validation.lengthIdentification(request.getIdentification())) {
            System.out.println("onlye 10 numbers");
            return null;
        }else if(!validation.requireText(request.getFirstName()) || !validation.requireText(request.getLastName())) {
            System.out.println("Name or Last Name Not Valid");
            return null;
        }else if(!validation.validateEmail(request.getEmail())) {
            System.out.println("Email Not Valid");
            return null;
        }else{
        Person person = Person.builder()
                .status("1")
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .identification(request.getIdentification())
                .email(request.getEmail())
                .nameUser(request.getNameUser())
                .passwordUser(hash)
                .rol("Employee")
                .build();

        personRepository.save(person);
        person = personRepository.save(person);

        saveEmployee(person.getPersonId());
        return person;
    }}
    public void saveEmployee(Integer id){
        Employee employee = Employee.builder()
                .personId(id)
                .status("1")
                .build();
        employeeRepository.save(employee);
    }
    public Person deleteById(Integer id) {
        Person person = personRepository
                .findById(id)
                .orElse(null);
        assert person != null;
        person.setStatus("0");
        personRepository.save(person);
        return person;
    }
    @Deprecated
    @Override
    public Person checkEmailPassword(LoginRequest request) {
        Person list = personRepository.checkEmail(request.getEmail());
        if (list == null) {
            return null;
        }

        String passwordHashed = list.getPasswordUser();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, request.getPassword())) {
            return list;
        }
        return null;
    }
}
