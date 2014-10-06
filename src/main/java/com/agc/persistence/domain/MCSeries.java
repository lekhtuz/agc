package com.agc.persistence.domain;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class MCSeries {
	private int id;
	private String title;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("MCSeries [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}

}
