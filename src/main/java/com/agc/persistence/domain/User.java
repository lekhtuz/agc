package com.agc.persistence.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class User {
	public enum UserType { UnRestricted, Restricted };

	private int id;
	private Employee employee;
	private UserType userType;

	@NotNull @NotEmpty
	private String username;

	@NotNull @NotEmpty
	private String password;

	/**
	 * @return the id
	 */
	public int getId() 
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) 
	{
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee()
	{
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}

	/**
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", employee=");
		builder.append(employee);
		builder.append("]");
		return builder.toString();
	}

}
