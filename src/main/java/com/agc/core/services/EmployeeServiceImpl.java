package com.agc.core.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;

import org.springframework.stereotype.Component;

import com.agc.persistence.domain.Employee;

/**
 * @author lekhdm
 *
 */
@Component
public class EmployeeServiceImpl implements EmployeeService {
	private static List<Employee> storage = new ArrayList<Employee>();

	static {
		Employee e = new Employee();
		
		e.setId(1);
		e.setFirstName("Admin_first");
		e.setLastName("Admin_last");
		storage.add(e);
	}

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#getEmployee(int)
	 */
	public Employee getEmployee(int id)
	{
		BeanPropertyValueEqualsPredicate predicate = new BeanPropertyValueEqualsPredicate("id", id);
		Employee e = (Employee)CollectionUtils.find(storage, predicate);
		return(e);
	}

	public Employee getEmployee(String username)
	{
		BeanPropertyValueEqualsPredicate predicate = new BeanPropertyValueEqualsPredicate("username", username);
		Employee e = (Employee)CollectionUtils.find(storage, predicate);
		return(e);
	}

	/* (non-Javadoc)
	 * @see com.agc.core.services.EmployeePersistenceService#getAllEmployees()
	 */
	public List<Employee> getAllEmployees()
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
