package com.agc.persistence.domain;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class PriceGridModel {
	private int series;
	private int priceGridNo;
	private int glassType;
	private int height;
	private int width;
	private int width2;
	private boolean headerRequired;
	private int price;

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
	 * @return the glassType
	 */
	public int getGlassType()
	{
		return glassType;
	}

	/**
	 * @param glassType the glassType to set
	 */
	public void setGlassType(int glassType)
	{
		this.glassType = glassType;
	}

	/**
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * @return the width2
	 */
	public int getWidth2()
	{
		return width2;
	}

	/**
	 * @param width2 the width2 to set
	 */
	public void setWidth2(int width2)
	{
		this.width2 = width2;
	}

	/**
	 * @return the headerRequired
	 */
	public boolean isHeaderRequired()
	{
		return headerRequired;
	}

	/**
	 * @param headerRequired the headerRequired to set
	 */
	public void setHeaderRequired(boolean headerRequired)
	{
		this.headerRequired = headerRequired;
	}

	/**
	 * @return the price
	 */
	public int getPrice()
	{
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price)
	{
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("PriceGridModel [series=");
		builder.append(series);
		builder.append(", priceGridNo=");
		builder.append(priceGridNo);
		builder.append(", glassType=");
		builder.append(glassType);
		builder.append(", height=");
		builder.append(height);
		builder.append(", width=");
		builder.append(width);
		builder.append(", width2=");
		builder.append(width2);
		builder.append(", headerRequired=");
		builder.append(headerRequired);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
}
