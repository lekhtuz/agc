package com.agc.core.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.agc.persistence.domain.Employee;

/**
 * @author lekhdm
 *
 */
@Component
public class EmployeeServiceImpl implements EmployeeService {
	private List<Employee> storage = new ArrayList<Employee>();

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#getEmployee(int)
	 */
	public Employee getEmployee(int id)
	{
		return(storage.get(id));
	}

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#search()
	 */
	public List<Employee> search()
	{
		return(Collections.unmodifiableList(storage));
	}

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#createEmployee(Employee)
	 */
	public int createEmployee(Employee employee)
	{
		storage.add(employee);
		return(storage.size() - 1);
	}

}
