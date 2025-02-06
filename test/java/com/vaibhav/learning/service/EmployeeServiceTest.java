package com.vaibhav.learning.service;

import com.vaibhav.learning.dto.EmployeeDto;
import com.vaibhav.learning.entity.Employee;
import com.vaibhav.learning.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees_ReturnsListOfEmployees() {
        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(new Employee()));
        ResponseEntity<?> response = employeeService.getAllEmployees();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getEmployeeById_ReturnsEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
        ResponseEntity<?> response = employeeService.getEmployeeById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getEmployeeById_ThrowsEmployeeNotFoundException() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());
        ResponseEntity<?> response = employeeService.getEmployeeById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void addEmployee_CreatesNewEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        ResponseEntity<?> response = employeeService.addEmployee(employeeDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void addEmployee_ReturnsBadRequestForNullDto() {
        ResponseEntity<?> response = employeeService.addEmployee(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateEmployeeById_UpdatesExistingEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
        ResponseEntity<?> response = employeeService.updateEmployeeById(1, new EmployeeDto());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateEmployeeById_ThrowsEmployeeNotFoundException() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());
        ResponseEntity<?> response = employeeService.updateEmployeeById(1, new EmployeeDto());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteEmployeeById_DeletesEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
        ResponseEntity<?> response = employeeService.deleteEmployeeById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteEmployeeById_ThrowsEmployeeNotFoundException() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());
        ResponseEntity<?> response = employeeService.deleteEmployeeById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}