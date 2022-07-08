package com.kruger.reto.repositories;

import com.kruger.reto.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IPersonRepository extends CrudRepository<Person, Integer> {

    @Query(value="SELECT persons.* FROM persons WHERE persons.status = '1'",nativeQuery = true)
    List<Person> findByStatus();

    @Query(value="SELECT persons.* FROM persons WHERE persons.email = :email LIMIT 1",nativeQuery = true)
    Person checkEmail(@Param("email") String email);


}
