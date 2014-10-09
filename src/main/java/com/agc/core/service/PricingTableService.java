package com.agc.core.service;

import java.util.List;

import com.agc.persistence.domain.ConfigCodeSearchInfo;
import com.agc.persistence.domain.PriceGridModel;

/**
 * @author Dmitry Lekhtuz
 *
 */
public interface PricingTableService {

	/**
	 * Retrieve information about specific code. In most cases it will be single record (or none if not found).
	 * But some codes correspond to several series.
	 * @param configCode
	 * @return
	 */
	public List<ConfigCodeSearchInfo> getConfigCodeInfo(String configCode);

	public List<PriceGridModel> getPriceGridModel(int series, int priceGridNo, String glassCodes[]);
}
