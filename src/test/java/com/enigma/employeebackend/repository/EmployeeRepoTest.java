package com.enigma.employeebackend.repository;

import com.enigma.employeebackend.domain.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepoTest {

    @Autowired
    private EmployeeRepo underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findEmployeeByStaffName() {
//        given
        Employee employee = new Employee("owolabi olamide","owolabi.olamide@gmail.com","0e214","Chief officer");
        underTest.save(employee);

//        when
        Employee employee1= underTest.findEmployeeByStaffName("owolabi olamide");
//        then
        assertThat(employee1).isEqualTo(employee);
    }

    @Test
    void findByStaffNameContainingIgnoreCase() {
//        given
        List<Employee> employeeList = List.of(new Employee("Owolabi Temi","owolabi.temi@lmu.edu.ng","i43oed","Imf")
                ,new Employee("Owolabi Olamide","owolabi.olamide@gmail.com","p1672h","coordinator"));
        underTest.saveAll(employeeList);
        Employee employee = new Employee("Owolabi Temi","owolabi.temi@lmu.edu.ng","i43oed","Imf");
//        when
        List<Employee> employees= underTest.findByStaffNameContainingIgnoreCase("Owol");
//        then
        assertThat(employeeList).isEqualTo(employees);

    }

    @Test
    @Disabled
    void findRandom10Record() {
    }
}