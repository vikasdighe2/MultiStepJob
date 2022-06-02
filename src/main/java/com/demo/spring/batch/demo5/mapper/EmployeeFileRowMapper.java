package com.demo.spring.batch.demo5.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import com.demo.spring.batch.demo5.dto.EmployeeDTO;

public class EmployeeFileRowMapper implements FieldSetMapper<EmployeeDTO> {

    @Override
    public EmployeeDTO mapFieldSet(FieldSet fieldSet) {
        EmployeeDTO employee = new EmployeeDTO();
        employee.setEmployeeId(fieldSet.readString("employeeId"));
        employee.setFirstName(fieldSet.readString("firstName"));
        employee.setLastName(fieldSet.readString("lastName"));
        employee.setEmail(fieldSet.readString("email"));
        try {
            employee.setAge(fieldSet.readInt("age"));
        } catch (Exception e) {

        }
        return employee;
    }

}
