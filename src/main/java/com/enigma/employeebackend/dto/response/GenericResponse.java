package com.enigma.employeebackend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse {
    private String code;
    private String message;
    private Object data;
    private Object metaData;
}
