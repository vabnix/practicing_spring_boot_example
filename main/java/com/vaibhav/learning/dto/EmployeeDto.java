package com.vaibhav.learning.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class EmployeeDto {
    private Integer id;
    private String name;
    private String department;
    private String location;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
