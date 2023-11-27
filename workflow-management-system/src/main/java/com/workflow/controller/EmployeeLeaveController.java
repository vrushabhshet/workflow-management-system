package com.workflow.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workflow.model.EmployeeLeave;
import com.workflow.repository.EmployeeLeaveRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/leave/{employee_id}")
public class EmployeeLeaveController {

	@Autowired
	EmployeeLeaveRepository employeLeaveRepository;

	@GetMapping("/")
	public ResponseEntity<List<EmployeeLeave>> getAllLeavesForAnEmployee(@PathVariable("employee_id") long employeeId) {

		List<EmployeeLeave> employeeLeaves = employeLeaveRepository.findByEmployeeId(employeeId);

		if (employeeLeaves.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(employeeLeaves, HttpStatus.OK);

	}

	@PostMapping("/")
	public ResponseEntity<EmployeeLeave> createLeaveForAnEmployee(@PathVariable("employee_id") long employeeId,
			@RequestBody EmployeeLeave employeeLeaveBody) {
		try {
			EmployeeLeave employeeLeave = employeLeaveRepository
					.save(new EmployeeLeave(employeeLeaveBody.getId(), employeeId, employeeLeaveBody.getFromDate(),
							employeeLeaveBody.getFromDate(), employeeLeaveBody.getReason()));
			return new ResponseEntity<>(employeeLeave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/")
	public ResponseEntity<EmployeeLeave> updateLeaveForEmployee(
			@PathVariable(name = "employee_id", required = true) long employeeId,
			@RequestParam(name = "id", required = true) long id, @RequestBody EmployeeLeave employeeLeaveBody) {
		Optional<EmployeeLeave> employeeLeave = employeLeaveRepository.findById(id);

		if (employeeLeave.isPresent()) {
			employeeLeaveBody.setId(id);
			employeeLeaveBody.setEmployeeId(employeeId);
			return new ResponseEntity<>(employeLeaveRepository.save(employeeLeaveBody), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/")
	public ResponseEntity<HttpStatus> deleteTutorial(
			@PathVariable(name = "employee_id", required = true) long employeeId,
			@RequestParam(name = "id", required = true) long id) {
		try {
			employeLeaveRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
