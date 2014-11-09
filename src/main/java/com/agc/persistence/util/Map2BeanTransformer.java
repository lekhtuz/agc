package com.agc.persistence.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class Map2BeanTransformer<T> {
	private static final Log LOG = LogFactory.getLog(Map2BeanTransformer.class);

	private List<String[]> mappingInfo;
	private Class<T> targetClass;
	@SuppressWarnings("rawtypes")
	private Map<Class, Map2BeanTypeConverter> typeConverterMap;

	public T transform(Map<String, Object> data)
	{
		String _M = "transform(): ";
		if (getMappingInfo() == null || getTargetClass() == null || data == null) {
			LOG.warn(_M + "Transformer is misconfigured or parameter is invalid.");
			return(null);
		}

		T target;
		try {
			target = (T) targetClass.newInstance();
		} catch (IllegalAccessException e) {
			LOG.warn(_M + "Unable to create object of class " + targetClass + ". Is default constructor available?");
			return(null);
		} catch (InstantiationException e) {
			LOG.warn(_M + "Unable to create object of class " + targetClass + ". Is default constructor available?");
			return(null);
		}

		for (String propNames[] : getMappingInfo()) {
			try {
				// GSon does not have type information, and therefore does not always return values of the correct type.
				// Example: all integers are converted to doubles
				Object targetValue = data.get(propNames[1]);
				Map2BeanTypeConverter converter = (Map2BeanTypeConverter)MapUtils.getObject(getTypeConverterMap(),
						targetValue.getClass(), NoopConverter.getInstance());
				targetValue = converter.convert(targetValue);

				PropertyUtils.setProperty(target, propNames[0], targetValue);
			} catch (Exception e) {
				LOG.warn(_M + "Property " + propNames[0] + " can not be set to " + data.get(propNames[1]));
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
	public Class<T> getTargetClass() {
		return targetClass;
	}

	/**
	 * @param targetClass the targetClass to set
	 */
	public void setTargetClass(Class<T> targetClass) {
		this.targetClass = targetClass;
	}

	/**
	 * @return the typeConverterMap
	 */
	@SuppressWarnings("rawtypes")
	public Map<Class, Map2BeanTypeConverter> getTypeConverterMap()
	{
		return typeConverterMap;
	}

	/**
	 * @param typeConverterMap the typeConverterMap to set
	 */
	public void setTypeConverterMap(@SuppressWarnings("rawtypes") Map<Class, Map2BeanTypeConverter> typeConverterMap)
	{
		this.typeConverterMap = typeConverterMap;
	}
}
