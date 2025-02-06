package com.vaibhav.learning.controller;

import com.vaibhav.learning.dto.EmployeeDto;
import com.vaibhav.learning.service.EmployeeService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

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
    void getEmployees_ReturnsListOfEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<?> response = employeeController.getEmployees();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getEmployeeById_ReturnsEmployee() {
        when(employeeService.getEmployeeById(anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<?> response = employeeController.getEmployeeById(1);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getEmployeeById_ReturnsNotFound() {
        when(employeeService.getEmployeeById(anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        ResponseEntity<?> response = employeeController.getEmployeeById(1);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void addEmployee_CreatesNewEmployee() {
        when(employeeService.addEmployee(any(EmployeeDto.class))).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
        ResponseEntity<?> response = employeeController.addEmployee(new EmployeeDto());
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void addEmployee_ReturnsBadRequestForNullDto() {
        when(employeeService.addEmployee(null)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        ResponseEntity<?> response = employeeController.addEmployee(null);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateEmployeeById_UpdatesExistingEmployee() {
        when(employeeService.updateEmployeeById(anyInt(), any(EmployeeDto.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<?> response = employeeController.updateEmployeeById(1, new EmployeeDto());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateEmployeeById_ReturnsNotFound() {
        when(employeeService.updateEmployeeById(anyInt(), any(EmployeeDto.class))).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        ResponseEntity<?> response = employeeController.updateEmployeeById(1, new EmployeeDto());
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteEmployeeById_DeletesEmployee() {
        when(employeeService.deleteEmployeeById(anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<?> response = employeeController.deleteEmployeeById(1);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteEmployeeById_ReturnsNotFound() {
        when(employeeService.deleteEmployeeById(anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        ResponseEntity<?> response = employeeController.deleteEmployeeById(1);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getEmployeeById_ReturnsBadRequestForNullId() {
        when(employeeService.getEmployeeById(null)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        ResponseEntity<?> response = employeeController.getEmployeeById(null);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deleteEmployeeById_ReturnsBadRequestForNullId() {
        when(employeeService.deleteEmployeeById(null)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        ResponseEntity<?> response = employeeController.deleteEmployeeById(null);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateEmployeeById_ReturnsBadRequestForNullId() {
        when(employeeService.updateEmployeeById(isNull(), any(EmployeeDto.class))).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        ResponseEntity<?> response = employeeController.updateEmployeeById(null, new EmployeeDto());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}