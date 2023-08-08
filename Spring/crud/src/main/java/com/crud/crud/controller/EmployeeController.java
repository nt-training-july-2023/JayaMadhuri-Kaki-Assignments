package com.crud.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crud.entity.Employee;
import com.crud.crud.service.serviceImplementation.EmployeeServiceImp;

@RestController
public class EmployeeController{
	@Autowired
	EmployeeServiceImp impl;
	
	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmpById(@PathVariable int id) {
		return impl.getEmployeeById(id);
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmp() {
		return impl.getAllEmployees();
	}

	@PostMapping("/employees") 
	public void addEmp(@RequestBody List<Employee> employee) {
		impl.addEmployees(employee);
		
	}

	@PutMapping("/employees/{id}") 
	public Employee updateEmp(@RequestBody Employee employee,@PathVariable int id) {
		return impl.updateEmployee(employee,id);
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmp(@PathVariable int id) {
		impl.deleteEmployee(id);
		
	}
}