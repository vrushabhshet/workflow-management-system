package com.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workflow.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}