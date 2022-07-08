package com.kruger.reto.controllers;

import com.kruger.reto.entities.Person;
import com.kruger.reto.repositories.IEmployeeRepository;
import com.kruger.reto.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class FilterEmployeeController {

    @Autowired
    IEmployeeRepository employeeRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Operation(summary = "Filter Employees By Vaccine Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees Vaccinated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employees Vaccinated Not Found",
                    content = @Content) })
    @GetMapping("/filtervaccinestatus/{status}")
    private List<?> findByState(@RequestHeader(value="Authorization") String token, @PathVariable(value = "status") Integer status) {
        if (!validarToken(token)) { return null; }
        return employeeRepository.findByState(status);
    }

    @Operation(summary = "Filter Employees By Vaccine Name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees Vaccinated By Specific Vaccine Name",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employees Vaccinated Not Found",
                    content = @Content) })
    @GetMapping("/filtervaccinename/{name}")
    private List<?> findByVaccineName(@RequestHeader(value="Authorization") String token, @PathVariable(value = "name") String name) {
        if (!validarToken(token)) { return null; }
        return employeeRepository.findByVaccineName(name);
    }

    @Operation(summary = "Filter Employees By Vaccine Date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees Vaccinated By Vaccine Date",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employees Vaccinated Not Found",
                    content = @Content) })
    @GetMapping("/filterdatevaccine")
    private List<?> findByVaccineDate(@RequestHeader(value="Authorization") String token, @RequestParam Date datein, @RequestParam Date dateout) {
        if (!validarToken(token)) { return null; }
        return employeeRepository.findByVaccineDate(datein, dateout);
    }

    @Operation(summary = "Validate Token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token Validate",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Token Not Found",
                    content = @Content) })
    private boolean validarToken(String token) {
        String personId = jwtUtil.getKey(token);
        return personId != null;
    }

}
