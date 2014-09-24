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

	@RequestMapping(method = RequestMethod.GET)
	public String doGetLogin(ModelMap model)
	{
		return("login");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPostLogin(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, ModelMap model)
	{
		LOG.debug("doPostLogin(): agcModel=" + agcModel + ", loginForm=" + loginForm + ", result=" + result);
        if (result.hasErrors()) {
            return("login");
        }

		agcModel.setLoggedIn(true);
		agcModel.setUsername(loginForm.getUsername());

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
