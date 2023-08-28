package com.enigma.employeebackend.service;


import com.enigma.employeebackend.domain.Employee;
import com.enigma.employeebackend.dto.EmployeeDto;
import com.enigma.employeebackend.dto.response.GenericResponse;
import com.enigma.employeebackend.repository.EmployeeRepo;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public ResponseEntity<GenericResponse> saveEmployee( EmployeeDto employeeDto) {
        log.info("SAVE EMPLOYEE API");
        Employee check = employeeRepo.findEmployeeByStaffName(employeeDto.getStaffName().toLowerCase());

        try{
            if(check != null){
                return new ResponseEntity<>(
                        new GenericResponse("11",
                                "Employee Already in the Database"
                                ,employeeDto
                                ,null)
                        ,HttpStatus.ACCEPTED);


            }
            Employee employee = new Employee(employeeDto.getStaffName().toLowerCase()
                    , employeeDto.getEmail().toLowerCase()
                    , employeeDto.getStaffId().toLowerCase()
                    , employeeDto.getRole().toLowerCase() );
            employeeRepo.save(employee);
            return new ResponseEntity<>(
                    new GenericResponse("00"
                            ,"Employee with name: "+employeeDto.getStaffName() +" saved"
                            ,employee
                            ,null)
                    , HttpStatus.OK);


        }catch (Exception e){
            return new ResponseEntity<>(
                    new GenericResponse("11",e.getMessage(),null,null), HttpStatus.OK
            );
        }



    }

    public ResponseEntity<GenericResponse> getEmployee() {
        log.info("API to get 10 Employee's");
        List<Employee> employeeList;
        // this list if supposed to be list of 10 random employee's
        employeeList = employeeRepo.findRandom10Record();

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
        log.info("API to get Employees By name Starting with {}",name);
        List<Employee> employeeList = employeeRepo.findByStaffNameContainingIgnoreCase(name);
        if(employeeList.isEmpty()){
            return new ResponseEntity<>(new GenericResponse("00"
                    ,"no Employee with " + name,null,null)
                    ,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new GenericResponse("00"
                ,"Employee that their name starts with " + name
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


    public ResponseEntity<GenericResponse> saveEmployeeByCSV(MultipartFile file) {
        if(file.isEmpty()){
            return new ResponseEntity(new GenericResponse("11","No file sent", null,null), HttpStatus.OK);
        }
        try{
            List<Employee> employeeList = new ArrayList<>();
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

            CsvToBean<EmployeeDto> roomCsvToBean = new CsvToBeanBuilder(reader)
                    .withType(EmployeeDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<EmployeeDto> employeeDtoList = roomCsvToBean.parse();

            log.info(" Employee List: {}",employeeDtoList);


            for(EmployeeDto employeeDto: employeeDtoList){
                saveEmployee(employeeDto);


            }
            return new ResponseEntity<>(
                    new GenericResponse("00"
                            , "Employees Saved"
                            ,employeeList
                            ,null),HttpStatus.CREATED);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
