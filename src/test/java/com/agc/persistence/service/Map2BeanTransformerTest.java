package com.agc.persistence.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class Map2BeanTransformerTest {
	private static final String TEST_STRING_PROPERTY_NAME = "stringProperty";
	private static final String TEST_INT_PROPERTY_NAME = "intProperty";
	private static final String TEST_INTEGER_PROPERTY_NAME = "integerProperty";
	private static final String TEST_ENUM_PROPERTY_NAME = "enumProperty";

	private static final String TEST_STRING_PROPERTY_VALUE = "STRING-PROPERTY-VALUE";
	private static final int TEST_INT_PROPERTY_VALUE = 5555;
	private static final Integer TEST_INTEGER_PROPERTY_VALUE = 6666;
	private static final TestBean.EnumType TEST_ENUM_PROPERTY_VALUE = TestBean.EnumType.avalue;

	private static Map2BeanTransformer<TestBean> transformer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		List<String[]> mappingInfo = new ArrayList<String[]>();
		mappingInfo.add(new String[] { TEST_STRING_PROPERTY_NAME, TEST_STRING_PROPERTY_NAME });
		mappingInfo.add(new String[] { TEST_INT_PROPERTY_NAME, TEST_INT_PROPERTY_NAME });
		mappingInfo.add(new String[] { TEST_INTEGER_PROPERTY_NAME, TEST_INTEGER_PROPERTY_NAME });
		mappingInfo.add(new String[] { TEST_ENUM_PROPERTY_NAME, TEST_ENUM_PROPERTY_NAME });

		transformer = new Map2BeanTransformer<TestBean>();

		transformer.setTargetClass(TestBean.class.getName());
		transformer.setMappingInfo(mappingInfo);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		transformer = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testTransform()
	{
		Map<String, Object> mappingData = new HashMap<String, Object>();

		mappingData.put(TEST_STRING_PROPERTY_NAME, TEST_STRING_PROPERTY_VALUE);
		mappingData.put(TEST_INT_PROPERTY_NAME, TEST_INT_PROPERTY_VALUE);
		mappingData.put(TEST_INTEGER_PROPERTY_NAME, TEST_INTEGER_PROPERTY_VALUE);
		mappingData.put(TEST_ENUM_PROPERTY_NAME, TEST_ENUM_PROPERTY_VALUE);

		TestBean testBean = transformer.transform(mappingData);
		assertEquals(testBean.getStringProperty(), mappingData.get(TEST_STRING_PROPERTY_NAME));
		assertEquals(testBean.getIntProperty(), mappingData.get(TEST_INT_PROPERTY_NAME));
		assertEquals(testBean.getIntegerProperty(), mappingData.get(TEST_INTEGER_PROPERTY_NAME));
		assertEquals(testBean.getEnumProperty(), mappingData.get(TEST_ENUM_PROPERTY_NAME));
	}

	public static class TestBean {
		private enum EnumType { avalue, bvalue };
		private String stringProperty;
		private int intProperty;
		private Integer integerProperty;
		private EnumType enumProperty;
		
		/**
		 * @return the stringProperty
		 */
		public String getStringProperty()
		{
			return stringProperty;
		}

		/**
		 * @param stringProperty the stringProperty to set
		 */
		public void setStringProperty(String stringProperty)
		{
			this.stringProperty = stringProperty;
		}

		/**
		 * @return the intProperty
		 */
		public int getIntProperty()
		{
			return intProperty;
		}

		/**
		 * @param intProperty the intProperty to set
		 */
		public void setIntProperty(int intProperty)
		{
			this.intProperty = intProperty;
		}

		/**
		 * @return the integerProperty
		 */
		public Integer getIntegerProperty()
		{
			return integerProperty;
		}

		/**
		 * @param integerProperty the integerProperty to set
		 */
		public void setIntegerProperty(Integer integerProperty)
		{
			this.integerProperty = integerProperty;
		}

		/**
		 * @return the enumProperty
		 */
		public EnumType getEnumProperty()
		{
			return enumProperty;
		}

		/**
		 * @param enumProperty the enumProperty to set
		 */
		public void setEnumProperty(EnumType enumProperty)
		{
			this.enumProperty = enumProperty;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("TestBean [stringProperty=");
			builder.append(stringProperty);
			builder.append(", intProperty=");
			builder.append(intProperty);
			builder.append(", integerProperty=");
			builder.append(integerProperty);
			builder.append(", enumProperty=");
			builder.append(enumProperty);
			builder.append("]");
			return builder.toString();
		}
	}
}
