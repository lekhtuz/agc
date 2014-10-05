package com.agc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agc.web.domain.AgcModel;

/**
 * @author Dmitry Lekhtuz
 *
 */
@Controller
@RequestMapping("/pricing")
public class PricingController {
	private static final Logger LOG = LoggerFactory.getLogger(PricingController.class);
	
//	@Autowired
//	private PricingService pricingService;
	
	@Autowired
	private AgcModel agcModel;

	@RequestMapping(method = RequestMethod.GET)
	public String displayConfigCodeSearchForm(Model model)
	{
		LOG.debug("displayEmployeeList(): agcModel=" + agcModel);

//		List<Employee> list = employeeService.getAllEmployees();
//		model.addAttribute("list", list);
		return("pricing/search");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String displaySearchResults(Model model)
	{
		LOG.debug("displaySearchResults(): agcModel=" + agcModel);
		return("pricing/results");
	}


}
