/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.util.Date;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.openiaml.model.model.InternetApplication;
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
	
	protected InternetApplication root;
	protected final String PROPERTIES = "SyncWiresProperties.properties";
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "codegen/SyncWiresProperties.iaml");
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
		copyFileIntoWorkspace(ROOT + "codegen/" + PROPERTIES, target);
		
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
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page1
		beginAtSitemapThenPage(sitemap, "container");
		
		// there should be some fields inferred automatically
		String fruitId = getLabelIDForText("fruit");
		assertNotNull(fruitId);
		String animalId = getLabelIDForText("animal");
		assertNotNull(animalId);
		String countryId = getLabelIDForText("country");
		assertNotNull(countryId);
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
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page1
		beginAtSitemapThenPage(sitemap, "container");
		
		// there should be some fields inferred automatically
		String fruitId = getLabelIDForText("fruit");
		assertNotNull(fruitId);
		String animalId = getLabelIDForText("animal");
		assertNotNull(animalId);
		String countryId = getLabelIDForText("country");
		assertNotNull(countryId);
		
		// check that they are as expected
		assertLabeledFieldEquals(fruitId, "apple");
		assertLabeledFieldEquals(animalId, "cat");
		assertLabeledFieldEquals(countryId, "New Zealand");
	}
	
	public void testPropertiesPersist() throws Exception {
		// copy properties
		copyProperties();

		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page1
		beginAtSitemapThenPage(sitemap, "container");
		
		// there should be some fields inferred automatically
		String newFruit = "fruit " + new Date().toString();
		String oldFruit = "apple";
		{
			String fruitId = getLabelIDForText("fruit");
			assertNotNull(fruitId);
			
			// lets set it
			setLabeledFormElementField(fruitId, newFruit);
			assertLabeledFieldEquals(fruitId, newFruit);
		}
		
		// reload the page
		gotoSitemapThenPage(sitemap, "container");
		
		// check it
		{
			String fruitId = getLabelIDForText("fruit");
			assertNotNull(fruitId);
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
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page1
		beginAtSitemapThenPage(sitemap, "container");
		
		// there should be some fields inferred automatically
		String newAnimal = "animal " + new Date().toString();
		String oldAnimal = "cat";
		{
			String fruitId = getLabelIDForText("animal");
			assertNotNull(fruitId);
			
			// lets set it
			setLabeledFormElementField(fruitId, newAnimal);
			assertLabeledFieldEquals(fruitId, newAnimal);
		}
		
		// open up the properties file
		waitForAjax();
		Properties p = new Properties();
		target.refreshLocal(IResource.DEPTH_INFINITE, monitor);	// refresh
		p.load(target.getContents());
		
		// check it has changed
		assertEquals("The property should have changed in the file.", p.getProperty("animal", "[did not exist]"), newAnimal);
		
		// reload the page
		gotoSitemapThenPage(sitemap, "container");
		
		// go back and reset it
		{
			String fruitId = getLabelIDForText("animal");
			assertNotNull(fruitId);
			setLabeledFormElementField(fruitId, oldAnimal);
			assertLabeledFieldEquals(fruitId, oldAnimal);
		}
		
	}
	
}
