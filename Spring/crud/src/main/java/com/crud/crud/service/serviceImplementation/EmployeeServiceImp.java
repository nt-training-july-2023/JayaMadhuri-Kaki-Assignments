package com.crud.crud.service.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crud.crud.entity.Employee;
import com.crud.crud.repository.EmployeeRepo;
import com.crud.crud.service.EmployeeService;

@Service
public class EmployeeServiceImp implements EmployeeService {
    EmployeeRepo r;
     EmployeeServiceImp(EmployeeRepo r){
    	 this.r=r;
     }
	@Override
	public Optional<Employee> getEmployeeById(int id) {
		return r.findById(id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return r.findAll();
	}

	@Override
	public void addEmployees(Employee employee) {
		r.save(employee);
		
	}

	@Override
	public Employee updateEmployee(Employee employee, int id) {
		return r.save(employee);
	}

	@Override
	public void deleteEmployee(int id) {
		r.deleteById(id);
	}

}
