package com.agc.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
import org.springframework.web.bind.annotation.ResponseBody;

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

	@Autowired private ConfigCodeSearchForm searchForm;
	@Autowired private AgcModel agcModel;
	@Autowired private ServletContext servletContext;
	
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

	@RequestMapping(value="/curl", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String returnCurlScript(Model model)
	{
		String _M = "returnCurlScript(): ";
		LOG.debug(_M +"started. agcModel=" + agcModel);

		return("curlllllllllllll");
	}

	@RequestMapping(value="/{configCode}", method = RequestMethod.GET)
	public String displayConfigCodeInfo(@PathVariable String configCode, Model model)
	{
		String _M = "displayConfigCodeSearchForm(): ";
		LOG.debug(_M +"started. configCode=" + configCode + ", agcModel=" + agcModel);
		
		Collection<TableModel> listOfTableModels = new ArrayList<TableModel>();
		model.addAttribute("listOfTableModels", listOfTableModels);

		List<ConfigCodeSearchInfo> configCodeSearchResultsList = getPricingTableService().getConfigCodeInfo(configCode);
		
		for (ConfigCodeSearchInfo ccsi:configCodeSearchResultsList) {
			List<PriceGridModel> priceGridModels = getPricingTableService().getPriceGridModel(ccsi.getSeries(), ccsi.getPriceGridNo(), getStandardGlassCodes());
			TableModel tableModel = generatePricingGrid(priceGridModels);
			tableModel.setCcsi(ccsi);
			listOfTableModels.add(tableModel);
		}

		return("pricingtable/configCodePricingTable");
	}

	@RequestMapping(value="/{configCode}.xlsx", method = RequestMethod.GET, 
			produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")	// vnd.ms-excel
	@ResponseBody
	public byte[] returnXlsxWorkbook(@PathVariable String configCode, Model model)
	{
		String _M = "generatePricingGrid(String, Model): ";
		LOG.debug(_M + "started.");
		
//		File tmpDir = (File)servletContext.getAttribute(ServletContext.TEMPDIR);
//		tmpDir = new File(tmpDir.getAbsolutePath() + "/pricingtable-" + System.currentTimeMillis());
//		boolean b = tmpDir.mkdir();

		XSSFWorkbook book = createExcelWorkbook(configCode);
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			book.write(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LOG.debug(_M + "ended.");
		return(stream.toByteArray());
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
	
	private XSSFWorkbook createExcelWorkbook(String configCode)
	{
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet(configCode);
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Config code:");
		cell = row.createCell(1);
		cell.setCellValue(configCode);
		
		return(book);
	}
	
	private TableModel generatePricingGrid(List<PriceGridModel> priceGridModels)
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

		TableModel tableModel = new TableModel();
		tableModel.setGrid(grid);
		tableModel.setRowLabels(rowLabels);
		tableModel.setColumnLabels(columnLabels);

		LOG.debug(_M + "ended.");
		return(tableModel);
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
	
	public static class TableModel {
		private int rowLabels[];
		private int columnLabels[];
		private PriceAreaPair grid[][];
		private String glassTitle;
		private int glassId;
		private ConfigCodeSearchInfo ccsi;

		/**
		 * @return the rowLabels
		 */
		public int[] getRowLabels()
		{
			return rowLabels;
		}

		/**
		 * @param rowLabels the rowLabels to set
		 */
		public void setRowLabels(int[] rowLabels)
		{
			this.rowLabels = rowLabels;
		}

		/**
		 * @return the columnLabels
		 */
		public int[] getColumnLabels()
		{
			return columnLabels;
		}

		/**
		 * @param columnLabels the columnLabels to set
		 */
		public void setColumnLabels(int[] columnLabels)
		{
			this.columnLabels = columnLabels;
		}

		/**
		 * @return the grid
		 */
		public PriceAreaPair[][] getGrid()
		{
			return grid;
		}

		/**
		 * @param grid the grid to set
		 */
		public void setGrid(PriceAreaPair[][] grid)
		{
			this.grid = grid;
		}

		/**
		 * @return the glassTitle
		 */
		public String getGlassTitle()
		{
			return glassTitle;
		}

		/**
		 * @param glassTitle the glassTitle to set
		 */
		public void setGlassTitle(String glassTitle)
		{
			this.glassTitle = glassTitle;
		}

		/**
		 * @return the glassId
		 */
		public int getGlassId()
		{
			return glassId;
		}

		/**
		 * @param glassId the glassId to set
		 */
		public void setGlassId(int glassId)
		{
			this.glassId = glassId;
		}

		/**
		 * @return the ccsi
		 */
		public ConfigCodeSearchInfo getCcsi()
		{
			return ccsi;
		}

		/**
		 * @param ccsi the ccsi to set
		 */
		public void setCcsi(ConfigCodeSearchInfo ccsi)
		{
			this.ccsi = ccsi;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("TableModel [rowLabels=");
			builder.append(Arrays.toString(rowLabels));
			builder.append(", columnLabels=");
			builder.append(Arrays.toString(columnLabels));
			builder.append(", grid=");
			builder.append(Arrays.toString(grid));
			builder.append(", glassTitle=");
			builder.append(glassTitle);
			builder.append(", glassId=");
			builder.append(glassId);
			builder.append(", ccsi=");
			builder.append(ccsi);
			builder.append("]");
			return builder.toString();
		}
	}
}
