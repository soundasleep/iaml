/**
 *
 */
package org.openiaml.model.tests.eclipse.helpers;

import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.diagram.helpers.GetShortcuts;
import org.openiaml.model.model.ModelFactory;
import org.openiaml.model.tests.eclipse.EclipseTestCase;
import org.openiaml.model.tests.model.ContainmentTestCase;
import org.openiaml.model.tests.release.GmfMapTestCase;
import org.openiaml.model.xpath.IterableElementList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Tests the methods in GetShortcuts.
 *
 * @author jmwright
 *
 */
public class GetShortcutsTest extends EclipseTestCase {

	/**
	 * Make sure that all Edges - as defined by the .gmfmaps - 
	 * are correctly implemented in {@link GetShortcuts#getSourceElement(org.eclipse.emf.ecore.EObject)}.
	 * 
	 * @throws Exception
	 */
	public void testGetSourceElement() throws Exception {
		
		for (String name : getEdgeTypes().keySet()) {
			EObject obj = getEdgeTypes().get(name);
			Object value = GetShortcuts.getSourceElement(obj, true);
			// since we have not set the source/target edges of the relationship, this
			// should return null. if GetShortcuts is not correctly implemented, it will
			// throw an exception.
			assertNull("Unexpectedly got non-null for '" + obj + "': " + value, value);
		}
		
		// make sure that if we test for something that isn't an edge, it does
		// throw an exception.
		try {
			EObject node = getNonEdgeNode();
			assertFalse("Node was actually an edge", getEdgeTypes().containsKey(node.eClass().getName()));
			GetShortcuts.getSourceElement(node, true);
			fail("Should have failed for a non-edge node");
		} catch (IllegalArgumentException e) {
			// expected
		}
		
	}
	
	/**
	 * Make sure that all Edges - as defined by the .gmfmaps - 
	 * are correctly implemented in {@link GetShortcuts#getTargetElement(org.eclipse.emf.ecore.EObject)}.
	 * 
	 * @throws Exception
	 */
	public void testGetTargetElement() throws Exception {
		
		for (String name : getEdgeTypes().keySet()) {
			EObject obj = getEdgeTypes().get(name);
			Object value = GetShortcuts.getTargetElement(obj, true);
			// since we have not set the source/target edges of the relationship, this
			// should return null. if GetShortcuts is not correctly implemented, it will
			// throw an exception.
			assertNull("Unexpectedly got non-null for '" + obj + "': " + value, value);
		}
		
		// make sure that if we test for something that isn't an edge, it does
		// throw an exception.
		try {
			EObject node = getNonEdgeNode();
			assertFalse("Node was actually an edge", getEdgeTypes().containsKey(node.eClass().getName()));
			GetShortcuts.getTargetElement(node, true);
			fail("Should have failed for a non-edge node");
		} catch (IllegalArgumentException e) {
			// expected
		}
		
	}
	
	/**
	 * Get a node that is not an edge.
	 * 
	 * @return
	 */
	private EObject getNonEdgeNode() {
		return ModelFactory.eINSTANCE.createInternetApplication();
	}
	
	/**
	 * {@link #getEdgeTypes()} should not be empty.
	 */
	public void testEdgeTypesNotEmpty() throws Exception {
		assertFalse(getEdgeTypes().isEmpty());
	}
	
	private Map<String,EObject> edgeTypes = null;
	
	/**
	 * Get all of the possible edge types. Loaded into the singleton property
	 * {@link #edgeTypes}.
	 * 
	 * @return A list of all edge types, as defined by the .gmfmaps.
	 * @throws XPathExpressionException 
	 */
	private Map<String,EObject> getEdgeTypes() throws XPathExpressionException {
		if (edgeTypes == null) {
			edgeTypes = new HashMap<String,EObject>();
			
			// for each gmfgen 
			for (String filename : GmfMapTestCase.getMapList()) {
				
				Document doc = GmfMapTestCase.getMapCache().get(filename);
				
				IterableElementList nodes = xpath(doc, "/Mapping/links/domainMetaElement");
				for (Element link : nodes) {
					// get the element name (e.g. ProvidesEdge)
					String nodeName = link.getAttribute("href");
					nodeName = nodeName.substring(nodeName.lastIndexOf("/") + 1);
					
					// have we already got this?
					if (edgeTypes.containsKey(nodeName))
						continue;
					
					// find it in the list of classes
					EClass found = null;
					for (EClass cls : ContainmentTestCase.getAllClasses().keySet()) {
						if (cls.getName().equals(nodeName)) {
							// found it
							found = cls;
							break;
						}
					}
					assertNotNull("Could not find EClass for '" + nodeName + "'", found);
					
					// then instantiate it
					EFactory factory = ContainmentTestCase.getAllClasses().get(found);
					EObject obj = factory.create(found);
					assertNotNull("Factory returned a null object for '" + nodeName + "'", obj);
					
					// save it
					edgeTypes.put(nodeName, obj);
				}
				
			}
		}
		
		return edgeTypes;
	}
	
}
