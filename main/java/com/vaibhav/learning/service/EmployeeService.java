package com.vaibhav.learning.service;

import com.vaibhav.learning.dto.EmployeeDto;
import com.vaibhav.learning.entity.Employee;
import com.vaibhav.learning.mapper.EmployeeMapper;
import com.vaibhav.learning.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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


    public EmployeeDto getAllEmployees() {
        Employee employee = employeeRepository.getAllEmployees();
        return EmployeeMapper.entityToDtoMapper(employee);
    }

    public EmployeeDto getEmployeeById(Integer id) {
        EmployeeDto employeeDto = new EmployeeDto();
        if(id!=null){
            Employee employee = employeeRepository.getEmployeeById(id);
            employeeDto = EmployeeMapper.entityToDtoMapper(employee);
            return employeeDto;
        } else {
                logger.error("Entered Employee Id is empty or null");
                return null;
        }
    }

    public void addEmployee(EmployeeDto employeeDto) {
        if(employeeDto != null){
            Employee employee = new Employee();
            employee = EmployeeMapper.dtoToEntityMapper(employeeDto);
            employeeRepository.addEmployee(employee);
        } else {
            logger.error("Employee data is empty or null");
            return;
        }
    }

    public EmployeeDto updateEmployeeById(Integer id, EmployeeDto employeeDto) {
        if(id==null){
            logger.error("Employee Id cannot be null");
            return null;
        }
        EmployeeDto dto = getEmployeeById(id);
        dto.setId(employeeDto.getId());
        dto.setName(employeeDto.getName());
        dto.setLocation(employeeDto.getLocation());
        dto.setDepartment(employeeDto.getDepartment());

        Employee employee = EmployeeMapper.dtoToEntityMapper(dto);
        Employee updatedEmployee = employeeRepository.updateEmployee(employee);
        return EmployeeMapper.entityToDtoMapper(updatedEmployee);

    }

    public void deleteEmployeeById(Integer id) {
        if(id==null){
            logger.error("Employee Id cannot be null");
            return;
        }
        EmployeeDto dto = getEmployeeById(id);
        if (dto.getId() != null){
            employeeRepository.deleteEmployeeById(id);
        }
    }



}
