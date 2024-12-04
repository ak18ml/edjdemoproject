package com.edj.activity.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.edj.activity.demo.model.Employee;

public interface EmployeeRepo extends MongoRepository<Employee, String> {

	@Query(value = "{ 'name': ?0 }", sort = "{ '_id': -1 }")
    List<Employee> findLatestByName(String name);

    
    List<Employee> findAllByOrderByExpectedSalaryDesc(Pageable pageable);
}
