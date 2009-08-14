/**
 *
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.jaxen.JaxenException;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;

import ca.ecliptical.emf.xpath.EMFXPath;

/**
 * Assorted methods to assist with loading and inferring models.
 *
 * @see #loadAndInfer(Class)
 * @author jmwright
 */
public abstract class ModelInferenceTestCase extends ModelTestCase {

	protected InternetApplication root;
	
	protected Resource resource;

	/**
	 * When inference is done, the model is saved to this file.
	 */
	protected File inferredModel;

	/**
	 * Load a model file and perform inference on it.
	 */
	protected InternetApplication loadAndInfer(String modelFile) throws Exception {
		InternetApplication root = (InternetApplication) loadModelDirectly(modelFile);
		return loadAndInfer(root, false);
	}

	/**
	 * Automagically load the model file (.iaml) for this given
	 * test class, and do inference.
	 *
	 * @see #loadAndCodegen(String)
	 * @param class1 The test class to load a model for.
	 * @return the loaded and inferred InternetApplication
	 */
	protected InternetApplication loadAndInfer(
			Class<?> class1) throws Exception {
		return loadAndInfer(class1, false);
	}

	/**
	 * Load the model file (.iaml) for this given
	 * test class, and do inference.
	 *
	 * @see #loadAndCodegen(String)
	 * @param class1 The test class to load a model for.
	 * @param logRuleSource Log the rule source of inserted elements.
	 * @return the loaded and inferred InternetApplication
	 */
	protected InternetApplication loadAndInfer(
			String filename, boolean logRuleSource) throws Exception {
		return loadAndInfer((InternetApplication) loadModelDirectly(filename), logRuleSource);
	}

	/**
	 * Automagically load the model file (.iaml) for this given
	 * test class, but don't do inference.
	 *
	 * @see #loadAndInfer(Class)
	 * @param class1 The test class to load a model for.
	 * @return the loaded and inferred InternetApplication
	 */
	protected InternetApplication loadDirectly(
			Class<?> class1) throws Exception {
		return loadDirectly(class1, false);
	}
	
