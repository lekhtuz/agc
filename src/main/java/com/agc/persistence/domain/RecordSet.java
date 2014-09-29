package com.agc.persistence.domain;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class RecordSet {
	private List<Map<String, Object>> recordList;

	public RecordSet()
	{
		this(null);
	}

	/**
	 * @param recordList
	 */
	@SuppressWarnings("unchecked")
	public RecordSet(List<Map<String, Object>> recordList)
	{
		this.recordList = (recordList == null ? ListUtils.EMPTY_LIST : recordList);
	}
	
	public int size()
	{
		return(recordList.size());
	}

	public int getIntValue(int index, String key)
	{
		return(MapUtils.getIntValue(recordList.get(index), key));
	}

	public String getStringValue(int index, String key)
	{
		return(MapUtils.getString(recordList.get(index), key));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecordSet [recordList=");
		builder.append(recordList);
		builder.append("]");
		return builder.toString();
	}
}
