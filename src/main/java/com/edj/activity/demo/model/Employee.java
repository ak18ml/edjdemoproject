package com.edj.activity.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "Employee")
public class Employee {

	@Id
	@JsonProperty("_id")
	public String _id;
	public String name;
	public String gender;
	public String department;
	public String username;
	public String password;
	public int expectedSalary;

	public int getExpectedSalary() {
		return expectedSalary;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getDepartment() {
		return department;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setExpectedSalary(int expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Employee(String _id, String name, String gender, String department, String password, String username,
			int expectedSalary) {
		this._id = _id;
		this.name = name;
		this.gender = gender;
		this.department = department;
		this.password = password;
		this.username = username;
		this.expectedSalary = expectedSalary;
	}

	public Employee() {
	}
}
