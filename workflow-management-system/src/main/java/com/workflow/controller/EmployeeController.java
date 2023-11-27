package com.workflow.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workflow.model.Employee;
import com.workflow.repository.EmployeeRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		if (employees.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if (!employee.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(employee.get(), HttpStatus.OK);

	}

	@PostMapping("/")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		try {
			Employee savedEmployee = employeeRepository.save(employee);
			return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if (!employee.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		employee.get().setLastName(employeeDetails.getLastName());
		employee.get().setEmailId(employeeDetails.getEmailId());
		employee.get().setFirstName(employeeDetails.getFirstName());
		Employee updatedEmployee = employeeRepository.save(employee.get());
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);

		if (!employee.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		employeeRepository.delete(employee.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}