package com.edj.activity.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edj.activity.demo.exception.CustomException;
import com.edj.activity.demo.model.Employee;
import com.edj.activity.demo.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	public Employee getEmployee(String name) throws CustomException {
		var emp = employeeRepo.findLatestByName(name);
		if (emp.isEmpty()) {
			throw new CustomException("Employee details with given name does not exist", HttpStatus.NOT_FOUND.value());
		}
		return emp.get(0);
	}

	public Employee getId(String id) throws CustomException {
		var emp = employeeRepo.findById(id)
				.orElseThrow(() -> new CustomException("Employee details with given Id does not exist",
						HttpStatus.NOT_FOUND.value()));
		return emp;
	}

	public List<Employee> getAll() {
		return employeeRepo.findAll();
	}

	public String createEmployee(Employee emp) throws CustomException {
		var msg = "";
		Optional<Employee> existingEmp = Optional.ofNullable(new Employee());
		emp.setPassword(passwordEncoder.encode(emp.getPassword()));
		if (!(emp.get_id() == null || emp.get_id().isBlank())) {
			existingEmp = employeeRepo.findById(emp.get_id());
			if (!existingEmp.isEmpty()) {
				throw new CustomException("Employee with same id already exists, please provide unique id",
						HttpStatus.BAD_REQUEST.value());
			}
		}
		msg = "Successfully registered the employee";
		employeeRepo.save(emp);
		return msg;
	}

	public String updateEmployee(Employee emp) throws CustomException {
		var updateEmp = employeeRepo.findById(emp.get_id())
				.orElseThrow(() -> new CustomException("Employee details with given Id does not exist",
						HttpStatus.NOT_FOUND.value()));

		updateEmp.setDepartment(emp.getDepartment());
		updateEmp.setExpectedSalary(emp.getExpectedSalary());
		updateEmp.setGender(emp.getGender());
		updateEmp.setName(emp.getName());
		updateEmp.setPassword(emp.getPassword());
		updateEmp.setUsername(emp.getUsername());
		employeeRepo.save(updateEmp);
		return "Successfully updated employee data";
	}

	public String removeEmployee(String name) throws CustomException {
		var emp = employeeRepo.findLatestByName(name);
		if (emp.isEmpty()) {
			throw new CustomException("Employee details with given name does not exist", HttpStatus.NOT_FOUND.value());
		}
		employeeRepo.delete(emp.get(0));
		return "Successfully deleted employee details";
	}

	public Employee getSecondHighestSalary() throws CustomException {
		var employees = employeeRepo.findAllByOrderByExpectedSalaryDesc(PageRequest.of(1, 1));

		if (employees.isEmpty()) {
			throw new CustomException("Not enough employee data to determine the second highest salary",
					HttpStatus.NO_CONTENT.value());
		}

		return employees.get(0);
	}

}