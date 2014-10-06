package com.agc.persistence.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agc.persistence.domain.MCSeries;
import com.agc.persistence.domain.RecordSet;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class ReferenceServiceImpl extends AbstractPersistenceService implements ReferenceService {
	private static final Log LOG = LogFactory.getLog(ReferenceServiceImpl.class);
	private Iterable2ListOfBeansTransformer<MCSeries> iterable2MCSeriesTransformer;

	@Override
	public List<MCSeries> getMCSeries()
	{
		String _M = "getMCSeries(): ";
		LOG.debug(_M + "started.");
		
		RecordSet recordSet = getDbAdapter().getRecordSet(getTableName(), "cmkey='MC_Series' and CmTinyInt <> 0 and CmField04 = 1");
		List<MCSeries> list = getIterable2MCSeriesTransformer().transform(recordSet);
		
		LOG.debug(_M + "ended. Number of records=" + list.size());
		return(list);
	}

	/**
	 * @return the iterable2MCSeriesTransformer
	 */
	public Iterable2ListOfBeansTransformer<MCSeries> getIterable2MCSeriesTransformer()
	{
		return iterable2MCSeriesTransformer;
	}

	/**
	 * @param iterable2mcSeriesTransformer the iterable2MCSeriesTransformer to set
	 */
	public void setIterable2MCSeriesTransformer(
			Iterable2ListOfBeansTransformer<MCSeries> iterable2mcSeriesTransformer)
	{
		iterable2MCSeriesTransformer = iterable2mcSeriesTransformer;
	}
}
