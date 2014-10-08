package com.agc.persistence.service;

import java.util.List;

import com.agc.persistence.domain.ConfigCodeSearchInfo;
import com.agc.persistence.domain.MCSeries;

/**
 * @author Dmitry Lekhtuz
 *
 */
public interface ReferenceService {

	/**
	 * Retrieve series codes with descriptions. This is static data, and can be cached.
	 * @return
	 */
	public List<MCSeries> getMCSeries();

	/**
	 * Retrieve information about specific code. In most cases it will be single record (or none if not found).
	 * But some codes correspond to several series.
	 * @param configCode
	 * @return
	 */
	public List<ConfigCodeSearchInfo> getConfigCodeInfo(String configCode);
}
