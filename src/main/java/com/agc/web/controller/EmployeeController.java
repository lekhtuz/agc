package com.agc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agc.core.services.EmployeeService;
import com.agc.web.domain.AgcModel;

/**
 * @author lekhdm
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AgcModel agcModel;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displaySearchForm(Model model)
	{
		LOG.debug("EmployeeController.displaySearchForm(): agcModel=" + agcModel);
		return("employee/searchForm");
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String displaySearchResults(Model model)
	{
		LOG.debug("EmployeeController.displaySearchResults(): agcModel=" + agcModel);
		return("/employee/searchResults");
	}

}
