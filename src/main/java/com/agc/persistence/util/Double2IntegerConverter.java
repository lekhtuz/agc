package com.agc.persistence.util;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class Double2IntegerConverter implements Map2BeanTypeConverter {

	/* (non-Javadoc)
	 * @see com.agc.persistence.util.Map2BeanTypeConverter#convert(java.lang.Object)
	 */
	@Override
	public Object convert(Object o)
	{
		return(new Integer(((Double)o).intValue()));
	}
}
