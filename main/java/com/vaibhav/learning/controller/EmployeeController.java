package com.vaibhav.learning.controller;

import com.vaibhav.learning.dto.EmployeeDto;
import com.vaibhav.learning.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.addEmployee(employeeDto);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployeeById(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto){
        return employeeService.updateEmployeeById(id, employeeDto);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Integer id){
         return employeeService.deleteEmployeeById(id);
    }




}
