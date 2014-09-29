package com.agc.persistence.adapters;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.agc.persistence.domain.PersistentStorageUnavailableException;
import com.agc.persistence.domain.RecordSet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class DreamFactoryRestAdapterImpl implements DatabaseAdapter {
	private static final Log LOG = LogFactory.getLog(DreamFactoryRestAdapterImpl.class);
	private static Gson gson = new Gson();

	private String baseUrl;
	private String applicationName;
	private String applicationNameHeader;
	private String sessionTokenHeader;
	private String sessionUrl;
	private String loginEmail;
	private String loginPassword;
	private int connectTimeoutSeconds;

	private String sessionId;	// Connection session id, access must be synchronized properly

	/**
	 * @throws PersistentStorageUnavailableException
	 */
	public void connect() throws PersistentStorageUnavailableException
	{
		String _M = "connect(): ";
		LOG.info(_M + "preparing to establish new session");
		String url = getBaseUrl() + getSessionUrl();
		LOG.info(_M + "url = " + url);

		HttpPost req = new HttpPost(url);
		req.addHeader(getApplicationNameHeader(), getApplicationName());
		req.addHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());
		SessionRequest sessionRequest = new SessionRequest(getLoginEmail(), getLoginPassword());
		StringEntity se = new StringEntity(gson.toJson(sessionRequest), ContentType.APPLICATION_JSON);
		req.setEntity(se);

		CloseableHttpResponse resp = null;
		LOG.info(_M + "about to execute request");
		try {
			resp = getHttpClient().execute(req);
			LOG.info(_M + "request executed. response=" + resp);
			sessionId = processSessionResponse(resp);
			LOG.info(_M + "responsed parsed. sessionId=" + sessionId);
		} catch (IOException e) {
			LOG.fatal(_M + "unable to connect to or parse response returned from DreamFactory Service Provider", e);
			sessionId = null;
			throw new PersistentStorageUnavailableException(e);
		} finally {
			try {
				resp.close();
			} catch (IOException e) {
				LOG.warn(_M + "unable to close CloseableHttpResponse", e);
			}
		}
	}
	
	/**
	 * @return
	 */
	private CloseableHttpClient getHttpClient()
	{
		int connectTimeoutMilliseconds = connectTimeoutSeconds * 1000;
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(connectTimeoutMilliseconds)
				.setConnectionRequestTimeout(connectTimeoutMilliseconds)
				.setSocketTimeout(connectTimeoutMilliseconds)
				.build();
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
		return(httpClient);
	}

	/**
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	private String processSessionResponse(CloseableHttpResponse resp) throws IOException
	{
		String _M = "processSessionResponse(): ";
		LOG.info(_M + "started");
		Map<String, Object> sessionInfoMap = processJsonResponse(resp);
		LOG.info(_M + "sessionInfoMap=" + sessionInfoMap);
		Date ticketExpiry = new Date(MapUtils.getLongValue(sessionInfoMap, "tiket_expiry"));
		LOG.info(_M + "ticketExpiry=" + ticketExpiry);
		String sessionId = MapUtils.getString(sessionInfoMap, "session_id");
		LOG.info(_M + "ended. sessionId=" + sessionId);
		return(sessionId);
	}

	/**
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	private Map<String, Object> processJsonResponse(CloseableHttpResponse resp) throws IOException
	{
		String _M = "processJsonResponse(): ";
		LOG.info(_M + "started");
		String sessionInfo = EntityUtils.toString(resp.getEntity());
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> sessionInfoMap = gson.fromJson(sessionInfo, type);
		LOG.info(_M + "ended. sessionInfoMap=" + sessionInfoMap);
		return(sessionInfoMap);
	}
	
	/**
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	private RecordSet processRecordSetResponse(CloseableHttpResponse resp) throws IOException
	{
		String _M = "processRecordSetResponse(): ";
		LOG.info(_M + "started");
		Map<String, Object> recordSetMap = processJsonResponse(resp);
		LOG.info(_M + "recordSetMap=" + recordSetMap);
		@SuppressWarnings("unchecked")
		RecordSet recordSet = new RecordSet((List<Map<String, Object>>)MapUtils.getObject(recordSetMap, "record"));
		LOG.info(_M + "ended. recordSet=" + recordSet);
		return(recordSet);
	}

	/* (non-Javadoc)
	 * @see com.agc.persistence.adapters.DatabaseAdapter#getRecordSet(java.lang.String, java.lang.String)
	 */
	@Override
	public RecordSet getRecordSet(String tableName, String whereClause)
	{
		String _M = "getRecordsFromTable(): ";
		LOG.debug(_M + "started. tableName=" + tableName);
		
		synchronized(this) {
			if (sessionId == null) {
				connect();
			}
		}

		URI uri = null;
		try {
			uri = new URIBuilder(getBaseUrl() + "/" + getApplicationName() + "/" + tableName).addParameter("filter", whereClause).build();
		} catch (URISyntaxException e) {
			// We should never be here. If we are, check DreamFactory url configuration settings.
		}

		HttpGet req = new HttpGet(uri);
		req.addHeader(getSessionTokenHeader(), sessionId);
		req.addHeader(getApplicationNameHeader(), getApplicationName());
		req.addHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());

		CloseableHttpResponse resp = null;
		RecordSet recordSet = null;
		LOG.info(_M + "about to execute request");
		try {
			resp = getHttpClient().execute(req);
			LOG.info(_M + "request executed. response=" + resp);
			recordSet = processRecordSetResponse(resp);
			LOG.info(_M + "responsed parsed. recordSet=" + recordSet);
		} catch (IOException e) {
			LOG.fatal(_M + "unable to connect to or parse response returned from DreamFactory Service Provider", e);
			sessionId = null;
			throw new PersistentStorageUnavailableException(e);
		} finally {
			try {
				resp.close();
			} catch (IOException e) {
				LOG.warn(_M + "unable to close CloseableHttpResponse", e);
			}
		}

		LOG.debug(_M + "ended.");
		return(recordSet);
	}

	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl()
	{
		return baseUrl;
	}

	/**
	 * @param baseUrl the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl)
	{
		this.baseUrl = baseUrl;
	}

	/**
	 * @return the applicationName
	 */
	public String getApplicationName()
	{
		return applicationName;
	}

	/**
	 * @param applicationName the applicationName to set
	 */
	public void setApplicationName(String applicationName)
	{
		this.applicationName = applicationName;
	}

	/**
	 * @return the applicationNameHeader
	 */
	public String getApplicationNameHeader() {
		return applicationNameHeader;
	}

	/**
	 * @param applicationNameHeader the applicationNameHeader to set
	 */
	public void setApplicationNameHeader(String applicationNameHeader) {
		this.applicationNameHeader = applicationNameHeader;
	}

	/**
	 * @return the sessionTokenHeader
	 */
	public String getSessionTokenHeader() {
		return sessionTokenHeader;
	}

	/**
	 * @param sessionTokenHeader the sessionTokenHeader to set
	 */
	public void setSessionTokenHeader(String sessionTokenHeader) {
		this.sessionTokenHeader = sessionTokenHeader;
	}

	/**
	 * @return the sessionUrl
	 */
	public String getSessionUrl()
	{
		return sessionUrl;
	}

	/**
	 * @param sessionUrl the sessionUrl to set
	 */
	public void setSessionUrl(String sessionUrl)
	{
		this.sessionUrl = sessionUrl;
	}

	/**
	 * @return the loginEmail
	 */
	public String getLoginEmail() {
		return loginEmail;
	}

	/**
	 * @param loginEmail the loginEmail to set
	 */
	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	/**
	 * @return the loginPassword
	 */
	public String getLoginPassword() {
		return loginPassword;
	}

	/**
	 * @param loginPassword the loginPassword to set
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	/**
	 * @return the connectTimeoutSeconds
	 */
	public int getConnectTimeoutSeconds() {
		return connectTimeoutSeconds;
	}

	/**
	 * @param connectTimeoutSeconds the connectTimeoutSeconds to set
	 */
	public void setConnectTimeoutSeconds(int connectTimeoutSeconds) {
		this.connectTimeoutSeconds = connectTimeoutSeconds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DreamFactoryRestAdapterImpl [baseUrl=");
		builder.append(baseUrl);
		builder.append(", applicationName=");
		builder.append(applicationName);
		builder.append(", applicationNameHeader=");
		builder.append(applicationNameHeader);
		builder.append(", sessionTokenHeader=");
		builder.append(sessionTokenHeader);
		builder.append(", sessionUrl=");
		builder.append(sessionUrl);
		builder.append(", loginEmail=");
		builder.append(loginEmail);
		builder.append(", loginPassword=");
		builder.append(loginPassword);
		builder.append(", connectTimeoutSeconds=");
		builder.append(connectTimeoutSeconds);
		builder.append(", sessionId=");
		builder.append(sessionId);
		builder.append("]");
		return builder.toString();
	}

	private static class SessionRequest {
		private String email;
		private String password;

		/**
		 * @param email
		 * @param password
		 */
		public SessionRequest(String email, String password)
		{
			this.email = email;
			this.password = password;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("SessionRequest [email=");
			builder.append(email);
			builder.append(", password=");
			builder.append(password);
			builder.append("]");
			return builder.toString();
		}
	}
}
