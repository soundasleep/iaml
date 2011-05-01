/**
 * 
 */
package org.openiaml.simplegmf.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.simplegmf.codegen.SimpleGMFCodeGenerator;
import org.openiaml.simplegmf.model.simplegmf.GMFConfiguration;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Tests the code generation functionality of SimpleGMF.
 * 
 * @author jmwright
 *
 */
public class SimpleGMFTestCase extends TestCase {

	/**
	 * 
	 * @throws Exception
	 */
	public void testDump() throws Exception {
		
		// delete everything in output folder
		File outputFolder = new File("output");
		if (outputFolder.exists() && outputFolder.isDirectory()) {
			for (File f : outputFolder.listFiles()) {
				if (!f.isDirectory()) {
					assertTrue("Could not delete " + f, f.delete());
				}
			}
		}

		File localFile = new File("model/test.simplegmf");
		assertTrue(localFile + " should exist", localFile.exists());
		
		// load model
		EObject model = ModelLoader.load(new File("model/test.simplegmf"));
		assertNotNull(model);
		assertTrue(model.getClass().getName(), model instanceof GMFConfiguration);
		
		// generate code
		SimpleGMFCodeGenerator codegen = new SimpleGMFCodeGenerator();
		IStatus status = codegen.generateCode(model, "./output");
		
		// check everything is OK
		assertTrue(status.toString(), status.isOK());
		
		// check that files are created;
		String[] filenames = new String[] {
				"iaml.gmfgraph",
				"root.gmftool",
		};
		
		for (String f : filenames) {
			// check the file exists
			File ff = new File("output/" + f);
			assertTrue(ff + " should exist", ff.exists());
			assertFalse(ff + " should not be a directory", ff.isDirectory());

			// and is a valid XML document
			loadDocument(ff);
		}
		
		// files should be created
		File check = new File("output/root.gmftool");
		assertTrue(check + " should exist", check.exists());
		
	}
	
	/**
	 * Load an XML document.
	 * TODO refactor out into helper class.
	 */
	public static Document loadDocument(String filename) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		return loadDocument( new FileInputStream(filename) );
	}

	/**
	 * Load an XML document from a file.
	 * TODO refactor out into helper class.
	 */
	public static Document loadDocument(File f) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		return loadDocument( new FileInputStream(f) );
	}
	
	/**
	 * Load an XML document from an input source.
	 * TODO refactor out into helper class.
	 */
	public static Document loadDocument(InputStream source) throws ParserConfigurationException, SAXException, IOException {
		// load the model version
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(source);
		
		// done
		source.close();
		
		return doc;
	}

	
}
