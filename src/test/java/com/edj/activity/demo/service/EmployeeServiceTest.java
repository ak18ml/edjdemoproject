package com.edj.activity.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.edj.activity.demo.exception.CustomException;
import com.edj.activity.demo.model.Employee;
import com.edj.activity.demo.repository.EmployeeRepo;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeService employeeService;
	
	@Mock
	EmployeeRepo employeeRepo;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	private Employee employee;
	
	@BeforeEach
    public void setup() {
       employee = new Employee("12", "Ak","M", "HR", "ak", "pass", 50000);
    }
	
	@Test
	public void testGetEmployee() throws CustomException {
		var list = new ArrayList<Employee>();
		list.add(employee);
		
		when(employeeRepo.findLatestByName("Ak")).thenReturn(list);
		var returnedEmployee = employeeService.getEmployee(employee.getName());
		
		assertNotNull(returnedEmployee);
		assertEquals("Ak", returnedEmployee.getName());
	}
	
	@Test
	public void testCreateEmployee() throws CustomException {
		
		when(employeeRepo.findById(employee.get_id())).thenReturn(Optional.ofNullable(null));
		var msg = employeeService.createEmployee(employee);
		assertEquals("Successfully registered the employee",msg);
	}
	
	@Test
	public void testUpdateEmployee()throws CustomException{
		when(employeeRepo.findById(employee.get_id())).thenReturn(Optional.ofNullable(employee));
		var msg = employeeService.updateEmployee(employee);
		assertEquals("Successfully updated employee data",msg);
	}
	
	@Test
	public void testRemoveEmployee() throws CustomException{
		var list = new ArrayList<Employee>();
		list.add(employee);
		
		when(employeeRepo.findLatestByName("Ak")).thenReturn(list);
		
		var msg = employeeService.removeEmployee("Ak");
		
		assertEquals("Successfully deleted employee details",msg);
	}
	
	@Test
	public void testGetSecondHighestSalary() throws CustomException {
		var emp1 = new Employee("12", "Ak","M", "HR", "ak", "pass", 50000);
		var list = new ArrayList<Employee>();
		list.add(emp1);
		
		when(employeeRepo.findAllByOrderByExpectedSalaryDesc(PageRequest.of(1, 1))).thenReturn(list);
		var result = employeeService.getSecondHighestSalary();
		assertEquals(result.getName(),"Ak");
	}
}