	/**
	 * Get the absolute path root of the testing plugin in the
	 * current filesystem.
	 * 
	 * @return
	 */
	protected String getAbsolutePathRoot() {
		try {
			return FileLocator.resolve(Platform.getBundle("org.openiaml.model.tests").getEntry("/")).getPath();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * Automatically find the model file (.iaml) for the given class.
	 * 
	 * @param class1
	 * @return
	 */
	protected String getModelFileForClass(Class<?> class1) {
		// check that the resolved path actually exists
		File f = new File(getAbsolutePathRoot());
		assertTrue("Resolved absolute path '" + getAbsolutePathRoot() + "' does not exist", f.exists());
		assertTrue("Resolved absolute path '" + getAbsolutePathRoot() + "' is not a directory", f.isDirectory());

		if (class1.getPackage().getName().contains("codegen.model0_1")) {
			return getAbsolutePathRoot() + ROOT + "codegen/model0_1/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.model0_2")) {
			return getAbsolutePathRoot() + ROOT + "codegen/model0_2/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.model0_3")) {
			return getAbsolutePathRoot() + ROOT + "codegen/model0_3/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.model0_4")) {
			return getAbsolutePathRoot() + ROOT + "codegen/model0_4/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.runtime")) {
			return getAbsolutePathRoot() + ROOT + "codegen/runtime/" + class1.getSimpleName() + ".iaml";
		}
		
		// TODO move other inference tests into separate test folders
		if (class1.getPackage().getName().contains("inference.model0_3")) {
			return getAbsolutePathRoot() + ROOT + "inference/model0_3/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("inference.model0_4")) {
			return getAbsolutePathRoot() + ROOT + "inference/model0_4/" + class1.getSimpleName() + ".iaml";
		}
		
		return getAbsolutePathRoot() + ROOT + "inference/" + class1.getSimpleName() + ".iaml";

	}
	
	/**
	 * Automagically load the model file (.iaml) for this given
	 * test class, but don't do inference.
	 *
	 * @see #getModelFileForClass(Class)
	 * @see #loadModelDirectly(Class)
	 * @param class1 The test class to load a model for.
	 * @param logRuleSource Log the rule source of inserted elements.
	 * @return the loaded and inferred InternetApplication
	 */
	protected InternetApplication loadDirectly(
			Class<?> class1, boolean logRuleSource) throws Exception {
		
		return (InternetApplication) loadModelDirectly(getModelFileForClass(class1));

	}

	/**
	 * Create an {@link ICreateElements} handler that can be used
	 * to modify the model.
	 * 
	 * @return
	 */
	protected ICreateElements createHandler() {
		ICreateElements handler = new EcoreInferenceHandler(resource);
		return handler;
	}
	
	/**
	 * Load a model file and perform inference on it.
	 *
	 * @see CreateMissingElementsWithDrools#create(EObject, boolean)
	 * @param logRuleSource Log the rule source of inserted elements.
	 * @return
	 * @throws Exception
	 */
	protected InternetApplication loadAndInfer(InternetApplication root, boolean logRuleSource) throws Exception {
		// we now try to do inference
		ICreateElements handler = createHandler();
		CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(handler, false);
		ce.create(root, logRuleSource, monitor);

		// write out this inferred model for reference
		inferredModel = saveInferredModel();

		return root;
	}
	
	private static Map<Class<?>, File> inferCache = new HashMap<Class<?>, File>();
	
	/**
	 * Load a model file and perform inference on it.
	 * This method also sees if we have got a cached model; if
	 * not, it loads and infers it, then saves the inferred model
	 * location to the cache.
	 *
	 * @see CreateMissingElementsWithDrools#create(EObject, boolean)
	 * @param logRuleSource Log the rule source of inserted elements.
	 * @return
	 * @throws Exception
	 */
	protected InternetApplication loadAndInfer(Class<?> loadClass, boolean logRuleSource) throws Exception {
		if (!inferCache.containsKey(loadClass)) {
			// reload
			InternetApplication root = loadDirectly(loadClass, logRuleSource);
			
			// we now try to do inference
			ICreateElements handler = createHandler();
			CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(handler, false);
			ce.create(root, logRuleSource, monitor);
	
			// write out this inferred model for reference
			inferredModel = saveInferredModel();
			
			// put this model down in the cache
			inferCache.put(loadClass, inferredModel);
			
			return root;
		} else {
			// load it from the given file - it will be inferred already
			System.out.println("Loaded model for '" + loadClass + "' directly from cache '" + inferCache.get(loadClass) + "'");
			
			inferredModel = inferCache.get(loadClass); 
			
			return (InternetApplication) loadModelDirectly(inferCache.get(loadClass).getAbsolutePath()); 
		}
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
		assertEquals("there should only be one contents in the model file", 1, resource.getContents().size());
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
		// xpath.addNamespace("iaml.domain", DomainPackage.eNS_URI);
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
	public EObject queryOne(EObject root, String query) throws JaxenException {
		List<?> q = query(root, query);
		assertEquals("queryOne for '" + query + "' did not return one result", 1, q.size());
		return (EObject) q.get(0);
	}
	
	/**
	 * Assert that there are no results for the given query.
	 *
	 * @param root
	 * @param query
	 * @return
	 * @throws JaxenException
	 */
	public void assertHasNone(EObject root, String query) throws JaxenException {
		List<?> q = query(root, query);
		assertEquals("Unexpected query result for '" + query + "' on '" + root + ": " + q, 0, q.size());
	}
	
	/**
	 * Assert that the given list of elements contains a
	 * NamedElement with the given name and given generated status. 
	 * 
	 * @param elements
	 * @param name
	 * @param isGenerated
	 */
	public void assertContainsNamedElement(List<? extends NamedElement> elements, String name, boolean isGenerated) {
		for (NamedElement e : elements) {
			if (e.getName().equals(name) && e.isIsGenerated() == isGenerated) {
				// ok
				return;
			}
		}
		fail("Did not find any NamedElement '" + name + "' [generated=" + isGenerated + "] in " + elements);
	}
	
	/**
	 * Assert that the given element is generated.
	 * 
	 * @param e
	 */
	public void assertGenerated(GeneratedElement e) {
		assertTrue("Element '" + e + "' should be generated", e.isIsGenerated());
	}

	/**
	 * Assert that the given element is not generated.
	 * 
	 * @param e
	 */
	public void assertNotGenerated(GeneratedElement e) {
		assertFalse("Element '" + e + "' should not be generated", e.isIsGenerated());
	}

	/**
	 * Save the changed, inferred model to a file for later reference.
	 *
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @returns the generated model file
	 */
	protected File saveInferredModel() throws FileNotFoundException, IOException {
		// check that the inference folder exists
		File folder = new File("infer-output/");
		if (!(folder.exists() && folder.isDirectory())) {
			// make it
			assertTrue("Could not make output folder '" + folder + "'", folder.mkdir());
		}
		// it should now exist
		assertTrue(folder.exists());
		assertTrue(folder.isDirectory());
		
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
	 * so we need to parse them manually.
	 *
	 * @param container
	 * @param fromElement
	 * @param toElement
	 * @return the wire found or null
	 * @throws JaxenException
	 */
	protected WireEdge getWireFromTo(EObject container, WireEdgesSource fromElement, WireEdgeDestination toElement) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof WireEdge) {
				WireEdge w = (WireEdge) o;
				if (w.getFrom().equals(fromElement) && w.getTo().equals(toElement))
					return w;
			}
		}

		fail("No wire found from [" + fromElement + "] to [" + toElement + "]");
		return null;
	}

	/**
	 * It's not possible to do something like //iaml:wire[iaml:from='id']
	 * so we need to parse them manually.
	 *
	 * @param container
	 * @param fromElement
	 * @param toElement
	 * @return the wire found or throws an exception
	 * @throws JaxenException
	 */
	protected Set<WireEdge> getWiresFromTo(EObject container, WireEdgesSource fromElement, WireEdgeDestination toElement) throws JaxenException {
		Set<WireEdge> results = new HashSet<WireEdge>();
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof WireEdge) {
				WireEdge w = (WireEdge) o;
				if (w.getFrom().equals(fromElement) && w.getTo().equals(toElement)) {
					results.add(w);
				}
			}
		}

		if (results.isEmpty()) {
			fail("No wires found between [" + fromElement + "] and [" + toElement + "]");
		}

		return results;
	}

	/**
	 * Assert that there are no wires (contained in <code>container//iaml:wires</code>)
	 * that connect the two given elements together.
	 *
	 * @param container
	 * @param fromElement
	 * @param toElement
	 * @throws JaxenException
	 */
	public void assertHasNoWiresFromTo(EObject container, WireEdgesSource fromElement, WireEdgeDestination toElement) throws JaxenException {

		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof WireEdge) {
				WireEdge w = (WireEdge) o;
				if (w.getFrom().equals(fromElement) && w.getTo().equals(toElement)) {
					fail("Unexpected wire found from '" + w.getFrom() + "' to '" + w.getTo() + "'");
				}
			}
		}
		
		// no wires found: pass
	}

	/**
	 * Assert that only the given number of wires exist between
	 * the source and the target.
	 * 
	 * @param count
	 * @param container
	 * @param fromElement
	 * @param toElement
	 * @return 
	 * @throws JaxenException 
	 * @return the found wires
	 */
	public Set<WireEdge> assertHasWiresFromTo(int count, EObject container, 
			WireEdgesSource fromElement, WireEdgeDestination toElement) throws JaxenException {

		Set<WireEdge> wires = getWiresFromTo(container, fromElement, toElement);
		if (wires.size() != count) {
			for (WireEdge wire : wires) {
				System.err.println(wire);
			}
			assertEquals("Expected " + count + " wires between [" + fromElement + "] and [" + toElement + "], found: " + wires.size(), count, wires.size());
		}
		return wires;
		
	}
	
	/**
	 * It's not possible to do something like //iaml:wire[iaml:from='id']
	 * so we need to parse them manually.
	 *
	 * @param container
	 * @param toElement
	 * @return the wire found or null
	 * @throws JaxenException
	 */
	protected Set<WireEdge> getWiresTo(EObject container, WireEdgeDestination toElement) throws JaxenException {
		Set<WireEdge> results = new HashSet<WireEdge>();
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof WireEdge) {
				WireEdge w = (WireEdge) o;
				if (w.getTo().equals(toElement)) {
					results.add(w);
				}
			}
		}

		if (results.isEmpty()) {
			fail("No wires found ti [" + toElement + "]");
		}

		return results;
	}

	/**
	 * For bidirectional wires.
	 *
	 * @param container
	 * @param element1
	 * @param element2
	 * @return the wire found or null
	 * @throws JaxenException
	 */
	protected WireEdge getWireBidirectional(EObject container, WireEdgesSource element1, WireEdgeDestination element2) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof WireEdge) {
				WireEdge w = (WireEdge) o;
				if (w.getFrom().equals(element1) && w.getTo().equals(element2))
					return w;
				if (w.getFrom().equals(element2) && w.getTo().equals(element1))
					return w;
			}
		}

		fail("No wire found between [" + element1 + "] and [" + element2 + "]");
		return null;
	}
	
	/**
	 * For bidirectional wires, get all wires connecting the
	 * two elements.
	 *
	 * @param container
	 * @param element1
	 * @param element2
	 * @throws JaxenException
	 */
	protected Set<WireEdge> getWiresBidirectional(EObject container, WireEdgesSource element1, WireEdgeDestination element2) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires");
		Set<WireEdge> edges = new HashSet<WireEdge>();
		for (Object o : wires) {
			if (o instanceof WireEdge) {
				WireEdge w = (WireEdge) o;
				if (w.getFrom().equals(element1) && w.getTo().equals(element2))
					edges.add(w);
				if (w.getFrom().equals(element2) && w.getTo().equals(element1))
					edges.add(w);
			}
		}

		return edges;
	}

	/**
	 * Assert that a given number of bidirectional wires
	 * occur between the two elements.
	 *
	 * @see #assertHasWiresFromTo(int, EObject, WireEdgesSource, WireEdgeDestination)
	 * @see #getWiresBidirectional(EObject, WireEdgesSource, WireEdgeDestination)
	 * @param container
	 * @param element1
	 * @param element2
	 * @throws JaxenException
	 */
	public void assertHasWiresBidirectional(int count, EObject container, WireEdgesSource element1, WireEdgeDestination element2) throws JaxenException {
		
		Set<WireEdge> wires = getWiresBidirectional(container, element1, element2);
		if (wires.size() != count) {
			for (WireEdge wire : wires) {
				System.err.println(wire);
			}
			assertEquals("Expected " + count + " wires connecting [" + element1 + "] and [" + element2 + "], found: " + wires.size(), count, wires.size());
		}
		
	}

	/**
	 * Assert that no given bidirectional wire exists.
	 * 
	 * @see #getWireBidirectional(EObject, WireEdgesSource, WireEdgeDestination)
	 * @param container
	 * @param element1
	 * @param element2
	 * @throws JaxenException
	 */
	public void assertNoWireBidirectional(EObject container, WireEdgesSource element1, WireEdgeDestination element2) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof WireEdge) {
				WireEdge w = (WireEdge) o;
				if (w.getFrom().equals(element1) && w.getTo().equals(element2))
					fail("Found an unexpected wire between [" + element1 + "] and [" + element2 + "]: " + w);
				if (w.getFrom().equals(element2) && w.getTo().equals(element1))
					fail("Found an unexpected wire between [" + element2 + "] and [" + element1 + "]: " + w);
			}
		}

		// pass
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

	/**
	 * Get the "safe name" of the given element.
	 *
	 * TODO add a test case that makes sure this method is synchronised
	 * with the one in the codegen extensions. OR add the safeName()
	 * method to all elements in the model.
	 *
	 * @param e
	 * @return
	 */
	public String safeName(GeneratedElement e) {
		return e.getId().replaceAll("[^A-Za-z0-9]", "_");
	}

	/**
	 * Get the file representing the saved post-inference model.
	 *
	 * @return
	 */
	public File getInferredModel() {
		return inferredModel;
	}

	@Override
	protected void tearDown() throws Exception {
		// remove reference to resource
		resource = null;
		
		root = null;
		
		super.tearDown();
	}
	

}
