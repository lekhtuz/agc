package com.agc.persistence.domain;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class JSONRecordSet {
	private JSONUser record[];

	/**
	 * @return the record
	 */
	public JSONUser[] getRecord() {
		return record;
	}

	/**
	 * @param record the record to set
	 */
	public void setRecord(JSONUser[] record)
	{
		this.record = record;
	}
}
