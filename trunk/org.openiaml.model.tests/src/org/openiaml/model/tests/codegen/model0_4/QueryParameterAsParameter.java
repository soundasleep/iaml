/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * We can use a QueryParameter as a Parameter to a SelectWire, in order
 * to select an instance from a domain store.
 * 
 * @example QueryParameter,SelectWire,DomainObjectInstance Using a {@model QueryParameter} from
 * 		the current request URI to {@model SelectWire select}
 * 		an {@model DomainObjectInstance object instance}.
 * @author jmwright
 *
 */
public class QueryParameterAsParameter extends DatabaseCodegenTestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(QueryParameterAsParameter.class);
		initialiseDatabase();
	}
	
	@Override
	protected String getDatabaseName() {
		return "output/model_123172ceeef_32.db";
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL, password VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO User (id, name, email, password) VALUES (1, 'User Default', 'default@jevon.org', 'test1')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (2, 'User Two', 'target@jevon.org', 'test2')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (4, 'User Three', 'test3@jevon.org', 'test3')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (8, 'User Four', 'test4@jevon.org', 'test4')");
		return s;
	}
	
	/**
	 * By default, it will select ID = 1.
	 */
	public void testDefault() throws Exception {
		beginAtSitemapThenPage("Home");
		
		{
			String field = getLabelIDForText("name");
			assertLabeledFieldEquals(field, "User Default");
		}
		
		{
			String field = getLabelIDForText("email");
			assertLabeledFieldEquals(field, "default@jevon.org");
		}
	}
	
	/**
	 * Select a particular user.
	 */
	public void testSelect() throws Exception {
		beginAtSitemapThenPageQuery("Home", "user_id=4");
		
		{
			String field = getLabelIDForText("name");
			assertLabeledFieldEquals(field, "User Three");
		}
		
		{
			String field = getLabelIDForText("email");
			assertLabeledFieldEquals(field, "test3@jevon.org");
		}
	}
	
	/**
	 * We can select a user, edit the fields, and reload
	 * with the same parameter, and the database object will not
	 * have changed (because autosave is false)
	 * 
	 * @implementation If a {@model InputForm} representing a {@model SelectWire selected}
	 * 		{@model DomainObjectInstance} changes, the changes will be saved. 
	 */
	public void testChangable() throws Exception {
		beginAtSitemapThenPageQuery("Home", "user_id=8");
		
		{
			String field = getLabelIDForText("name");
			assertLabeledFieldEquals(field, "User Four");
			
			// change it
			setLabeledFormElementField(field, "a changed value");
			assertLabeledFieldEquals(field, "a changed value");
		}
		
		// reload
		beginAtSitemapThenPageQuery("Home", "user_id=8");
		
		// hasn't changed
		{
			String field = getLabelIDForText("name");
			assertLabeledFieldEquals(field, "User Four");
		}
		
	}
	
}
