package com.agc.core.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agc.persistence.domain.User;

/**
 * @author lekhdm
 *
 */
@Component
public class UserServiceImpl implements UserService {
	private static List<User> storage = new ArrayList<User>();

	static {
		User u = new User();
		
		u.setId(1);
		u.setUsername("BobW");
		u.setPassword("Production");
		storage.add(u);
	}

	@Autowired
	private EmployeeService employeeService;
	
	/* (non-Javadoc)
	 * @see com.agc.core.services.UserService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public User login(String username, String password)
	{
		User u = getUser(username);
		
		if (u == null) {
			return(null);
		}

		if (!StringUtils.equals(u.getPassword(), password)) {
			return(null);
		}
		
		u.setEmployee(getEmployeeService().getEmployee(u.getId()));
		return(u);
	}
	
	private User getUser(String username)
	{
		BeanPropertyValueEqualsPredicate predicate = new BeanPropertyValueEqualsPredicate("username", username);
		User u = (User)CollectionUtils.find(storage, predicate);
		return(u);
	}

	/**
	 * @return the employeeService
	 */
	public EmployeeService getEmployeeService()
	{
		return employeeService;
	}

	/**
	 * @param employeeService the employeeService to set
	 */
	public void setEmployeeService(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}
}
