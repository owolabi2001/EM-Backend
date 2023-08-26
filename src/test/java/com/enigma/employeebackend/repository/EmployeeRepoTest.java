package com.enigma.employeebackend.repository;

import com.enigma.employeebackend.domain.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    @Disabled
    void findByStaffNameContainingIgnoreCase() {
//        given
//        when
//        then

    }

    @Test
    @Disabled
    void findRandom10Record() {
    }
}