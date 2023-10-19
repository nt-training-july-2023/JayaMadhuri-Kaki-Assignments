package com.crud.crud.service;

import java.util.List;
import java.util.Optional;

import com.crud.crud.entity.Employee;

public interface EmployeeService {
	Optional<Employee> getEmployeeById(int id);
	List<Employee> getAllEmployees();
	void addEmployees(Employee employee);
	Employee updateEmployee(Employee employee, int id);
	void deleteEmployee(int id);
}
