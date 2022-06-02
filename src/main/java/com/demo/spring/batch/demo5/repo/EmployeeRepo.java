package com.demo.spring.batch.demo5.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.batch.demo5.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {
}
