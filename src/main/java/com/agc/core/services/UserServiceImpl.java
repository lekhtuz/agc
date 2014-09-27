package com.agc.core.services;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agc.persistence.domain.User;
import com.agc.persistence.service.UserPersistenceService;

/**
 * @author Dmitry Lekhtuz
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserPersistenceService userPersistenceService;
	
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
		
		u.setEmployee(employeeService.getEmployee(u.getId()));
		return(u);
	}
	
	private User getUser(String username)
	{
		User u = userPersistenceService.getUser(username);
		return(u);
	}
}
