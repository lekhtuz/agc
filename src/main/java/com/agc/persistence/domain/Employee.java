package com.agc.persistence.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class Employee {
	private int id;

	@NotNull
	@NotEmpty
	private String firstName;

	@NotNull
	@NotEmpty
	private String lastName;

	private String middleName;
	private int badgeNo;
	private Date hireDate0;
	private Date hireDate1;
	private Date hireDate2;
	private Date hireDate3;
	private Date termDate0;
	private Date termDate1;
	private Date termDate2;
	private Date termDate3;
	private boolean gcEmployee;
	private boolean install;
	private boolean help;
	private boolean measure;
	private boolean shop;
	private boolean office;
	private boolean salesPerson;
	private boolean active;
	private String badgeTitle;
	private boolean driverLic;
	private Date dateCreated;
	private String createdBy;
	private Date dateModified;
	private String modifiedBy;

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
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @return the badgeNo
	 */
	public int getBadgeNo()
	{
		return badgeNo;
	}

	/**
	 * @param badgeNo the badgeNo to set
	 */
	public void setBadgeNo(int badgeNo)
	{
		this.badgeNo = badgeNo;
	}

	/**
	 * @return the hireDate0
	 */
	public Date getHireDate0()
	{
		return hireDate0;
	}

	/**
	 * @param hireDate0 the hireDate0 to set
	 */
	public void setHireDate0(Date hireDate0)
	{
		this.hireDate0 = hireDate0;
	}

	/**
	 * @return the hireDate1
	 */
	public Date getHireDate1()
	{
		return hireDate1;
	}

	/**
	 * @param hireDate1 the hireDate1 to set
	 */
	public void setHireDate1(Date hireDate1)
	{
		this.hireDate1 = hireDate1;
	}

	/**
	 * @return the hireDate2
	 */
	public Date getHireDate2()
	{
		return hireDate2;
	}

	/**
	 * @param hireDate2 the hireDate2 to set
	 */
	public void setHireDate2(Date hireDate2)
	{
		this.hireDate2 = hireDate2;
	}

	/**
	 * @return the hireDate3
	 */
	public Date getHireDate3()
	{
		return hireDate3;
	}

	/**
	 * @param hireDate3 the hireDate3 to set
	 */
	public void setHireDate3(Date hireDate3)
	{
		this.hireDate3 = hireDate3;
	}

	/**
	 * @return the termDate0
	 */
	public Date getTermDate0()
	{
		return termDate0;
	}

	/**
	 * @param termDate0 the termDate0 to set
	 */
	public void setTermDate0(Date termDate0)
	{
		this.termDate0 = termDate0;
	}

	/**
	 * @return the termDate1
	 */
	public Date getTermDate1()
	{
		return termDate1;
	}

	/**
	 * @param termDate1 the termDate1 to set
	 */
	public void setTermDate1(Date termDate1)
	{
		this.termDate1 = termDate1;
	}

	/**
	 * @return the termDate2
	 */
	public Date getTermDate2()
	{
		return termDate2;
	}

	/**
	 * @param termDate2 the termDate2 to set
	 */
	public void setTermDate2(Date termDate2)
	{
		this.termDate2 = termDate2;
	}

	/**
	 * @return the termDate3
	 */
	public Date getTermDate3()
	{
		return termDate3;
	}

	/**
	 * @param termDate3 the termDate3 to set
	 */
	public void setTermDate3(Date termDate3)
	{
		this.termDate3 = termDate3;
	}

	/**
	 * @return the gcEmployee
	 */
	public boolean isGcEmployee()
	{
		return gcEmployee;
	}

	/**
	 * @param gcEmployee the gcEmployee to set
	 */
	public void setGcEmployee(boolean gcEmployee)
	{
		this.gcEmployee = gcEmployee;
	}

	/**
	 * @return the install
	 */
	public boolean isInstall()
	{
		return install;
	}

	/**
	 * @param install the install to set
	 */
	public void setInstall(boolean install)
	{
		this.install = install;
	}

	/**
	 * @return the help
	 */
	public boolean isHelp()
	{
		return help;
	}

	/**
	 * @param help the help to set
	 */
	public void setHelp(boolean help)
	{
		this.help = help;
	}

	/**
	 * @return the measure
	 */
	public boolean isMeasure()
	{
		return measure;
	}

	/**
	 * @param measure the measure to set
	 */
	public void setMeasure(boolean measure)
	{
		this.measure = measure;
	}

	/**
	 * @return the shop
	 */
	public boolean isShop()
	{
		return shop;
	}

	/**
	 * @param shop the shop to set
	 */
	public void setShop(boolean shop)
	{
		this.shop = shop;
	}

	/**
	 * @return the office
	 */
	public boolean isOffice()
	{
		return office;
	}

	/**
	 * @param office the office to set
	 */
	public void setOffice(boolean office)
	{
		this.office = office;
	}

	/**
	 * @return the salesPerson
	 */
	public boolean isSalesPerson()
	{
		return salesPerson;
	}

	/**
	 * @param salesPerson the salesPerson to set
	 */
	public void setSalesPerson(boolean salesPerson)
	{
		this.salesPerson = salesPerson;
	}

	/**
	 * @return the active
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}

	/**
	 * @return the badgeTitle
	 */
	public String getBadgeTitle()
	{
		return badgeTitle;
	}

	/**
	 * @param badgeTitle the badgeTitle to set
	 */
	public void setBadgeTitle(String badgeTitle)
	{
		this.badgeTitle = badgeTitle;
	}

	/**
	 * @return the driverLic
	 */
	public boolean isDriverLic()
	{
		return driverLic;
	}

	/**
	 * @param driverLic the driverLic to set
	 */
	public void setDriverLic(boolean driverLic)
	{
		this.driverLic = driverLic;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated()
	{
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	/**
	 * @return the dateModified
	 */
	public Date getDateModified()
	{
		return dateModified;
	}

	/**
	 * @param dateModified the dateModified to set
	 */
	public void setDateModified(Date dateModified)
	{
		this.dateModified = dateModified;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy()
	{
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", middleName=");
		builder.append(middleName);
		builder.append(", badgeNo=");
		builder.append(badgeNo);
		builder.append(", hireDate0=");
		builder.append(hireDate0);
		builder.append(", hireDate1=");
		builder.append(hireDate1);
		builder.append(", hireDate2=");
		builder.append(hireDate2);
		builder.append(", hireDate3=");
		builder.append(hireDate3);
		builder.append(", termDate0=");
		builder.append(termDate0);
		builder.append(", termDate1=");
		builder.append(termDate1);
		builder.append(", termDate2=");
		builder.append(termDate2);
		builder.append(", termDate3=");
		builder.append(termDate3);
		builder.append(", gcEmployee=");
		builder.append(gcEmployee);
		builder.append(", install=");
		builder.append(install);
		builder.append(", help=");
		builder.append(help);
		builder.append(", measure=");
		builder.append(measure);
		builder.append(", shop=");
		builder.append(shop);
		builder.append(", office=");
		builder.append(office);
		builder.append(", salesPerson=");
		builder.append(salesPerson);
		builder.append(", active=");
		builder.append(active);
		builder.append(", badgeTitle=");
		builder.append(badgeTitle);
		builder.append(", driverLic=");
		builder.append(driverLic);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", dateModified=");
		builder.append(dateModified);
		builder.append(", modifiedBy=");
		builder.append(modifiedBy);
		builder.append("]");
		return builder.toString();
	}

}
