package com.agc.web.controller;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agc.core.service.PricingTableService;
import com.agc.persistence.domain.ConfigCodeSearchInfo;
import com.agc.persistence.domain.PriceGridModel;
import com.agc.persistence.service.ReferenceService;
import com.agc.web.domain.AgcModel;
import com.agc.web.domain.ConfigCodeSearchForm;

/**
 * @author Dmitry Lekhtuz
 *
 */
@Controller
@RequestMapping("/pricingtable")
public class PricingTableController {
	private static final Logger LOG = LoggerFactory.getLogger(PricingTableController.class);

	@Autowired
	private ConfigCodeSearchForm searchForm;
	
	@Autowired
	private AgcModel agcModel;

	private ReferenceService referenceService;
	private PricingTableService pricingTableService;
	private String standardGlassCodes[] = new String[] { "CLGTN37500", "CLGTN50000" };

	@RequestMapping(method = RequestMethod.GET)
	public String displayConfigCodeSearchForm(Model model)
	{
		String _M = "displayConfigCodeSearchForm(): ";
		LOG.debug(_M +"started. agcModel=" + agcModel);
		model.addAttribute("allSeries", getReferenceService().getMCSeries());
		return("pricingtable/search");
	}
	
	@RequestMapping(value="/{configCode}", method = RequestMethod.GET)
	public String displayConfigCodeInfo(@PathVariable String configCode, Model model)
	{
		String _M = "displayConfigCodeSearchForm(): ";
		LOG.debug(_M +"started. configCode=" + configCode + ", agcModel=" + agcModel);

		List<ConfigCodeSearchInfo> configCodeSearchResultsList = getPricingTableService().getConfigCodeInfo(configCode);
		
		for (ConfigCodeSearchInfo ccsi:configCodeSearchResultsList) {
			List<PriceGridModel> priceGridModels = getPricingTableService().getPriceGridModel(ccsi.getSeries(), ccsi.getPriceGridNo(), getStandardGlassCodes());
			generatePricingGrid(priceGridModels, model);
			model.addAttribute("configCodeSearchResult", ccsi);
		}

		return("pricingtable/configCodePricingTable");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String displaySearchResults(@ModelAttribute("searchForm") ConfigCodeSearchForm searchForm, BindingResult result, ModelMap model)
	{
		LOG.debug("displaySearchResults(): agcModel=" + agcModel + ", result=" + result);
		
		if (result.hasErrors()) {
			return("pricingtable/search");
		}

		String configCode = searchForm.getConfigCode();
		int series[] = searchForm.getSelectedSeries();	// Not implemented yet

		List<ConfigCodeSearchInfo> configCodeSearchResult = getPricingTableService().getConfigCodeInfo(configCode);
		model.addAttribute("configCodeSearchResult", configCodeSearchResult);
		
		return("pricingtable/results");
	}
	
	private void generatePricingGrid(List<PriceGridModel> priceGridModels, Model model)
	{
		String _M = "generatePricingGrid(List<PriceGridModel>, Model): ";
		LOG.debug(_M + "started.");

		SortedSet<Integer> heightSet = new TreeSet<Integer>();
		SortedSet<Integer> widthSet = new TreeSet<Integer>();
		SortedSet<Integer> width2Set = new TreeSet<Integer>();

		// 1. Determine the size of the table
		for (PriceGridModel pgModel : priceGridModels) {
			heightSet.add(pgModel.getHeight());
			widthSet.add(pgModel.getWidth());
			width2Set.add(pgModel.getWidth2());
		}

		int numberOfHeights = heightSet.size();
		int numberOfWidths = widthSet.size();

		// 2. Allocate the grid and label arrays.
		PriceAreaPair grid[][] = new PriceAreaPair[heightSet.size()][widthSet.size()];
		int rowLabels[] = new int[numberOfHeights];
		int columnLabels[] = new int[numberOfWidths];

		// 3. Fill in width/height labels. Reversed natural comparator returns them in reverse order.
		Iterator<Integer>it = heightSet.iterator();
		for (int i = numberOfHeights; --i >= 0; ) {
			rowLabels[i] = it.next();
		}

		it = widthSet.iterator();
		for (int i = 0; i < numberOfWidths; i ++) {
			columnLabels[i] = it.next();
		}

		// 4. Fill in the values
		for (PriceGridModel pgModel : priceGridModels) {
			int hidx = ArrayUtils.indexOf(rowLabels, pgModel.getHeight());
			int widx = ArrayUtils.indexOf(columnLabels, pgModel.getWidth());
			double area = calculateArea(pgModel.getHeight(), pgModel.getWidth());
			grid[hidx][widx] = new PriceAreaPair(pgModel.getPrice(), area);
		}

		model.addAttribute("pricingGrid", grid);
		model.addAttribute("rowLabels", rowLabels);
		model.addAttribute("columnLabels", columnLabels);
		LOG.debug(_M + "rowLabels=" + rowLabels + "(" + rowLabels.length + " elements)");
		LOG.debug(_M + "columnLabels=" + columnLabels + "(" + columnLabels.length + " elements)");
		LOG.debug(_M + "ended.");
	}

	// Parameters are passed in inches. Result is in sq feet, rounded up to the nearest 0.5.
	private double calculateArea(int w, int h)
	{
		double area = w * h / 144.0;
		area = Math.ceil(area * 2) / 2;
		
		LOG.debug("calculateArea(): width=" + w + ", height=" + h + ", area=" + area);
		return(area);
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

	/**
	 * @return the referenceService
	 */
	public ReferenceService getReferenceService()
	{
		return referenceService;
	}

	/**
	 * @param referenceService the referenceService to set
	 */
	public void setReferenceService(ReferenceService referenceService)
	{
		this.referenceService = referenceService;
	}

	/**
	 * @return the pricingTableService
	 */
	public PricingTableService getPricingTableService()
	{
		return pricingTableService;
	}

	/**
	 * @param pricingTableService the pricingTableService to set
	 */
	public void setPricingTableService(PricingTableService pricingTableService)
	{
		this.pricingTableService = pricingTableService;
	}

	/**
	 * @return the standardGlassCodes
	 */
	public String[] getStandardGlassCodes()
	{
		return standardGlassCodes;
	}

	/**
	 * @param standardGlassCodes the standardGlassCodes to set
	 */
	public void setStandardGlassCodes(String[] standardGlassCodes)
	{
		this.standardGlassCodes = standardGlassCodes;
	}

	public static class PriceAreaPair {
		private int price;
		private double area;

		public PriceAreaPair(int price, double area)
		{
			this.price = price;
			this.area = area;
		}

		/**
		 * @return the price
		 */
		public int getPrice()
		{
			return price;
		}

		/**
		 * @param price the price to set
		 */
		public void setPrice(int price)
		{
			this.price = price;
		}

		/**
		 * @return the area
		 */
		public double getArea()
		{
			return area;
		}

		/**
		 * @param area the area to set
		 */
		public void setArea(double area)
		{
			this.area = area;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("PriceAreaPair [price=");
			builder.append(price);
			builder.append(", area=");
			builder.append(area);
			builder.append("]");
			return builder.toString();
		}
	}
}
