package com.agc.persistence.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agc.persistence.domain.ConfigCodeSearchInfo;
import com.agc.persistence.domain.MCSeries;
import com.agc.persistence.domain.RecordSet;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class ReferenceServiceImpl extends AbstractPersistenceService implements ReferenceService {
	private static final Log LOG = LogFactory.getLog(ReferenceServiceImpl.class);
	private Iterable2ListOfBeansTransformer<MCSeries> iterable2MCSeriesTransformer;
	private Iterable2ListOfBeansTransformer<ConfigCodeSearchInfo> iterable2ConfigCodeInfoTransformer;
	private String configInfoViewName;

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.ReferenceService#getMCSeries()
	 */
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

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.ReferenceService#getConfigCodeInfo(java.lang.String)
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
			filter = "lower(PgConfigCode) like '" + configCode + "'";

		} else if (StringUtils.isNotBlank(configCode)) {
			// Config code is provided. Most likely there will be 1 record, but not always.
			filter = "lower(PgConfigCode) = '" + configCode + "'";
		}

		RecordSet recordSet = getDbAdapter().getRecordSet(getConfigInfoViewName(), filter);
		List<ConfigCodeSearchInfo> list = getIterable2ConfigCodeInfoTransformer().transform(recordSet);
		
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

}
