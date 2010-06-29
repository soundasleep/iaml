/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * If a domain object instance is created through a New Instance wire
 * without the DomainObject having an 'id' element created, then
 * an 'id' element should automatically be created and the
 * instance can be edited.
 * 
 * <p>Since this DomainObjectInstance is stored in a page, there
 * is only ever one instance of the object created in the application.
 * 
 * <p>Tests issue 65.
 * 
 * <p>This also tests the creation of a new database if one does
 * not already exist.
 * 
 * @author jmwright
 *
 */
public class NewInstanceWithoutId extends DatabaseCodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(NewInstanceWithoutId.class);
	}
	
	private String testValue = "test " + new Date();
	
	/**
	 * We should be able to access the page normally.
	 */
	public void testCanAccessContainerPage() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("container");
		assertNoProblem();
		
		// there should be a 'name' attribute generated
		String name = getLabelIDForText("name");
		assertLabeledFieldEquals(name, "");
		
		// lets change it
		setLabeledFormElementField(name, testValue);
		assertLabeledFieldEquals(name, testValue);
		
		// reload page
		reloadPage(sitemap, "container");
		
		// it should have remained
		name = getLabelIDForText("name");
		assertLabeledFieldEquals(name, testValue);
	}
	
	public void testCreatedDatabaseStructure() throws Exception {
		
		// create as normal
		testCanAccessContainerPage();
		
		// check the results set
		ResultSet rs = executeQuery("SELECT * FROM domain_object");
		assertTrue(rs.next());
		assertEquals(testValue, rs.getString("name"));
		// generated_primary_key should be 1
		assertEquals(1, rs.getInt("generated_primary_key"));
		assertFalse(rs.next());
		
	}
	
	/**
	 * Test that the database does not exist before it is tested,
	 * but exists fine afterwards.
	 * 
	 * @throws Exception
	 */
	public void testDatabaseNotCreated() throws Exception {
		
		// the database should not exist
		IFile db = getProject().getFile(getDatabaseName());
		assertFalse(db.exists());
		
		// check the results set
		try {
			executeQuery("SELECT * FROM domain_object");
			throw new RuntimeException("Unexpectedly could execute query");
		} catch (AssertionFailedError e) {
			// expected
		}
		
		// works fine afterwards
		testCreatedDatabaseStructure();
		
		assertTrue(db.exists());
		
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.codegen.DatabaseCodegenTestCase#getDatabaseInitialisers()
	 */
	@Override
	protected List<String> getDatabaseInitialisers() {
		throw new UnsupportedOperationException("NewInstanceWithoutId should not be creating a database");
	}

}
