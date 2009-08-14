/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * A QueryParameter connected to a text field via a SetWire.
 * 
 * @author jmwright
 *
 */
public class QueryParameterSet extends CodegenTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(QueryParameterSet.class);
	}
	
	/**
	 * Initially, the query is empty.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		beginAtSitemapThenPage("Home");
		
		String source = getLabelIDForText("current name");
		assertLabeledFieldEquals(source, "empty");
	}
	
	/**
	 * Check the page with the query set.
	 * 
	 * @throws Exception
	 */
	public void testQuery() throws Exception {
		beginAtSitemapThenPageQuery("Home", "name=value");
		
		String source = getLabelIDForText("current name");
		assertLabeledFieldEquals(source, "value");
	}
	
	/**
	 * Try visiting the page multiple times with different queries.
	 * 
	 * @throws Exception
	 */
	public void testMultiple() throws Exception {
		beginAtSitemapThenPageQuery("Home", "name=value");
		
		{
			String source = getLabelIDForText("current name");
			assertLabeledFieldEquals(source, "value");
		}
		
		beginAtSitemapThenPageQuery("Home", "name=new");
		
		{
			String source = getLabelIDForText("current name");
			assertLabeledFieldEquals(source, "new");
		}
		
		// blank
		beginAtSitemapThenPage("Home");
		
		{
			String source = getLabelIDForText("current name");
			assertLabeledFieldEquals(source, "");
		}
	}
	
	/**
	 * We can edit the target field and it will not change, because
	 * the value is taken from the query parameter.
	 * 
	 * @throws Exception
	 */
	public void testChangeable() throws Exception {
		beginAtSitemapThenPageQuery("Home", "name=test1");
		
		{
			String source = getLabelIDForText("current name");
			assertLabeledFieldEquals(source, "test1");
			
			// set it
			setLabeledFormElementField(source, "a new value");
		}
		
		// reload
		beginAtSitemapThenPageQuery("Home", "name=test1");

		// it hasn't changed
		{
			String source = getLabelIDForText("current name");
			assertLabeledFieldEquals(source, "test1");
		}
	}
	
}
