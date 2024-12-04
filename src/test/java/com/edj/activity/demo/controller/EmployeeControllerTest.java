package com.edj.activity.demo.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.edj.activity.demo.model.Employee;
import com.edj.activity.demo.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private Employee employee;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        employee = new Employee("123", "Ak", "M", "IT", "ak", "pass", 80000);
    }

    @Test
    public void testGetEmployee() throws Exception {
        when(employeeService.getEmployee("Ak")).thenReturn(employee);

        mockMvc.perform(get("/api/employees/getEmployee/Ak"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ak"))
                .andReturn();

        verify(employeeService, times(1)).getEmployee("Ak");
    }

    @Test
    public void testRegisterEmployee() throws Exception {
        when(employeeService.createEmployee(any(Employee.class))).thenReturn("Employee created successfully");

        mockMvc.perform(post("/api/employees/createEmployee")
                        .contentType("application/json")
                        .content("{\"name\":\"Ak\",\"gender\":\"M\",\"department\":\"IT\",\"username\":\"ak\",\"password\":\"pass\",\"expectedSalary\":80000}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value("Employee created successfully"));

        verify(employeeService, times(1)).createEmployee(any(Employee.class));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        when(employeeService.updateEmployee(any(Employee.class))).thenReturn("Employee updated successfully");

        mockMvc.perform(put("/api/employees/updateEmployee")
                        .contentType("application/json")
                        .content("{\"_id\":\"123\",\"name\":\"Ak\",\"gender\":\"M\",\"department\":\"IT\",\"username\":\"ak\",\"password\":\"pass\",\"expectedSalary\":65000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Employee updated successfully"));

        verify(employeeService, times(1)).updateEmployee(any(Employee.class));
    }

    @Test
    public void testRemoveEmployee() throws Exception {
        when(employeeService.removeEmployee("Ak")).thenReturn("Employee removed successfully");

        mockMvc.perform(delete("/api/employees/removeEmployee/Ak"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Employee removed successfully"));

        verify(employeeService, times(1)).removeEmployee("Ak");
    }

    @Test
    public void testFetchSecondHighestSalary() throws Exception {
        when(employeeService.getSecondHighestSalary()).thenReturn(employee);

        mockMvc.perform(get("/api/employees/getSecondHighestExpSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ak"));

        verify(employeeService, times(1)).getSecondHighestSalary();
    }
}

