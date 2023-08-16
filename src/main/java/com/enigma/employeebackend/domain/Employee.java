package com.enigma.employeebackend.domain;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true,nullable = false)
    private String staffName;
    private String email;
    private String staffId;
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
