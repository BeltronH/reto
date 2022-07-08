package com.kruger.reto.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private Integer employeeId;

    @Column (name = "phone")
    private String phone;

    @Temporal(TemporalType.DATE)
    @Column (name = "dateOfBirth")
    private Date dateOfBirth;

    @Column (name = "homeAddress")
    private String homeAddress;

    @Column(name = "vaccineStatus")
    private Integer vaccineStatus;

    @Column(name = "personId")
    private Integer personId;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn (name = "personId", nullable = false,insertable = false,updatable = false)
    private Person person;

}
