package com.kruger.reto.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vaccinesEmployees")
public class VaccineEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccinesEmployeesId")
    private Integer vaccinesEmployeesId;

    @Column(name = "nameVaccine")
    private String nameVaccine;

    @Temporal(TemporalType.DATE)
    @Column(name = "dateVaccine")
    private Date dateVaccine;

    @Column(name = "numberOfDosis")
    private Integer numberOfDosis;

    @Column(name = "employeeId")
    private Integer employeeId;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn (name = "employeeId", nullable = false,insertable = false,updatable = false)
    private Employee employee;
}
