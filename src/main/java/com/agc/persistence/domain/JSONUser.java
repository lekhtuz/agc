package com.agc.persistence.domain;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class JSONUser {
	private String UsUserId;
	private int UsEmployeeNo;
	private String UsUserPassword;
	private String UsUserType;

	/**
	 * @return the usUserId
	 */
	public String getUsUserId()
	{
		return UsUserId;
	}

	/**
	 * @param usUserId the usUserId to set
	 */
	public void setUsUserId(String usUserId)
	{
		UsUserId = usUserId;
	}

	/**
	 * @return the usEmployeeNo
	 */
	public int getUsEmployeeNo()
	{
		return UsEmployeeNo;
	}

	/**
	 * @param usEmployeeNo the usEmployeeNo to set
	 */
	public void setUsEmployeeNo(int usEmployeeNo)
	{
		UsEmployeeNo = usEmployeeNo;
	}

	/**
	 * @return the usUserPassword
	 */
	public String getUsUserPassword()
	{
		return UsUserPassword;
	}

	/**
	 * @param usUserPassword the usUserPassword to set
	 */
	public void setUsUserPassword(String usUserPassword)
	{
		UsUserPassword = usUserPassword;
	}

	/**
	 * @return the usUserType
	 */
	public String getUsUserType()
	{
		return UsUserType;
	}

	/**
	 * @param usUserType the usUserType to set
	 */
	public void setUsUserType(String usUserType)
	{
		UsUserType = usUserType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JSONUser [UsUserId=");
		builder.append(UsUserId);
		builder.append(", UsEmployeeNo=");
		builder.append(UsEmployeeNo);
		builder.append(", UsUserPassword=");
		builder.append(UsUserPassword);
		builder.append(", UsUserType=");
		builder.append(UsUserType);
		builder.append("]");
		return builder.toString();
	}
}
