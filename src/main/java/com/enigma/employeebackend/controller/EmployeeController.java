package com.enigma.employeebackend.controller;


import com.enigma.employeebackend.dto.EmployeeDto;
import com.enigma.employeebackend.dto.GenericResponse;
import com.enigma.employeebackend.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping(path = "/addEmployee")
    private ResponseEntity<GenericResponse> addEmployee
            (@RequestBody EmployeeDto employeeDto){
        return employeeService.saveEmployee(employeeDto);

    }

    @CrossOrigin(origins = "*", methods = RequestMethod.POST)
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity handleOptionsRequest() {
        return ResponseEntity.ok().build();
    }


    @GetMapping("/getEmployee")
    private ResponseEntity<GenericResponse> getEmployee(){
        return employeeService.getEmployee();
    }

    @GetMapping("getEmploee/{name}")
    private ResponseEntity<GenericResponse> getEmployeeByName
            (@PathVariable String name){
        return employeeService.getEmployeeByName(name);
    }

}
