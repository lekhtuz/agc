package com.agc.persistence.service;

import java.util.List;

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
}
