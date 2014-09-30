package com.agc.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agc.persistence.domain.Employee;
import com.agc.persistence.service.EmployeePersistenceService;

/**
 * @author Dmitry Lekhtuz
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeePersistenceService employeePersistenceService;

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#getEmployee(int)
	 */
	public Employee getEmployee(int id)
	{
		Employee e = getEmployeePersistenceService().getEmployee(id);
		return(e);
	}

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#getEmployee(String)
	 */
	public Employee getEmployee(String username)
	{
		Employee e = getEmployeePersistenceService().getEmployee(username);
		return(e);
	}

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#getAllEmployees()
	 */
	public List<Employee> getAllEmployees()
	{
		List<Employee> list = getEmployeePersistenceService().getAllEmployees();
		return(list);
	}

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#createEmployee(Employee)
	 */
	public int createEmployee(Employee employee)
	{
		int employeeId = getEmployeePersistenceService().createEmployee(employee);
		return(employeeId);
	}

	/**
	 * @return the employeePersistenceService
	 */
	public EmployeePersistenceService getEmployeePersistenceService()
	{
		return employeePersistenceService;
	}

	/**
	 * @param employeePersistenceService the employeePersistenceService to set
	 */
	public void setEmployeePersistenceService(EmployeePersistenceService employeePersistenceService)
	{
		this.employeePersistenceService = employeePersistenceService;
	}

}
