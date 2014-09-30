package com.agc.core.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.agc.persistence.domain.User;
import com.agc.persistence.service.UserPersistenceService;

/**
 * @author Dmitry Lekhtuz
 *
 */
@Service
public class UserServiceImpl implements UserService {
	private EmployeeService employeeService;
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

	/**
	 * @return the userPersistenceService
	 */
	public UserPersistenceService getUserPersistenceService()
	{
		return userPersistenceService;
	}

	/**
	 * @param userPersistenceService the userPersistenceService to set
	 */
	public void setUserPersistenceService(UserPersistenceService userPersistenceService)
	{
		this.userPersistenceService = userPersistenceService;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserServiceImpl [employeeService=");
		builder.append(employeeService);
		builder.append(", userPersistenceService=");
		builder.append(userPersistenceService);
		builder.append("]");
		return builder.toString();
	}
}
