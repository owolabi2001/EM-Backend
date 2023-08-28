package com.enigma.employeebackend.service;

import com.enigma.employeebackend.domain.Employee;
import com.enigma.employeebackend.dto.EmployeeDto;
import com.enigma.employeebackend.dto.response.GenericResponse;
import com.enigma.employeebackend.repository.EmployeeRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {


    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private  EmployeeService underTest;

    @BeforeAll
    static void beforeAll() {
//
    }

    @Test
    void testToSaveEmployee() {

//        given
        EmployeeDto employee = new EmployeeDto("Owolabi George","owolabi.george@lmu.edu.ng","IP68942","CTO");

        ArgumentCaptor<EmployeeDto> employeeDtoArgumentCaptor = ArgumentCaptor.forClass(EmployeeDto.class);

//        when
//        when(employeeRepo.save(any(Employee.class))).thenReturn()
        ResponseEntity<GenericResponse> response = underTest.saveEmployee(employee);

//        then

        verify(employeeRepo).findEmployeeByStaffName(employee.getStaffName().toLowerCase());
//        ResponseEntity<GenericResponse> verifyWith = new ResponseEntity<GenericResponse>(
//                new GenericResponse("00"
//                        ,"Employee with name: "+employee.getStaffName() +" saved"
//                        ,employee
//                        ,null)
//                , HttpStatus.OK);

//        verify(response).equals(verifyWith);



    }

    @Test
    void getEmployee() {
    }

    @Test
    void getEmployeeByName() {
    }

    @Test
    void getEmployeesByName() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void saveEmployeeByCSV() {
    }
}