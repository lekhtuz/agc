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

@Controller
@RequestMapping("/")
public class SiteController {
	private static final Log LOG = LogFactory.getLog(SiteController.class);

	@Autowired
	private AgcModel agcModel;

	@RequestMapping(method = RequestMethod.GET)
	public String doHome(ModelMap model)
	{
		LOG.debug("SiteController.homePage(): agcModel=" + agcModel);
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";

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