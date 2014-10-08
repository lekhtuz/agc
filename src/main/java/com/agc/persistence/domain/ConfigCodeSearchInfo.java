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
	private int priceGridNo;
	private int priceGridNo2;

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

	/**
	 * @return the priceGridNo
	 */
	public int getPriceGridNo()
	{
		return priceGridNo;
	}

	/**
	 * @param priceGridNo the priceGridNo to set
	 */
	public void setPriceGridNo(int priceGridNo)
	{
		this.priceGridNo = priceGridNo;
	}

	/**
	 * @return the priceGridNo2
	 */
	public int getPriceGridNo2()
	{
		return priceGridNo2;
	}

	/**
	 * @param priceGridNo2 the priceGridNo2 to set
	 */
	public void setPriceGridNo2(int priceGridNo2)
	{
		this.priceGridNo2 = priceGridNo2;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("ConfigCodeSearchInfo [configCode=");
		builder.append(configCode);
		builder.append(", series=");
		builder.append(series);
		builder.append(", groupNo=");
		builder.append(groupNo);
		builder.append(", title=");
		builder.append(title);
		builder.append(", priceGridNo=");
		builder.append(priceGridNo);
		builder.append(", priceGridNo2=");
		builder.append(priceGridNo2);
		builder.append("]");
		return builder.toString();
	}
}
