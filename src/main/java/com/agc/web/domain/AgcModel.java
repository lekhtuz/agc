package com.agc.web.domain;

import java.io.Serializable;

/**
 * @author lekhdm
 *
 */
public class AgcModel implements Serializable {
	private static final long serialVersionUID = 2997203439717041775L;
	
	private boolean loggedIn;
	private LoginForm loginForm = new LoginForm();

	/**
	 * @return the loggedIn
	 */
	public boolean isLoggedIn()
	{
		return loggedIn;
	}

	/**
	 * @param loggedIn the loggedIn to set
	 */
	public void setLoggedIn(boolean loggedIn)
	{
		this.loggedIn = loggedIn;
	}

	/**
	 * @return the loginForm
	 */
	public LoginForm getLoginForm() {
		return loginForm;
	}

	/**
	 * @param loginForm the loginForm to set
	 */
	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("AgcModel [loggedIn=");
		builder.append(loggedIn);
		builder.append(", loginForm=");
		builder.append(loginForm);
		builder.append("]");
		return builder.toString();
	}

}
