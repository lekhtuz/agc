package com.agc.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agc.persistence.domain.Employee;
import com.agc.persistence.service.EmployeePersistenceService;

/**
 * @author Dmitry Lekhtuz
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeePersistenceService employeePersistenceService;

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#getEmployee(int)
	 */
	public Employee getEmployee(int id)
	{
		Employee e = employeePersistenceService.getEmployee(id);
		return(e);
	}

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#getEmployee(String)
	 */
	public Employee getEmployee(String username)
	{
		Employee e = employeePersistenceService.getEmployee(username);
		return(e);
	}

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#getAllEmployees()
	 */
	public List<Employee> getAllEmployees()
	{
		List<Employee> list = employeePersistenceService.getAllEmployees();
		return(list);
	}

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#createEmployee(Employee)
	 */
	public int createEmployee(Employee employee)
	{
		int employeeId = employeePersistenceService.createEmployee(employee);
		return(employeeId);
	}

}
