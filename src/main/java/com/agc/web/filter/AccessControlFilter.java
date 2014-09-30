package com.agc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agc.web.domain.AgcModel;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class AccessControlFilter implements Filter {
	private static final Log LOG = LogFactory.getLog(AccessControlFilter.class);
	private static final String WEBAPP_LOGIN_URL_KEY_PARAM_NAME = "login.url.key";

	private String loginUrl;

    /* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		String _M = "init(FilterConfig): ";
		LOG.debug(_M + "started. filterConfig=" + filterConfig);

		loginUrl = filterConfig.getInitParameter(WEBAPP_LOGIN_URL_KEY_PARAM_NAME);
		LOG.debug(_M + "loginUrl=" + loginUrl);
		
		if (!StringUtils.isNotBlank(loginUrl)) {
			throw new ServletException("Login URL redirect is not configured for AccessControlFilter");
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException
	{
		String _M = "doFilter(ServletRequest, ServletResponse, FilterChain): ";
		LOG.debug(_M + "started.");

		HttpServletRequest httpRequest = (HttpServletRequest)req;
		HttpServletResponse httpResponse = (HttpServletResponse)resp;
		String redirectUrl = httpRequest.getContextPath() + loginUrl;

		HttpSession session = httpRequest.getSession();
		if (session == null) {
			httpResponse.sendRedirect(httpResponse.encodeRedirectURL(redirectUrl));
			LOG.debug(_M + "ended. HttpSession does not exist.");
			return;
		}
		
		AgcModel agcModel = (AgcModel)session.getAttribute("scopedTarget.agcModel");
		if (agcModel == null || agcModel.getLoggedInUser() == null) {
			httpResponse.sendRedirect(httpResponse.encodeRedirectURL(redirectUrl));
			LOG.debug(_M + "ended. User is not logged in.");
			return;
		}

		LOG.debug(_M + "ended. User is logged in, sending request down the filter chain.");
		filterChain.doFilter(httpRequest, httpResponse);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy()
	{
		// Empty
	}
}
