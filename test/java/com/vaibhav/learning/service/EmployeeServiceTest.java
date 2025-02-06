package com.vaibhav.learning.service;

import com.vaibhav.learning.dto.EmployeeDto;
import com.vaibhav.learning.entity.Employee;
import com.vaibhav.learning.exception.EmployeeNotFoundException;
import com.vaibhav.learning.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @AfterEach
    void tearDown() {
        // Perform cleanup after each test
        System.out.println("Cleaning up after each test...");
    }

    @AfterAll
    static void tearDownAll() {
        // Perform cleanup after all tests
        System.out.println("Cleaning up after all tests...");
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

    // EmployeeServiceTest.java

    @Test
    void deleteEmployeeById_ThrowsEmployeeNotFoundException() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.deleteEmployeeById(1);
        });
        doNothing().when(employeeRepository).deleteById(1);
    }

    @Test
    void updateEmployeeById_UpdatesExistingEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        ResponseEntity<?> response = employeeService.updateEmployeeById(1, new EmployeeDto());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateEmployeeById_ThrowsEmployeeNotFoundException() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.updateEmployeeById(1, new EmployeeDto());
        });
    }

    @Test
    void deleteEmployeeById_DeletesEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
        ResponseEntity<?> response = employeeService.deleteEmployeeById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}