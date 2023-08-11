package com.enigma.employeebackend.service;


import com.enigma.employeebackend.domain.Employee;
import com.enigma.employeebackend.dto.EmployeeDto;
import com.enigma.employeebackend.dto.response.GenericResponse;
import com.enigma.employeebackend.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public ResponseEntity<GenericResponse> saveEmployee(EmployeeDto employeeDto) {

        Employee check = employeeRepo.findEmployeeByStaffName(employeeDto.getStaffName());
        if(check==null){
            log.info("SAVE EMPLOYEE API");
            Employee employee = new Employee(employeeDto.getStaffName().toLowerCase()
                    , employeeDto.getEmail().toLowerCase()
                    , employeeDto.getStaffId().toLowerCase()
                    , employeeDto.getRole().toLowerCase() );
            employeeRepo.save(employee);
            return new ResponseEntity<>(
                    new GenericResponse("00"
                            ,"Employe with name: "+employeeDto.getStaffName() +" saved"
                            ,employee
                            ,null)
                    , HttpStatus.OK);

        }
        return new ResponseEntity<>(
                new GenericResponse("11",
                        "Employee Already in the Database"
                        ,employeeDto
                        ,null)
                ,HttpStatus.ACCEPTED);

    }

    public ResponseEntity<GenericResponse> getEmployee() {
        log.info("API to get 10 Employee's");
        List<Employee> employeeList;
        // this list if supposed to be list of 10 random employee's
        employeeList = employeeRepo.findAll();

        return new ResponseEntity<>(
                new GenericResponse("00"
                        ,"The 10 Employee's include"
                        ,employeeList
                        ,null
                )
                ,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<GenericResponse> getEmployeeByName(String name) {
        log.info("API to get Employee by Name");
        Employee employee = employeeRepo.findEmployeeByStaffName(name.toLowerCase());
        if(employee==null){
            return new ResponseEntity<>(new GenericResponse("00"
                    ,"no Employee with "+ name,null,null)
                    ,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new GenericResponse("00"
                ,""
                ,employee
                ,null),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<GenericResponse> getEmployeesByName(String name){
        log.info("API to get Employees By name Starting with ",name);
        List<Employee> employeeList = employeeRepo.findByStaffNameContainingIgnoreCase(name);
        if(employeeList.size() == 0 ){
            return new ResponseEntity<>(new GenericResponse("00"
                    ,"no Employee with " + name,null,null)
                    ,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new GenericResponse("00"
                ,"Employee that their name startswith " + name
                ,employeeList
                ,null)
                ,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<GenericResponse> deleteEmployee(String name) {
        log.info("API to delete Employee from the Database");
        Employee employee = employeeRepo.findEmployeeByStaffName(name.toLowerCase());
        System.out.println(employee);
        if(employee==null){
            return new ResponseEntity<>(new GenericResponse("11"
                    ,"No Employee with the name " + name
                    ,null
                    ,null)
                    ,HttpStatus.ACCEPTED);
        }
        employeeRepo.delete(employee);
        return new ResponseEntity<>(new GenericResponse("00"
                ,"Employee " + name +" Deleted"
                , null
                ,null)
                ,HttpStatus.ACCEPTED);
    }
}
