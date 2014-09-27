package com.agc.persistence.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.client.RestTemplate;

import com.agc.persistence.domain.JSONRecordSet;
import com.agc.persistence.domain.JSONUser;
import com.agc.persistence.domain.User;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class UserPersistenceServiceImplDF implements UserPersistenceService {
	private static final Log LOG = LogFactory.getLog(UserPersistenceServiceImplDF.class);
	private String baseUrl;
	private String tableName;

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.UserPersistenceService#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String username)
	{
		RestTemplate rt = new RestTemplate();
		String url = getBaseUrl() + "/" + getTableName();
		LOG.debug("getUser(): url=" + url);
		JSONRecordSet jsonRecordSet = rt.getForObject(url, JSONRecordSet.class);
		return null;
	}

	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserPersistenceServiceImplDF [baseUrl=");
		builder.append(baseUrl);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append("]");
		return builder.toString();
	}
}
