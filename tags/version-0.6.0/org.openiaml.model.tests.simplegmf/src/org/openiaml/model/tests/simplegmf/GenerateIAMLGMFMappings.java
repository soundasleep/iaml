/**
 * 
 */
package org.openiaml.model.tests.simplegmf;

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
import org.openiaml.model.ModelLoader;
import org.openiaml.simplegmf.codegen.SimpleGMFCodeGenerator;
import org.openiaml.simplegmf.model.simplegmf.GMFConfiguration;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author jmwright
 *
 */
public class GenerateIAMLGMFMappings extends TestCase {
	
	private static final String MODEL_ROOT = "../org.openiaml.model/model/";

	/**
	 * This is essentially a script to generate the .gmfXXX mappings from
	 * the <code>/org.openiaml.model/model/iaml.simplegmf</code> file.
	 * 
	 * @throws Exception
	 */
	public void testGenerateIAMLGMFMappings() throws Exception {
		
		// load model
		EObject model = ModelLoader.load(new File(MODEL_ROOT + "iaml.simplegmf"));
		assertNotNull(model);
		assertTrue(model.getClass().getName(), model instanceof GMFConfiguration);

		// generate code
		SimpleGMFCodeGenerator codegen = new SimpleGMFCodeGenerator();
		IStatus status = codegen.generateCode(model, MODEL_ROOT);

		// check everything is OK
		assertTrue(status.toString(), status.isOK());
		
		// check that files are created;
		String[] filenames = new String[] {
				"iaml.gmfgraph",
				"root.gmftool",
				"visual.gmftool",
				"frame.gmftool",
				"root.gmfmap",
				"visual.gmfmap",
				"frame.gmfmap",
		};
		
		for (String f : filenames) {
			// check the file exists
			File ff = new File(MODEL_ROOT + f);
			assertTrue(ff + " should exist", ff.exists());
			assertFalse(ff + " should not be a directory", ff.isDirectory());

			// and is a valid XML document
			loadDocument(ff);
		}
		
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
