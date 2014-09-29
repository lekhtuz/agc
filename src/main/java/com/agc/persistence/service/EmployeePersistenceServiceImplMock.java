package com.agc.persistence.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;

import com.agc.persistence.domain.Employee;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class EmployeePersistenceServiceImplMock implements EmployeePersistenceService {
	private static List<Employee> storage = new ArrayList<Employee>();
	private static int nextEmployeeIdSequence = 1;

	static {
		Employee e = new Employee();
		
		e.setId(1);
		e.setFirstName("Admin_first");
		e.setLastName("Admin_last");
		storage.add(e);
	}

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.EmployeePersistenceService#getEmployee(int)
	 */
	@Override
	public Employee getEmployee(int id)
	{
		BeanPropertyValueEqualsPredicate predicate = new BeanPropertyValueEqualsPredicate("id", id);
		Employee e = (Employee)CollectionUtils.find(storage, predicate);
		return(e);
	}

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.EmployeePersistenceService#getEmployee(java.lang.String)
	 */
	@Override
	public Employee getEmployee(String username)
	{
		BeanPropertyValueEqualsPredicate predicate = new BeanPropertyValueEqualsPredicate("username", username);
		Employee e = (Employee)CollectionUtils.find(storage, predicate);
		return(e);
	}

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.EmployeePersistenceService#getAllEmployees()
	 */
	@Override
	public List<Employee> getAllEmployees()
	{
		return(Collections.unmodifiableList(storage));
	}

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.EmployeePersistenceService#createEmployee(com.agc.persistence.domain.Employee)
	 */
	@Override
	public int createEmployee(Employee employee)
	{
		employee.setId(nextEmployeeIdSequence);
		storage.add(employee);
		return(nextEmployeeIdSequence ++);
	}
}
