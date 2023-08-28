package com.enigma.employeebackend.controller;


import com.enigma.employeebackend.dto.EmployeeDto;
import com.enigma.employeebackend.dto.response.GenericResponse;
import com.enigma.employeebackend.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping(path = "/addEmployee")
    private ResponseEntity<GenericResponse> addEmployee
            ( @RequestBody EmployeeDto employeeDto){
        return employeeService.saveEmployee(employeeDto);

    }

    @PostMapping(path = "/addEmployee/CSV")
    private ResponseEntity<GenericResponse> addEmployeeByCSV(@RequestParam("file") MultipartFile file){

        return employeeService.saveEmployeeByCSV(file);
//        TODO: check if the file sent from the frontend is either CSV or excel and then convert it to CSV before upload
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

    @GetMapping("getEployee/{name}")
    private ResponseEntity<GenericResponse> getEmployeeWithNameStartingwith(@PathVariable String name){
        return employeeService.getEmployeesByName(name);

    }

    @DeleteMapping("/deleteEmployee")
    private ResponseEntity<GenericResponse> deleteEmployee(@RequestParam String name){
        return employeeService.deleteEmployee(name);
    }

}
