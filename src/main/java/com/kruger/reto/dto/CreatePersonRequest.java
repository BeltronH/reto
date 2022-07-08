package com.kruger.reto.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest {

    private String identification;
    private String firstName;
    private String lastName;
    private String email;
    private String rol;
    private String nameUser;
    private String passwordUser;
    private Integer personId;
    private String status;

}
