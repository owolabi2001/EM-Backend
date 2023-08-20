package com.enigma.employeebackend.domain;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "staffName",unique = false,nullable = false)
    @CsvBindByName(column = "Staff Name")
    private String staffName;

    @Column(name = "email")
    @CsvBindByName(column = "email")
    private String email;

    @Column(name = "staffId")
    @CsvBindByName(column = "staffId")
    private String staffId;

    @Column(name = "role")
    @CsvBindByName(column = "role")
    private String role;

    public Employee(String staffName, String email, String staffId, String role) {
        this.staffName = staffName;
        this.email = email;
        this.staffId = staffId;
        this.role = role;
    }

    public Employee() {
    }
}
