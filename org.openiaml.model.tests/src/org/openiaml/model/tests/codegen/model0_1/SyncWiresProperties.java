/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_1;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Testing FileDomainObjects synchronisation with InputForms.
 * 
 * <code>
 * Page target
 *   InputForm target form
 *     /InputTextField ...
 *     SyncWire to: properties
 * FileDomainStore properties file [SyncWiresProperties.properties)
 *   /FileDomainObject properties
 *     /FileDomainAttribute ...
 * </code>
 * 
 * We expect that:
 * <ol>
 *   <li>Since the target form has no elements in it, they will be inferred
 *     from the sync wire ({@link #testFormInference()}) </li>
 *   <li>The .properties file is copied over to the output destination, and
 *     exists ({@link #testCopyProperties()})</li>
 *   <li>The wire starts out with values derived from the properties file
 *   	({@link #testPropertiesInitial()})</li>
 *   <li>If we change a value in a field, it persists over sessions
 *   	({@link #testPropertiesPersist()})</li>
 *   <li>If we change a value in a field, it changes in the .properties file
 *   	({@link #testPropertiesChangeFile()})</li>
 * </ol>
 * 
 * @author jmwright
 *
 */
public class SyncWiresProperties extends CodegenTestCase {

	protected static final String PROPERTIES = "SyncWiresProperties.properties";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SyncWiresProperties.class);
	}
	
	/**
	 * Copy the properties file into the local project workspace.
	 * 
	 * @see #PROPERTIES
	 * @return
	 * @throws Exception
	 */
	protected IFile copyProperties() throws Exception {
		// copy file
		IFile target = getProject().getFile("output/" + PROPERTIES);
		copyFileIntoWorkspace(ROOT + "codegen/model0_1/" + PROPERTIES, target);
		
		return target;
	}
	
	/**
	 * Since the target form has no elements in it, they will be inferred
	 * from the sync wire.
	 * 
	 * @throws Exception
	 */
	public void testFormInference() throws Exception {
		// go to sitemap
		beginAtSitemapThenPage("container");
		
		// there should be some fields inferred automatically
		String fruitId = getLabelIDForText("fruit");
		String animalId = getLabelIDForText("animal");
		String countryId = getLabelIDForText("country");
		
		assertNotEqual(fruitId, animalId);
		assertNotEqual(countryId, animalId);
		assertNotEqual(animalId, countryId);
	}
	
	/**
	 * The .properties file is copied over to the output destination, and
	 * exists
	 * 
	 * @throws Exception
	 */
	public void testCopyProperties() throws Exception {
		// copy properties
		IFile target = copyProperties();
		
		// it should exist
		assertTrue("Properties file should be copied over", target.exists());
	}

	public void testPropertiesInitial() throws Exception {
		// copy properties
		copyProperties();

		// go to sitemap
		beginAtSitemapThenPage("container");
		
		// there should be some fields inferred automatically
		String fruitId = getLabelIDForText("fruit");
		String animalId = getLabelIDForText("animal");
		String countryId = getLabelIDForText("country");
		
		// check that they are as expected
		assertLabeledFieldEquals(fruitId, "apple");
		assertLabeledFieldEquals(animalId, "cat");
		assertLabeledFieldEquals(countryId, "New Zealand");
	}

	/*
	 * When we load a properties page, it shouldn't be calling
	 * the database yet, because the data should be coming from
	 * init --> access, which doesn't call the update operation 
	 * (which we define as calling the edit trigger every time)
	 * 
	 * BUT since they are not stored in a Session, but on the
	 * Server, we must still keep the values of these fields on the
	 * server.
	 */

	public void testPropertiesPersist() throws Exception {
		// copy properties
		copyProperties();

		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("container");
		waitForAjax();
		
		// there should be some fields inferred automatically
		String newFruit = "fruit " + new Date().toString();
		String oldFruit = "apple";
		{
			String fruitId = getLabelIDForText("fruit");
			
			// lets set it
			setLabeledFormElementField(fruitId, newFruit);
			assertLabeledFieldEquals(fruitId, newFruit);
		}
		
		// reload the page
		gotoSitemapThenPage(sitemap, "container");
		waitForAjax();
		
		// check it
		{
			String fruitId = getLabelIDForText("fruit");
			assertLabeledFieldEquals(fruitId, newFruit);
			
			// then go back and reset it
			setLabeledFormElementField(fruitId, oldFruit);
			assertLabeledFieldEquals(fruitId, oldFruit);
		}
		
	}
	
	public void testPropertiesChangeFile() throws Exception {
		// copy properties
		IFile target = copyProperties();

		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("container");
		
		// there should be some fields inferred automatically
		String newAnimal = "animal " + new Date().toString();
		String oldAnimal = "cat";
		{
			String fruitId = getLabelIDForText("animal");
			
			// lets set it
			setLabeledFormElementField(fruitId, newAnimal);
			assertLabeledFieldEquals(fruitId, newAnimal);
		}
		
		// open up the properties file
		waitForAjax();
		Properties p = new Properties();
		target.refreshLocal(IResource.DEPTH_INFINITE, monitor);	// refresh
		InputStream stream = target.getContents();
		p.load(stream);
		stream.close();
		getProject().refreshProject();
		
		// check it has changed
		assertEquals("The property should have changed in the file.", p.getProperty("animal", "[did not exist]"), newAnimal);
		
		// reload the page
		gotoSitemapThenPage(sitemap, "container");
		
		// go back and reset it
		{
			String fruitId = getLabelIDForText("animal");
			setLabeledFormElementField(fruitId, oldAnimal);
			assertLabeledFieldEquals(fruitId, oldAnimal);
		}
		
	}
	
}
