package com.edj.activity.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edj.activity.demo.exception.CustomException;
import com.edj.activity.demo.model.Employee;
import com.edj.activity.demo.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Management", description = "Service for managing employee data")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping("/getEmployee/{name}")
	@Operation(summary = "Get employee details by name")
	public ResponseEntity<Employee> getEmployee(@PathVariable String name) throws CustomException {
		logger.info("Fetching employee details for name: {}", name);
		var employee = employeeService.getEmployee(name);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@PostMapping("/createEmployee")
	@Operation(summary = "Register employee")
	public ResponseEntity<String> registerEmployee(@RequestBody Employee employee) throws CustomException {
		var msg = employeeService.createEmployee(employee);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

	@PutMapping("/updateEmployee")
	@Operation(summary = "Update employee details by name")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) throws CustomException {
		var msg = employeeService.updateEmployee(employee);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/removeEmployee/{name}")
	@Operation(summary = "Remove employee details by name")
	public ResponseEntity<String> removeEmployee(@PathVariable String name) throws CustomException {
		var msg = employeeService.removeEmployee(name);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/getSecondHighestExpSalary")
	@Operation(summary = "Get employee details with second highest expected salary")
	public ResponseEntity<Employee> fetchSecondHighestSalary() throws CustomException{
		var employee = employeeService.getSecondHighestSalary();
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
}
