package com.enigma.employeebackend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
