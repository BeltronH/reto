package com.kruger.reto.controllers;

import com.kruger.reto.dto.CreatePersonRequest;
import com.kruger.reto.dto.LoginRequest;
import com.kruger.reto.dto.UpdateEmployeeRequest;
import com.kruger.reto.entities.Person;
import com.kruger.reto.repositories.IPersonRepository;
import com.kruger.reto.services.PersonService;
import com.kruger.reto.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private PersonService personService;

    @Autowired
    private IPersonRepository iPersonRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Deprecated
    @PostMapping("/login")
    public String loginEmployee(@RequestBody LoginRequest request){
        Person logedEmployee = personService.checkEmailPassword(request);
            if (logedEmployee != null) {
                String tokenJwt = jwtUtil.create(String.valueOf(logedEmployee.getPersonId()), logedEmployee.getEmail());
                return tokenJwt;
            }
        return "FAIL";
    }
}

