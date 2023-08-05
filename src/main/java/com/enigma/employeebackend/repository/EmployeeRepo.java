package com.enigma.employeebackend.repository;


import com.enigma.employeebackend.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Employee findEmployeeByStaffName(String staffName);
    
//    List<Employee> findEmployeesByStaffNameStartingWithIgnorCase(String staffName);
    List<Employee> findByStaffNameStartingWith(String staffName);

    List<Employee> findByStaffNameStartingWithIgnoreCase(String staffName);
    List<Employee> findByStaffNameContainingIgnoreCase(String staffName);
    Employee findByEmail(String emial);
}
