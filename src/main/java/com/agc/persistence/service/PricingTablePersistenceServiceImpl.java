package com.agc.persistence.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agc.persistence.domain.ConfigCodeSearchInfo;
import com.agc.persistence.domain.PriceGridModel;
import com.agc.persistence.domain.RecordSet;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class PricingTablePersistenceServiceImpl extends AbstractPersistenceService implements PricingTablePersistenceService {
	private static final Log LOG = LogFactory.getLog(PricingTablePersistenceServiceImpl.class);

	private Iterable2ListOfBeansTransformer<ConfigCodeSearchInfo> iterable2ConfigCodeInfoTransformer;
	private Iterable2ListOfBeansTransformer<PriceGridModel> iterable2PriceGridModelTransformer;
	private String configInfoViewName;
	private String priceGridTableName;

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.PricingTablePersistenceService#getConfigCodeInfo(java.lang.String)
	 */
	@Override
	public List<ConfigCodeSearchInfo> getConfigCodeInfo(String configCode)
	{
		String _M = "getConfigCodeInfo(String): ";
		LOG.debug(_M + "started. configCode=" + configCode);

		configCode = StringUtils.lowerCase(StringUtils.trim(configCode));
		String filter = null;	// Default value - retrieve all config codes.
		
		if (StringUtils.contains(configCode, "*")) {
			// Config code uses wild card. Convert * to % and use 'like'.
			filter = "lower(PgConfigCode) like '" + configCode + "' and PtSeries = 1";	// Majestic only for now

		} else if (StringUtils.isNotBlank(configCode)) {
			// Config code is provided. Most likely there will be 1 record, but not always.
			filter = "lower(PgConfigCode) = '" + configCode + "' and PtSeries = 1";		// Majestic only for now
		}

		RecordSet recordSet = getDbAdapter().getRecordSet(getConfigInfoViewName(), filter);
		List<ConfigCodeSearchInfo> list = getIterable2ConfigCodeInfoTransformer().transform(recordSet);

		LOG.debug(_M + "ended. Number of records=" + list.size());
		return(list);
	}

	@Override
	public List<PriceGridModel> getPriceGridModel(int series, int priceGridNo)
	{
		String _M = "getPriceGridModel(int, int): ";
		LOG.debug(_M + "started. series=" + series + ", priceGridNo=" + priceGridNo);

		String filter = "PdSeries = " + series + " and PdPriceGridNo = " + priceGridNo;
		filter += " and PdGlassType in (12,13)";	// Base glass, remove after tables are generated
		RecordSet recordSet = getDbAdapter().getRecordSet(getPriceGridTableName(), filter);
		List<PriceGridModel> list = getIterable2PriceGridModelTransformer().transform(recordSet);

		LOG.debug(_M + "ended. Number of records=" + list.size());
		return(list);
	}

	/**
	 * @return the iterable2ConfigCodeInfoTransformer
	 */
	public Iterable2ListOfBeansTransformer<ConfigCodeSearchInfo> getIterable2ConfigCodeInfoTransformer()
	{
		return iterable2ConfigCodeInfoTransformer;
	}

	/**
	 * @param iterable2ConfigCodeInfoTransformer the iterable2ConfigCodeInfoTransformer to set
	 */
	public void setIterable2ConfigCodeInfoTransformer(
			Iterable2ListOfBeansTransformer<ConfigCodeSearchInfo> iterable2ConfigCodeInfoTransformer)
	{
		this.iterable2ConfigCodeInfoTransformer = iterable2ConfigCodeInfoTransformer;
	}

	/**
	 * @return the iterable2PriceGridModelTransformer
	 */
	public Iterable2ListOfBeansTransformer<PriceGridModel> getIterable2PriceGridModelTransformer()
	{
		return iterable2PriceGridModelTransformer;
	}

	/**
	 * @param iterable2PriceGridModelTransformer the iterable2PriceGridModelTransformer to set
	 */
	public void setIterable2PriceGridModelTransformer(
			Iterable2ListOfBeansTransformer<PriceGridModel> iterable2PriceGridModelTransformer)
	{
		this.iterable2PriceGridModelTransformer = iterable2PriceGridModelTransformer;
	}

	/**
	 * @return the configInfoViewName
	 */
	public String getConfigInfoViewName()
	{
		return configInfoViewName;
	}

	/**
	 * @param configInfoViewName the configInfoViewName to set
	 */
	public void setConfigInfoViewName(String configInfoViewName)
	{
		this.configInfoViewName = configInfoViewName;
	}

	/**
	 * @return the priceGridTableName
	 */
	public String getPriceGridTableName()
	{
		return priceGridTableName;
	}

	/**
	 * @param priceGridTableName the priceGridTableName to set
	 */
	public void setPriceGridTableName(String priceGridTableName)
	{
		this.priceGridTableName = priceGridTableName;
	}
}
