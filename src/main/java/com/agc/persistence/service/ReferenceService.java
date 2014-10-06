package com.agc.persistence.service;

import java.util.List;

import com.agc.persistence.domain.MCSeries;

/**
 * @author Dmitry Lekhtuz
 *
 */
public interface ReferenceService {
	public List<MCSeries> getMCSeries();
}
