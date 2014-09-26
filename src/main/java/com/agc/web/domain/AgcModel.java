package com.agc.web.domain;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.agc.persistence.domain.User;

/**
 * @author lekhdm
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AgcModel implements Serializable {
	private static final long serialVersionUID = 2997203439717041775L;

	private User loggedInUser;

	/**
	 * @return the loggedInUser
	 */
	public User getLoggedInUser()
	{
		return loggedInUser;
	}

	/**
	 * @param loggedInUser the loggedInUser to set
	 */
	public void setLoggedInUser(User loggedInUser)
	{
		this.loggedInUser = loggedInUser;
	}

}
