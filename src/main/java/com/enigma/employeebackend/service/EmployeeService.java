package com.enigma.employeebackend.service;


import com.enigma.employeebackend.domain.Employee;
import com.enigma.employeebackend.dto.EmployeeDto;
import com.enigma.employeebackend.dto.GenericResponse;
import com.enigma.employeebackend.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public ResponseEntity<GenericResponse> saveEmployee(EmployeeDto employeeDto) {

        Employee check = employeeRepo.findEmployeeByStaffName(employeeDto.getStaffName());
        if(check==null){
            Employee employee = new Employee(employeeDto.getStaffName()
                    , employeeDto.getEmail()
                    , employeeDto.getStaffId()
                    , employeeDto.getRole() );
            employeeRepo.save(employee);
            return new ResponseEntity<>(
                    new GenericResponse("00","Employe saved",employee ,null)
                    , HttpStatus.OK);

        }
        return new ResponseEntity<>(
                new GenericResponse("00",
                        "Employee Already in the Database"
                        ,employeeDto
                        ,null)
                ,HttpStatus.OK);

    }
}
