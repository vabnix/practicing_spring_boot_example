package com.vaibhav.learning.mapper;

import com.vaibhav.learning.dto.EmployeeDto;
import com.vaibhav.learning.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {

    public static EmployeeDto entityToDtoMapper(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setLocation(employee.getLocation());
        return employeeDto;
    }

    public static Employee dtoToEntityMapper(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setLocation(employeeDto.getLocation());
        return employee;
    }

    public static List<EmployeeDto> entityToDtoMapper(List<Employee> employeeList) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
            for(Employee employee: employeeList){
                EmployeeDto employeeDto = new EmployeeDto();
                employeeDto.setId(employee.getId());
                employeeDto.setName(employee.getName());
                employeeDto.setDepartment(employee.getDepartment());
                employeeDto.setLocation(employee.getLocation());
                employeeDtoList.add(employeeDto);
            }
            return employeeDtoList;
    }
}
