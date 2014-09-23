package com.agc.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agc.web.domain.AgcModel;
import com.agc.web.domain.LoginForm;

/**
 * @author lekhdm
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Log LOG = LogFactory.getLog(LoginController.class);

	@Autowired
	private AgcModel agcModel;

	@RequestMapping(method = RequestMethod.POST)
	public String doPostLogin(@ModelAttribute("loginForm") LoginForm loginForm, ModelMap model)
	{
		LOG.debug("doPostLogin(): agcModel=" + agcModel);
		LOG.debug("doPostLogin(): loginForm=" + loginForm);
		return("redirect:/");
	}

	/**
	 * @return the agcModel
	 */
	@ModelAttribute("agcModel")
	private AgcModel getAgcModel()
	{
		return(agcModel);
	}
	
	@ModelAttribute("loginForm")
	private LoginForm getLoginForm()
	{
		return(new LoginForm());
	}
}
