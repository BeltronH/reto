package com.kruger.reto.controllers;

import com.kruger.reto.dto.CreateVaccineEmployeeRequest;
import com.kruger.reto.entities.Person;
import com.kruger.reto.services.VaccineEmployeeService;
import com.kruger.reto.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class VaccineEmployeeController {

    @Autowired
    private VaccineEmployeeService vaccineEmployeeService;

    @Autowired
    private JWTUtil jwtUtil;

    @Operation(summary = "List Of All Employees Vaccinated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees Vaccinated List",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employees Vaccinated Not Found",
                    content = @Content) })
    @GetMapping("/vaccineEmployee")
    public ResponseEntity<?> getAllVaccineEmployee(@RequestHeader(value="Authorization") String token, @RequestParam(required = false) String vaccineEmployee){
        if (!validarToken(token)) { return null; }
        return ResponseEntity.ok(vaccineEmployeeService.findAll());
    }

    @Operation(summary = "Save Employee Vaccinated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee Vaccinated Saved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee Vaccinated Not Found",
                    content = @Content) })
    @PostMapping("/vaccineEmployee/{id}")
    public ResponseEntity<?> saveVaccineEmployee(@RequestHeader(value="Authorization") String token, @RequestBody CreateVaccineEmployeeRequest request, @PathVariable(value = "id") Integer id){
        if (!validarToken(token)) { return null; }
        return ResponseEntity.ok(vaccineEmployeeService.saveVaccineEmployee(request,id));
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