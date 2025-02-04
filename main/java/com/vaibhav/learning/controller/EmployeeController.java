package com.vaibhav.learning.controller;

import com.vaibhav.learning.dto.EmployeeDto;
import com.vaibhav.learning.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public EmployeeDto getEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.addEmployee(employeeDto);
    }

    @PutMapping("/employees/{id}")
    public EmployeeDto updateEmployeeById(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto){
        return employeeService.updateEmployeeById(id, employeeDto);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployeeById(@PathVariable Integer id){
         employeeService.deleteEmployeeById(id);
    }




}
