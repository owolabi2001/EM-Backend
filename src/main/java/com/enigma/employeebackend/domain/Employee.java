package com.enigma.employeebackend.domain;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true,nullable = false)
    @CsvBindByName(column = "Staff Name")
    private String staffName;
    @CsvBindByName(column = "email")
    private String email;
    @CsvBindByName(column = "staffId")
    private String staffId;
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
