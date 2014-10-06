package com.agc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agc.persistence.service.ReferenceService;
import com.agc.web.domain.AgcModel;
import com.agc.web.domain.ConfigCodeSearchForm;

/**
 * @author Dmitry Lekhtuz
 *
 */
@Controller
@RequestMapping("/pricing")
public class PricingController {
	private static final Logger LOG = LoggerFactory.getLogger(PricingController.class);

	@Autowired
	private ConfigCodeSearchForm searchForm;

	@Autowired
	private ReferenceService referenceService;

//	@Autowired
//	private PricingService pricingService;
	
	@Autowired
	private AgcModel agcModel;

	@RequestMapping(method = RequestMethod.GET)
	public String displayConfigCodeSearchForm(Model model)
	{
		LOG.debug("displayConfigCodeSearchForm(): started. agcModel=" + agcModel);
		model.addAttribute("allSeries", referenceService.getMCSeries());
		return("pricing/search");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String displaySearchResults(@ModelAttribute("searchForm") ConfigCodeSearchForm searchForm, BindingResult result, ModelMap model)
	{
		LOG.debug("displaySearchResults(): agcModel=" + agcModel + ", result=" + result);
		
		if (result.hasErrors()) {
			return("pricing/search");
		}

		String configCode = searchForm.getConfigCode();
		int series[] = searchForm.getSelectedSeries();
		
		return("pricing/results");
	}

	/**
	 * @return the agcModel
	 */
	@ModelAttribute("agcModel")
	private AgcModel getAgcModel()
	{
		return(agcModel);
	}

	/**
	 * @return the searchForm
	 */
	@Bean
	@ModelAttribute("searchForm")
	private ConfigCodeSearchForm getSearchForm()
	{
		return(new ConfigCodeSearchForm());
	}
	

}
