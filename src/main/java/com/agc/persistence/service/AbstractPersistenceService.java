package com.agc.persistence.service;

import com.agc.persistence.adapter.DatabaseAdapter;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class AbstractPersistenceService {
	private String tableName;
	private DatabaseAdapter dbAdapter;
	
	/**
	 * @return the tableName
	 */
	public String getTableName()
	{
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	/**
	 * @return the dbAdapter
	 */
	public DatabaseAdapter getDbAdapter()
	{
		return dbAdapter;
	}
	/**
	 * @param dbAdapter the dbAdapter to set
	 */
	public void setDbAdapter(DatabaseAdapter dbAdapter)
	{
		this.dbAdapter = dbAdapter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractPersistentService [dbAdapter=");
		builder.append(dbAdapter);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append("]");
		return builder.toString();
	}
}
