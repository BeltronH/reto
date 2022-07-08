package com.kruger.reto.controllers;


import com.kruger.reto.dto.CreatePersonRequest;
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


import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")

public class PersonController {

        @Autowired
        private PersonService personService;

        @Autowired
        private IPersonRepository iPersonRepository;

        @Autowired
        private JWTUtil jwtUtil;


        @Operation(summary = "Person's List")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Person's List",
                        content = { @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Person.class)) }),
                @ApiResponse(responseCode = "400", description = "Invalid Id Supplied",
                        content = @Content),
                @ApiResponse(responseCode = "404", description = "Person's List Not Found",
                        content = @Content) })
        @GetMapping("/persons")
        private List<?> findByStatus(@RequestHeader(value="Authorization") String token) {
                if (!validarToken(token)) { return null; }
                return personService.findAll();
        }


        @Operation(summary = "Save a Person")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Person Saved",
                        content = { @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Person.class)) }),
                @ApiResponse(responseCode = "400", description = "Invalid Id Supplied",
                        content = @Content),
                @ApiResponse(responseCode = "404", description = "Person Not Found",
                        content = @Content) })
        @PostMapping("/person")
        public ResponseEntity<?> savePerson(@RequestBody CreatePersonRequest request){
                return ResponseEntity.ok(personService.savePerson(request));
        }

        @Operation(summary = "Disable a Person")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Person Disabled",
                        content = { @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Person.class)) }),
                @ApiResponse(responseCode = "400", description = "Invalid Id Supplied",
                        content = @Content),
                @ApiResponse(responseCode = "404", description = "Person Not Found",
                        content = @Content) })
        @PatchMapping("/persons/{id}")
        public ResponseEntity<?> disablePersonById(@RequestHeader(value="Authorization") String token, @PathVariable(value = "id") Integer id){
                if (!validarToken(token)) { return null; }
                return ResponseEntity.ok(personService.deleteById(id)) ;
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
