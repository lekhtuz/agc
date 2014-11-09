package com.agc.persistence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agc.persistence.util.Map2BeanTransformer;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class Iterable2ListOfBeansTransformer<T> extends Map2BeanTransformer<T> {
	private static final Log LOG = LogFactory.getLog(Iterable2ListOfBeansTransformer.class);

	public List<T> transform(Iterable<Map<String, Object>> data)
	{
		String _M = "transform(): ";
		LOG.debug(_M + "started.");
		List<T> list = new ArrayList<T>();

		for (Map<String, Object> map : data) {
			T bean = super.transform(map);
			list.add(bean);
		}
		
		LOG.debug(_M + "ended.");
		return(list);
	}
}
