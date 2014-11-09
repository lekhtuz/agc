package com.agc.persistence.util;

/**
 * @author lekhdm
 *
 */
public class NoopConverter implements Map2BeanTypeConverter {
	static private NoopConverter converter = null;

	// Private default constructor turns it into a singleton
	private NoopConverter()
	{
	}

	/* (non-Javadoc)
	 * @see com.agc.persistence.util.Map2BeanTypeConverter#convert(java.lang.Object)
	 */
	@Override
	public Object convert(Object o)
	{
		return(o);
	}

	static public final NoopConverter getInstance()
	{
		// Don't bother with synchronization
		if (converter == null) {
			converter = new NoopConverter();
		}

		return(converter);
	}
}
