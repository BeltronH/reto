package com.kruger.reto.controllers;

import com.kruger.reto.dto.UpdateEmployeeRequest;
import com.kruger.reto.entities.Person;
import com.kruger.reto.services.EmployeeService;
import com.kruger.reto.services.PersonService;
import com.kruger.reto.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JWTUtil jwtUtil;

    @Operation(summary = "Active Employee's List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Active Employee's List",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee Not Found",
                    content = @Content) })
    @GetMapping("/employees")
    public List<?> findByStatus(){
            return employeeService.findAll();

    }

    @Operation(summary = "Disable an Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee Disabled",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee Not Found",
                    content = @Content) })
    @PatchMapping("/employees/{id}")
    public ResponseEntity<?> disableEmployeeById(@RequestHeader(value="Authorization") String token, @PathVariable(value = "id") Integer id){
        if (!validarToken(token)) { return null; }
        return ResponseEntity.ok(employeeService.deleteById(id)) ;
    }

    @Operation(summary = "Update an Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee Updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee Not Found",
                    content = @Content) })
    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@RequestHeader(value="Authorization") String token, @RequestBody UpdateEmployeeRequest request, @PathVariable(value = "id") Integer id){
        if (!validarToken(token)) { return null; }
        return ResponseEntity.ok(employeeService.updateEmployee(request,id));
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
