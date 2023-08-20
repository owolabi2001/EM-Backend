package com.enigma.employeebackend.repository;


import com.enigma.employeebackend.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Employee findEmployeeByStaffName(String staffName);

    List<Employee> findByStaffNameContainingIgnoreCase(String staffName);


    // An Optimized database query to get random employees from the dataBase
    @Query(value = "select * from employee TABLESAMPLE SYSTEM(60.0)",nativeQuery = true)
    List<Employee> findRandom10Record();



}
