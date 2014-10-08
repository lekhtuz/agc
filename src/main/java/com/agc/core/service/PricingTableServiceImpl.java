package com.agc.core.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agc.persistence.domain.ConfigCodeSearchInfo;
import com.agc.persistence.domain.PriceGridModel;
import com.agc.persistence.service.PricingTablePersistenceService;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class PricingTableServiceImpl implements PricingTableService {
	private static final Log LOG = LogFactory.getLog(PricingTableServiceImpl.class);

	private PricingTablePersistenceService pricingTablePersistenceService;

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.PricingTableService#getConfigCodeInfo(java.lang.String)
	 */
	@Override
	public List<ConfigCodeSearchInfo> getConfigCodeInfo(String configCode)
	{
		String _M = "getConfigCodeInfo(String): ";
		LOG.debug(_M + "started. configCode=" + configCode);

		List<ConfigCodeSearchInfo> list = getPricingTablePersistenceService().getConfigCodeInfo(configCode);

		LOG.debug(_M + "ended. Number of records=" + list.size());
		return(list);
	}

	/* (non-Javadoc)
	 * @see com.agc.core.service.PricingTableService#getPriceGridModel(int, int)
	 */
	@Override
	public List<PriceGridModel> getPriceGridModel(int series, int priceGridNo)
	{
		String _M = "getConfigCodeInfo(String): ";
		LOG.debug(_M + "started. series=" + series + ", priceGridNo=" + priceGridNo);

		List<PriceGridModel> list = getPricingTablePersistenceService().getPriceGridModel(series, priceGridNo);

		LOG.debug(_M + "ended. Number of records=" + list.size());
		return(list);
	}

	/**
	 * @return the pricingTablePersistenceService
	 */
	public PricingTablePersistenceService getPricingTablePersistenceService()
	{
		return pricingTablePersistenceService;
	}

	/**
	 * @param pricingTablePersistenceService the pricingTablePersistenceService to set
	 */
	public void setPricingTablePersistenceService(
			PricingTablePersistenceService pricingTablePersistenceService)
	{
		this.pricingTablePersistenceService = pricingTablePersistenceService;
	}
}
