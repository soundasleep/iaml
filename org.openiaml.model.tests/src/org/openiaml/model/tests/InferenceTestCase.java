/**
 * 
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import junit.framework.AssertionFailedError;

import net.sourceforge.jwebunit.api.IElement;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.jaxen.JaxenException;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.WireEdge;

import ca.ecliptical.emf.xpath.EMFXPath;

/**
 * @author jmwright
 *
 */
public abstract class InferenceTestCase extends ModelTestCase {

	protected Resource resource;

	/**
	 * Load a model file and perform inference on it.
	 */
	protected InternetApplication loadAndInfer(String modelFile) throws Exception {
		EObject model = loadModelDirectly(modelFile);
		assertTrue("the model file '" + modelFile + "' should be of type InternetApplication", model instanceof InternetApplication);
		assertNotNull(model);
		
		InternetApplication root = (InternetApplication) model;
		
		// we now try to do inference
		ICreateElements handler = new EcoreInferenceHandler(resource);
		CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(handler);
		ce.create(root);
		
		// write out this inferred model for reference
		saveInferredModel();	
		
		return root;
	}
	
	/**
	 * Load a model file directly.
	 * Assumes that it will only contain one element (and tests this with JUnit).
	 */
	protected EObject loadModelDirectly(String filename) {
		ResourceSet resourceSet = new ResourceSetImpl(); 
		URI uri = URI.createFileURI(filename);
		resource = resourceSet.getResource(uri, true);
		assertNotNull(resource);
		assertEquals("there should only be one contents in the model file", resource.getContents().size(), 1);
		return resource.getContents().get(0);
	}

	/**
	 * Perform an XPath-like query on an EMF object
	 * 
	 * @param root
	 * @param query
	 * @return
	 * @throws JaxenException
	 */
	public static List<?> query(final EObject root, String query) throws JaxenException {
		EMFXPath xpath = new EMFXPath(query);
		xpath.addNamespace("iaml", ModelPackage.eNS_URI);
		xpath.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		return xpath.selectNodes(root);
	}
	
	/**
	 * Helper method: print out a list of objects
	 * @param obj
	 */
	protected void dump(List<Object> obj) {
		for (Object o : obj) 
			EMFXPath.dump(o, System.out);
		System.out.println("-");
	}

	/**
	 * Helper method: print out an objects
	 * @param obj
	 */
	protected void dump(Object o) {
		EMFXPath.dump(o, System.out);
	}
	
	/**
	 * Helper method: perform a query, but assert that there is only
	 * one result returned, and it is of type EObject
	 * 
	 * @param root
	 * @param query
	 * @return
	 * @throws JaxenException 
	 */
	protected EObject queryOne(EObject root, String query) throws JaxenException {
		List<?> q = query(root, query);
		assertEquals("queryOne for '" + query + "' had no results", 1, q.size());
		return (EObject) q.get(0);
	}

	/**
	 * Save the changed, inferred model to a file for later reference.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @returns the generated model file
	 */
	protected File saveInferredModel() throws FileNotFoundException, IOException {
		File tempJavaFile = new File("infer-output/" + this.getClass().getSimpleName() + ".iaml");
		Map<?,?> options = resource.getResourceSet().getLoadOptions();
		resource.save(new FileOutputStream(tempJavaFile), options);
		System.out.println("inferred model saved to: " + tempJavaFile.getAbsolutePath());
		return tempJavaFile;
	}


	/**
	 * It's not possible to do something like //iaml:wire[iaml:from='id']
	 * so we need to parse them manually?
	 * 
	 * @param container
	 * @param fromElement
	 * @param wireName
	 * @return the wire found or null
	 * @throws JaxenException 
	 */
	protected WireEdge getWireFrom(EObject container, EObject fromElement,
			String wireName) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires[iaml:name='" + wireName + "']");
		for (Object o : wires) {
			if (o instanceof WireEdge && ((WireEdge) o).getFrom().equals(fromElement))
				return (WireEdge) o;
		}
		
		fail("no wire found");
		return null;
	}

	/**
	 * It's not possible to do something like //iaml:wire[iaml:from='id']
	 * so we need to parse them manually?
	 * 
	 * @param container
	 * @param fromElement
	 * @param toElement
	 * @return the wire found or null
	 * @throws JaxenException 
	 */
	protected WireEdge getWireFromTo(EObject container, EObject fromElement, EObject toElement) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof WireEdge) {
				WireEdge w = (WireEdge) o;
				if (w.getFrom().equals(fromElement) && w.getTo().equals(toElement))
					return w;
			}
		}
		
		fail("no wire found");
		return null;
	}

	/**
	 * It's not possible to do something like //iaml:wire[iaml:from='id']
	 * so we need to parse them manually?
	 * 
	 * @param container
	 * @param fromElement
	 * @return the wire found or null
	 * @throws JaxenException 
	 */
	protected WireEdge getWireFrom(EObject container, EObject fromElement) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof WireEdge) {
				WireEdge w = (WireEdge) o;
				if (w.getFrom().equals(fromElement))
					return w;
			}
		}
		
		fail("no wire found");
		return null;
	}

	// TODO move methods below into separate CodegenTestCase
	
	/**
	 * Have we loaded at least one page (so we can find an ajax_monitor if necessary)?
	 */
	private boolean hasLoaded = false;
	protected void goSitemapThenPage(IFile sitemap, String pageText) throws Exception {
		// sleep a little bit first, so ajax calls can continue
		if (hasLoaded) {
			if (getElementById("ajax_monitor") == null) {
				Thread.sleep(2000);	// sleep for way too long, since we don't know when it will finish
			} else {
				int cycles = 0;
				while (cycles < 500) {		// max 15 seconds
					try {
						IElement monitor = getElementById("ajax_monitor");
						String text = monitor.getTextContent();
						if (text != null && (text.isEmpty() || new Integer(text) == 0))		// all ajax calls have finished
							break;		// completed; we can carry on the test case
						
						if (text.length() > 6 && text.substring(0, 6).equals("failed"))
							throw new Exception("Ajax loading failed: " + monitor.getTextContent());
					
						// carry on sleeping
						Thread.sleep(30);
					} catch (AssertionFailedError e) {
						// the monitor was not found
						Thread.sleep(300);
					}
					cycles++;
				}
				
			}
		}
		
		beginAt(sitemap.getProjectRelativePath().toString());
		hasLoaded = true;		// we have now loaded a page
		assertTitleMatch("sitemap");
		
		assertLinkPresentWithText(pageText);
		clickLinkWithText(pageText);
		try {
			assertTitleMatch(pageText);
		} catch (AssertionFailedError e) {
			// something went wrong in the page execution, or
			// the output is mangled HTML: output page source for debug purposes
			System.out.println(this.getPageSource());
			throw e;	// carry on throwing
		}
		
	}
	
}
