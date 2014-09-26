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

/**
 * @author lekhdm
 *
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
	private static final Log LOG = LogFactory.getLog(LogoutController.class);

	@Autowired
	private AgcModel agcModel;

	@RequestMapping(method = RequestMethod.GET)
	public String doGetLogout(ModelMap model)
	{
		LOG.debug("doPostLogout(): agcModel=" + agcModel);
		agcModel.setLoggedInUser(null);
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
}
