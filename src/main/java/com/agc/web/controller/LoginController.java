package com.agc.web.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agc.core.service.UserService;
import com.agc.persistence.domain.PersistentStorageUnavailableException;
import com.agc.persistence.domain.User;
import com.agc.web.domain.AgcModel;
import com.agc.web.domain.LoginForm;

/**
 * @author Dmitry Lekhtuz
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Log LOG = LogFactory.getLog(LoginController.class);

	@Autowired
	private AgcModel agcModel;
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String doGetLogin(ModelMap model)
	{
		return("login");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPostLogin(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, ModelMap model)
	{
		String _M = "doPostLogin(): ";
		LOG.debug(_M + "started. agcModel=" + agcModel + ", loginForm=" + loginForm + ", result=" + result);
		if (result.hasErrors()) {
			return("login");
		}

		User u;
		try {
			u = getUserService().login(loginForm.getUsername(), loginForm.getPassword());
		} catch(PersistentStorageUnavailableException e) {
			LOG.error(_M + "persistent storage is inavailable.", e);
			result.reject("Unavailable");
			return("login");
		}

		if (u == null) {
			result.reject("Invalid");
			return("login");
        }

    	agcModel.setLoggedInUser(u);
    	LOG.debug(_M + "ended. login successful. user=" + u);
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

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
