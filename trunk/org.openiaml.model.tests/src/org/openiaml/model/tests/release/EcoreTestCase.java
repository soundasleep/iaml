/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;
import java.io.FileWriter;

import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.tests.XmlTestCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Simple tests to ensure that all copies of .ecores across the
 * plugin are identical.
 * 
 * @author jmwright
 *
 */
public class EcoreTestCase extends XmlTestCase {

	/**
	 * Check the ecore stored in oaw.
	 * @throws Exception 
	 */
	public void testOawEcore() throws Exception {
		File source = new File("../org.openiaml.model/model/iaml.ecore");
		File target = new File("../org.openiaml.model.codegen.php/src/metamodel/iaml-current.ecore");
		
		assertFileExists(source);
		assertFileExists(target);
		
		String sourceString = readFile(source);
		String targetString = readFile(target);
		
		if (!sourceString.equals(targetString)) {
			// copy over the file manually
			if (source.lastModified() < target.lastModified()) {
				fail("Cannot copy over the OAW ecore files: the target file '" + target + "' has been changed since the source file '" + source + "'");
			}
			
			assertTrue("Could not delete '" + target + "'", target.delete());
			
			FileWriter fw = new FileWriter(target);
			fw.write(sourceString);
			fw.close();
			
			System.out.println("Copied '" + source + "' to '" + target + "'");
		}
		
	}
	
	/**
	 * Issue 121: check that model NS URIs are synchronised
	 * across instances. 
	 * 
	 * @throws Exception
	 */
	public void testModelNamespaceURIs() throws Exception {
		String nsURI = ModelPackage.eINSTANCE.getNsURI();
		
		// try org.openiaml.model.codegen.php/plugin.xml
		{
			String file = "../org.openiaml.model.codegen.php/plugin.xml";
			String xpath = "/plugin/extension/metaModel";
			Document doc = loadDocument(file);
			Element node = xpathFirst(doc, xpath);
			String thisns = node.getAttribute("nsURI");
			assertEquals(file + xpath, nsURI, thisns);
		}

		// try org.openiaml.model.edit/plugin.xml
		{
			String file = "../org.openiaml.model.edit/plugin.xml";
			String xpath = "/plugin/extension[@point='org.eclipse.emf.edit.itemProviderAdapterFactories']/factory[@class='org.openiaml.model.model.provider.ModelItemProviderAdapterFactory']";
			Document doc = loadDocument(file);
			Element node = xpathFirst(doc, xpath);
			String thisns = node.getAttribute("uri");
			assertEquals(file + xpath, nsURI, thisns);
		}
		
		// try org.openiaml.model/plugin.xml
		// it doesn't look like GenModel always updates the plugin.xml correctly
		// you may need to delete and regen plugin.xml
		{
			String file = "../org.openiaml.model/plugin.xml";
			String xpath = "/plugin/extension[@point='org.eclipse.emf.ecore.generated_package']/package[@uri='" + nsURI + "']";
			Document doc = loadDocument(file);
			Element node = xpathFirst(doc, xpath);
			String thisns = node.getAttribute("uri");
			assertEquals(file + xpath, nsURI, thisns);
		}
	}
	
}
