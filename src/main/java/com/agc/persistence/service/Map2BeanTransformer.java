package com.agc.persistence.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class Map2BeanTransformer<T> {
	private static final Log LOG = LogFactory.getLog(Map2BeanTransformer.class);

	private List<String[]> mappingInfo;
	private String targetClass;

	@SuppressWarnings("unchecked")
	public T transform(Map<String, Object> data)
	{
		String _M = "transform() :";
		if (getMappingInfo() == null || targetClass == null || data == null) {
			LOG.warn(_M + "Transformer is misconfigured or parameter is invalid.");
			return(null);
		}

		T target;
		try {
			target = (T) Class.forName(targetClass).newInstance();
		} catch (Exception e) {
			LOG.warn(_M + "Unable to create object of class " + targetClass + ". Is default constructor available?");
			return(null);
		}

		for (String propName : data.keySet()) {
			try {
				PropertyUtils.setSimpleProperty(target, propName, data.get(propName));
			} catch (Exception e) {
				LOG.warn(_M + "Property " + propName + " can not be set to " + data.get(propName));
			}
		}
		return(target);
	}

	/**
	 * @return the mappingInfo
	 */
	public List<String[]> getMappingInfo()
	{
		return mappingInfo;
	}

	/**
	 * @param mappingInfo the mappingInfo to set
	 */
	public void setMappingInfo(List<String[]> mappingInfo)
	{
		this.mappingInfo = mappingInfo;
	}

	/**
	 * @return the targetClass
	 */
	public String getTargetClass() {
		return targetClass;
	}

	/**
	 * @param targetClass the targetClass to set
	 */
	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}
}
