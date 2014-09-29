package com.agc.persistence.adapters;

import com.agc.persistence.domain.RecordSet;

/**
 * @author Dmitry Lekhtuz
 *
 */
public interface DatabaseAdapter {
	public RecordSet getRecordSet(String tableName, String whereClause);
}
