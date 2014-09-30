package com.agc.core.service;

import java.util.List;

import com.agc.persistence.domain.Employee;

/**
 * @author Dmitry Lekhtuz
 *
 */
public interface EmployeeService {
	public Employee getEmployee(int id);
	public Employee getEmployee(String username);
	public List<Employee> getAllEmployees();
	public int createEmployee(Employee employee);
}
