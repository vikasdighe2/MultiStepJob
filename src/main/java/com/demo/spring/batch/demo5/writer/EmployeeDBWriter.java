package com.demo.spring.batch.demo5.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.spring.batch.demo5.model.Employee;
import com.demo.spring.batch.demo5.repo.EmployeeRepo;

import java.util.List;

@Component
public class EmployeeDBWriter implements ItemWriter<Employee> {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public void write(List<? extends Employee> employees) throws Exception {
        employeeRepo.saveAll(employees);
        System.out.println("inside writer " + employees);
    }
}