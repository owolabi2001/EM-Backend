package com.enigma.employeebackend.dto;


import com.enigma.employeebackend.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String staffName;
    private String email;
    private String staffId;
    private String role;

}
