package com.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workflow.model.EmployeeLeave;

public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long> {
	List<EmployeeLeave> findByEmployeeId(long employeeId);
}
