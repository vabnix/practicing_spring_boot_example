package com.vaibhav.learning.repository;

import com.vaibhav.learning.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    public Employee getAllEmployees() {
        return new Employee(1,"Vaibhav Srivastava", "Engineering", "Texas");
    }

    public Employee getEmployeeById(Integer id) {
        return new Employee(1,"Vaibhav Srivastava", "Engineering", "Texas");
    }

    public void addEmployee(Employee employee) {
    }

    public Employee updateEmployee(Employee employee) {
        return employee;
    }

    public void deleteEmployeeById(Integer id) {

    }
}
