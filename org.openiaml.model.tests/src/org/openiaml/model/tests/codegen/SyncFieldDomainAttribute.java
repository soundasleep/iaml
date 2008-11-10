/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

import junit.framework.AssertionFailedError;
import junit.framework.ComparisonFailure;
import net.sourceforge.jwebunit.api.IElement;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Test just a single SyncWire with a InputTextField to a DomainAttribute
 * 
 * @author jmwright
 *
 */
public class SyncFieldDomainAttribute extends InferenceTestCase {
	
	protected InternetApplication root;
	
	// TODO refactor out
	protected void setUp() throws Exception {
		String modelFile = ROOT + "codegen/SyncFieldDomainAttribute.iaml";
		EObject model = loadModelDirectly(modelFile);
		assertTrue("the model file '" + modelFile + "' should be of type InternetApplication", model instanceof InternetApplication);
		assertNotNull(model);

		root = (InternetApplication) model;
		
		// we now try to do inference
		EcoreInferenceHandler handler = new EcoreInferenceHandler(resource);
		CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(handler);
		ce.create(root);
		
		// write out this inferred model for reference
		String outModel = saveInferredModel().getAbsolutePath();

		super.setUp();		// create project
		doTransform(outModel);	// output to project
	}
	
	/**
	 * Make sure that the database is initially empty (and can be loaded 
	 * through SQLite)
	 */
	public void testDatabaseIsEmpty() throws Exception {
		// there should first be no database
		{
			IFile db = getProject().getFile("output/test.db");
			assertFalse("db shouldn't exist", db.exists());
		}

		// we can now go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		// go to "page" to initialise the db/application
		goSitemapThenPage(sitemap, "page");

		// refresh the workspace
		assertTrue(refreshProject().isOK());

		// the database should now exist 
		IFile db = getProject().getFile("output/test.db");
		assertTrue("db should exist now that we have initialised the application", db.exists());

		// make sure the database is empty
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:" + db.getLocation());
		Statement stat = conn.createStatement();

		ResultSet rs = stat.executeQuery("select field1 from form1;");
		assertFalse(rs.next());	// it should be empty

	}
		
	/**
	 * We need some way of working out the label ID that contains 
	 * a particular string.
	 * 
	 * @param text
	 * @return
	 */
	protected String getLabelIDForText(String text) {
		IElement element = getElementByXPath("//*[contains(text(),'" + text + "')]");
		return element.getAttribute("id");
	}

}
