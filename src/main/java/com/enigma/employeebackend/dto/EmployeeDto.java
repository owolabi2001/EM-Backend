package com.enigma.employeebackend.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EmployeeDto {

    private String staffName;
    private String email;
    private String staffId;
    private String role;

}
