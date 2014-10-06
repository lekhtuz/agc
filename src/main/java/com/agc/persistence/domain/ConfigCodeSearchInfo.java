package com.agc.persistence.domain;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class ConfigCodeSearchInfo {
	private String configCode;
	private int series;
	private int groupNo;
	private String title;

	/**
	 * @return the configCode
	 */
	public String getConfigCode()
	{
		return configCode;
	}

	/**
	 * @param configCode the configCode to set
	 */
	public void setConfigCode(String configCode)
	{
		this.configCode = configCode;
	}

	/**
	 * @return the series
	 */
	public int getSeries()
	{
		return series;
	}

	/**
	 * @param series the series to set
	 */
	public void setSeries(int series)
	{
		this.series = series;
	}

	/**
	 * @return the groupNo
	 */
	public int getGroupNo()
	{
		return groupNo;
	}

	/**
	 * @param groupNo the groupNo to set
	 */
	public void setGroupNo(int groupNo)
	{
		this.groupNo = groupNo;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

}
