package com.vaibhav.learning.service;

import com.vaibhav.learning.dto.EmployeeDto;
import com.vaibhav.learning.entity.Employee;
import com.vaibhav.learning.entity.ErrorResponse;
import com.vaibhav.learning.exception.EmployeeNotFoundException;
import com.vaibhav.learning.mapper.EmployeeMapper;
import com.vaibhav.learning.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        System.out.println("Employee Constructor Initialized");
    }

   @PostConstruct
    public void implementPostConstruct() throws Exception {
        System.out.println("\nInit Step after Employee Service is created. Post Construct\n");
    }


    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("\n Before Destroying Employee Service , calling PreDestroy");
    }


    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return new ResponseEntity<>(EmployeeMapper.entityToDtoMapper(employeeList),HttpStatus.OK);
    }

    public ResponseEntity<?> getEmployeeById(Integer id) {
        EmployeeDto employeeDto = new EmployeeDto();
        if(id!=null){
            try {
                Employee employee = employeeRepository.findById(id)
                        .orElseThrow(()-> new EmployeeNotFoundException("Employee Not Found with Id: "+ id));
                employeeDto = EmployeeMapper.entityToDtoMapper(employee);
                return new ResponseEntity<>(employeeDto, HttpStatus.OK);
            } catch (EmployeeNotFoundException e) {
                ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),e.getMessage(), "Employee Not Found");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
        } else {
                logger.error("Entered Employee Id is empty or null");
                return null;
        }
    }

    public ResponseEntity<?> addEmployee(EmployeeDto employeeDto) {
        if(employeeDto != null){
            Employee employee = new Employee();
            employee = EmployeeMapper.dtoToEntityMapper(employeeDto);
            employeeRepository.save(employee);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            logger.error("Employee data is empty or null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateEmployeeById(Integer id, EmployeeDto employeeDto) {
        if(id==null){
            logger.error("Employee Id cannot be null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee Not Found with Id: "+ id));
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setLocation(employeeDto.getLocation());
        employee.setDepartment(employeeDto.getDepartment());

        Employee updatedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(EmployeeMapper.entityToDtoMapper(updatedEmployee), HttpStatus.OK);
    }



    public ResponseEntity<?> deleteEmployeeById(Integer id) {
        if(id==null){
            logger.error("Employee Id cannot be null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee Not Found with Id: "+ id));
        if (employee.getId() != null){
            employeeRepository.deleteById(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
