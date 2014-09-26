package com.agc.core.services;

import java.util.List;

import com.agc.persistence.domain.Employee;

/**
 * @author lekhdm
 *
 */
public interface EmployeeService {
	public Employee getEmployee(int id);
	public Employee getEmployee(String username);
	public List<Employee> getAllEmployees();
	public int createEmployee(Employee employee);
}
