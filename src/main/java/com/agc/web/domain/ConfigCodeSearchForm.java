package com.agc.web.domain;

import java.util.Arrays;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class ConfigCodeSearchForm {
	private String configCode;
	private int selectedSeries[];

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
	 * @return the selectedSeries
	 */
	public int[] getSelectedSeries()
	{
		return selectedSeries;
	}

	/**
	 * @param selectedSeries the selectedSeries to set
	 */
	public void setSelectedSeries(int[] selectedSeries)
	{
		this.selectedSeries = selectedSeries;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("ConfigCodeSearchForm [configCode=");
		builder.append(configCode);
		builder.append(", selectedSeries=");
		builder.append(Arrays.toString(selectedSeries));
		builder.append("]");
		return builder.toString();
	}

}
