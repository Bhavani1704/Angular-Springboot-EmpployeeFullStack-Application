package com.bhavani.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhavani.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
