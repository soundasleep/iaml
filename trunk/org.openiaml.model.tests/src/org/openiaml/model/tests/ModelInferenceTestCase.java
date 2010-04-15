/**
 *
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.jaxen.JaxenException;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.codegen.php.CheckModelInstance;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.drools.ICreateElementsFactory;
import org.openiaml.model.model.Action;
import org.openiaml.model.model.ActionDestination;
import org.openiaml.model.model.ActionSource;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelFactory;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.WireSource;
import org.openiaml.model.model.domain.DomainPackage;
import org.openiaml.model.model.scopes.ScopesPackage;
import org.openiaml.model.model.users.RequiresEdgeDestination;
import org.openiaml.model.model.users.RequiresEdgesSource;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ConditionEdgeDestination;
import org.openiaml.model.model.wires.ConditionEdgesSource;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ExtendsEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdgesSource;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.RequiresEdge;
import org.openiaml.model.tests.CachedModelLoader.IModelReloader;

import ca.ecliptical.emf.xpath.EMFXPath;

/**
 * Assorted methods to assist with loading and inferring models.
 *
 * @see ModelTestCaseWithProperties
 * @see #loadAndInfer(Class)
 * @author jmwright
 */
public abstract class ModelInferenceTestCase extends ModelTestCase implements IProvidesInferenceEngine {

	protected InternetApplication root;

	/**
	 * Load a model file and perform inference on it.
	 */
	protected InternetApplication loadAndInfer(final String modelFile) throws Exception {
		return inferer.loadAndInfer(this, modelFile);
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
			final String filename, boolean logRuleSource) throws Exception {
		return inferer.loadAndInfer(this, filename, logRuleSource);
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
		return loader.loadDirectly(class1, false);
	}

	/**
	 * Load a model file directly.
	 * Assumes that it will only contain one element (and tests this with JUnit).
	 * @throws ModelLoadException 
	 */
	public EObject loadModelDirectly(String filename) throws ModelLoadException {
		return loader.loadModelDirectly(filename);
	}
	
	private static ModelSourceResolver resolver = ModelSourceResolver.getInstance();
	private static CachedModelLoader loader = CachedModelLoader.getInstance();
	
	/**
	 * Automatically find the model file (.iaml) for the given class.
	 * 
	 * @param class1
	 * @return
	 */
	protected String getModelFileForClass(Class<?> class1) {
		return resolver.getModelFileForClass(class1);
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
		return loader.loadDirectly(class1, logRuleSource);
	}

	/**
	 * Perform inference on a loaded model.
	 *
	 * @see CreateMissingElementsWithDrools#create(EObject, boolean)
	 * @param logRuleSource Log the rule source of inserted elements.
	 * @return
	 * @throws Exception
	 */
	protected InternetApplication infer(InternetApplication root, boolean logRuleSource, IModelReloader reloader) throws Exception {
		return inferer.infer(this, root, logRuleSource, reloader);
	}
	
	/**
	 * <p>Create a new instance of the inference engine.</p>
	 * 
	 * @return
	 */
	public CreateMissingElementsWithDrools getInferenceEngine(ICreateElementsFactory handler, boolean trackInsertions, final IModelReloader reloader) {
		return new CreateMissingElementsWithDrools(handler, trackInsertions);
	}
	
	private static CachedModelInferer inferer = CachedModelInferer.getInstance();

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
	protected InternetApplication loadAndInfer(final Class<?> loadClass, final boolean logRuleSource) throws Exception {
		return inferer.loadAndInfer(this, loadClass, logRuleSource);
	}

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
	protected InternetApplication loadAndInfer(final Class<?> loadClass, final boolean logRuleSource, IProgressMonitor monitor) throws Exception {
		return inferer.loadAndInfer(this, loadClass, logRuleSource, monitor);
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
		xpath.addNamespace("iaml.domain", DomainPackage.eNS_URI);
		xpath.addNamespace("iaml.scopes", ScopesPackage.eNS_URI);
		xpath.addNamespace("iaml.visual", VisualPackage.eNS_URI);
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
	 * Assert that there are no results for the given XPath query.
	 *
	 * @param root the node on which to execute the XPath query
	 * @param query the query to execute
	 * @throws JaxenException
	 */
	public void assertHasNone(EObject root, String query) throws JaxenException {
		List<?> q = query(root, query);
		assertEquals("Unexpected query result for '" + query + "' on '" + root + ": " + q, 0, q.size());
	}
	
	/**
	 * Assert that there are no results for the given XPath query with
	 * the given type.
	 *
	 * @param root the node on which to execute the XPath query
	 * @param query the query to execute
	 * @param type the type to check for in the results
	 * @throws JaxenException
	 */
	public void assertHasNone(EObject root, String query, Class<?> type) throws JaxenException {
		List<?> q = query(root, query);
		for (Object o : q) {
			if (type.isInstance(o)) {
				fail("Unexpected type '" + type.getName() + "' result for '" + query + "' on '" + root + ": " + o);				
			}
		}

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
	 *
	 * @param container TODO we don't need this parameter
	 * @param from
	 * @param wire
	 * @return the wire found or null
	 * @throws JaxenException
	 */
	protected Wire getWireFrom(EObject container, WireSource from,
			String wire) throws JaxenException {
		for (Wire w : from.getOutWires()) {
			if (w instanceof NamedElement && wire.equals(((NamedElement) w).getName())) {
				return w;
			}
		}
		
		fail("No wire found with name '" + wire + "'");
		return null;
	}
	
	/**
	 *
	 * @param from
	 * @param name
	 * @return the wire found or null
	 * @throws JaxenException
	 */
	protected Action getActionFrom(ActionSource from, String name) throws JaxenException {
		for (Action w : from.getOutActions()) {
			if (w instanceof NamedElement && name.equals(((NamedElement) w).getName())) {
				return w;
			}
		}
		
		fail("No action found with name '" + name + "'");
		return null;
	}	
	
	/**
	 * Get <em>any</em> Wire connecting the given elements,
	 * contained with the given container element or any of its children.
	 * 
	 * <p>It's not possible to do something like //iaml:wire[iaml:from='id']
	 * so we need to parse them manually.
	 *
	 * @param container
	 * @param fromElement
	 * @param toElement
	 * @return the wire found
	 * @throws JaxenException
	 * @throws AssertionFailedError if no wire was found
	 */
	protected Wire getWireFromTo(EObject container, WireSource fromElement, WireDestination toElement) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof Wire) {
				Wire w = (Wire) o;
				if (w.getFrom().equals(fromElement) && w.getTo().equals(toElement))
					return w;
			}
		}

		fail("No wire found from [" + fromElement + "] to [" + toElement + "]");
		return null;
	}

	/**
	 * Get <em>any</em> ParameterWire connecting the given elements,
	 * contained with the given container element or any of its children.
	 * If many exist, this method fails.
	 *
	 * @return the found ParameterEdge
	 * @throws JaxenException
	 * @throws AssertionFailedError if no edge was found, or more than one was found
	 */
	protected ParameterEdge getParameterEdgeFromTo(EObject container, ParameterEdgesSource from, ParameterEdgeDestination to) throws JaxenException {
		List<ParameterEdge> edges = to.getInParameterEdges();
		ParameterEdge result = null;
		int count = 0;
		
		for (ParameterEdge e : edges) {
			if (from.equals(e.getFrom())) {
				count++;
				result = e;
			}
		}
		
		assertEquals("Expected 1 ParameterEdge from '" + from + "' to '" + to + "'", 1, count);
		return result;
	}

	/**
	 * Get <em>all</em> ParameterEdge connecting the given elements,
	 * contained with the given container element or any of its children.
	 *
	 * @return the found ParameterEdges
	 * @throws JaxenException
	 */
	protected Set<ParameterEdge> getParameterEdgesFromTo(EObject container, ParameterEdgesSource from, ParameterEdgeDestination to) throws JaxenException {
		Set<ParameterEdge> result = new HashSet<ParameterEdge>();
		
		List<?> wires = query(container, "//iaml:parameterEdges");
		for (Object o : wires) {
			if (o instanceof ParameterEdge) {
				ParameterEdge w = (ParameterEdge) o;
				if (w.getFrom().equals(from) && w.getTo().equals(to))
					result.add(w);
			}
		}

		return result;
	}
	
	/**
	 * Get <em>all</em> ConditionEdge connecting the given elements,
	 * contained with the given container element or any of its children.
	 *
	 * @return the found ConditionEdges
	 * @throws JaxenException
	 */
	protected Set<ConditionEdge> getConditionEdgesFromTo(EObject container, ConditionEdgesSource from, ConditionEdgeDestination to) throws JaxenException {
		Set<ConditionEdge> result = new HashSet<ConditionEdge>();
		
		List<?> wires = query(container, "//iaml:conditionEdges");
		for (Object o : wires) {
			if (o instanceof ConditionEdge) {
				ConditionEdge w = (ConditionEdge) o;
				if (w.getFrom().equals(from) && w.getTo().equals(to))
					result.add(w);
			}
		}

		return result;
	}
	
	/**
	 * Get <em>all</em> ConditionEdge connecting the given elements,
	 * contained with the given container element or any of its children,
	 * with the given name.
	 *
	 * @param name the name to search for
	 * @return the found ConditionEdges
	 * @throws JaxenException
	 */
	protected Set<ConditionEdge> getConditionEdgesFromTo(EObject container, ConditionEdgesSource from, ConditionEdgeDestination to, String name) throws JaxenException {
		Set<ConditionEdge> result = new HashSet<ConditionEdge>();
		
		List<?> wires = query(container, "//iaml:conditionEdges");
		for (Object o : wires) {
			if (o instanceof ConditionEdge) {
				ConditionEdge w = (ConditionEdge) o;
				if (w.getFrom().equals(from) && w.getTo().equals(to) && name.equals(w.getName()))
					result.add(w);
			}
		}

		return result;
	}
	
	/**
	 * Get <em>all</em> ExtendsEdge connecting the given elements,
	 * contained with the given container element or any of its children.
	 *
	 * @return the found ExtendsEdges
	 * @throws JaxenException
	 */
	protected Set<ExtendsEdge> getExtendsEdgesFromTo(EObject container, ExtendsEdgesSource from, ExtendsEdgeDestination to) throws JaxenException {
		Set<ExtendsEdge> result = new HashSet<ExtendsEdge>();
		
		List<?> wires = query(container, "//iaml:extendsEdges");
		for (Object o : wires) {
			if (o instanceof ExtendsEdge) {
				ExtendsEdge w = (ExtendsEdge) o;
				if (w.getFrom().equals(from) && w.getTo().equals(to))
					result.add(w);
			}
		}

		return result;
	}

	/**
	 * Get <em>all</em> RequiresEdges connecting the given elements,
	 * contained with the given container element or any of its children.
	 *
	 * @return the found RequiresEdges
	 * @throws JaxenException
	 */
	protected Set<RequiresEdge> getRequiresEdgesFromTo(EObject container, RequiresEdgesSource from, RequiresEdgeDestination to) throws JaxenException {
		Set<RequiresEdge> result = new HashSet<RequiresEdge>();
		
		List<?> wires = query(container, "//iaml:requiresEdges");
		for (Object o : wires) {
			if (o instanceof RequiresEdge) {
				RequiresEdge w = (RequiresEdge) o;
				if (w.getFrom().equals(from) && w.getTo().equals(to))
					result.add(w);
			}
		}

		return result;
	}

	/**
	 * Get all of the wires connecting the two elements together of any
	 * class. Does not throw an error if there are no wires.
	 * 
	 * @param container
	 * @param fromElement
	 * @param toElement
	 * @return the wire found or throws an exception
	 * @throws JaxenException
	 * @see #getWiresFromTo(EObject, WireSource, WireDestination, Class)
	 */
	protected Set<Wire> getWiresFromTo(EObject container, WireSource fromElement, WireDestination toElement) throws JaxenException {
		return getWiresFromTo(container, fromElement, toElement, Wire.class);
	}
	
	/**
	 * Get all of the wires connecting the two elements together of any
	 * class. Does not throw an error if there are no wires.
	 * 
	 * @param container
	 * @param fromElement
	 * @param toElement
	 * @return the wire found or throws an exception
	 * @throws JaxenException
	 * @see #getWiresFromTo(EObject, WireSource, WireDestination, Class)
	 */
	protected Set<Action> getActionsFromTo(EObject container, ActionSource fromElement, ActionDestination toElement) throws JaxenException {
		return getActionsFromTo(container, fromElement, toElement, Action.class);
	}
	
	/**
	 * Get all of the wires connecting the two elements together of the
	 * given class. Does not throw an error if there are no wires.
	 *
	 * @param container
	 * @param fromElement
	 * @param toElement
	 * @return the wire found or throws an exception
	 * @throws JaxenException
	 */
	protected Set<Wire> getWiresFromTo(EObject container, WireSource fromElement, WireDestination toElement, Class<? extends Wire> type) throws JaxenException {
		Set<Wire> results = new HashSet<Wire>();
		for (Wire w : fromElement.getOutWires()) {
			if (type.isInstance(w) && w.getTo().equals(toElement)) {
				results.add(w);
			}
		}

		return results;
	}
	
	/**
	 * Get all of the actions connecting the two elements together of the
	 * given class. Does not throw an error if there are no wires.
	 *
	 * @param container
	 * @param fromElement
	 * @param toElement
	 * @return the wire found or throws an exception
	 * @throws JaxenException
	 */
	protected Set<Action> getActionsFromTo(EObject container, ActionSource fromElement, ActionDestination toElement, Class<? extends Action> type) throws JaxenException {
		Set<Action> results = new HashSet<Action>();
		for (Action w : fromElement.getOutActions()) {
			if (type.isInstance(w) && w.getTo().equals(toElement)) {
				results.add(w);
			}
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
	public void assertHasNoWiresFromTo(EObject container, WireSource fromElement, WireDestination toElement) throws JaxenException {

		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof Wire) {
				Wire w = (Wire) o;
				if (w.getFrom().equals(fromElement) && w.getTo().equals(toElement)) {
					fail("Unexpected wire found from '" + w.getFrom() + "' to '" + w.getTo() + "'");
				}
			}
		}
		
		// no wires found: pass
	}


	/**
	 * Assert that there are no wires (contained in <code>container//iaml:wires</code>)
	 * that connect the two given elements together with the given wire class.
	 *
	 * @param container
	 * @param fromElement
	 * @param toElement
	 * @throws JaxenException
	 */
	public void assertHasNoWiresFromTo(EObject container, WireSource fromElement, WireDestination toElement, Class<? extends Wire> wireClass) throws JaxenException {
		for (Wire wire : getWiresFromTo(container, fromElement, toElement)) {
			if (wireClass.isInstance(wire)) {
				fail("Found wire '" + wire + "' (" + wireClass + ") from '" + fromElement + "' to '" + toElement + "'");
			}
		}
		
		// pass
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
	public Set<Wire> assertHasWiresFromTo(int count, EObject container, 
			WireSource fromElement, WireDestination toElement) throws JaxenException {

		Set<Wire> wires = getWiresFromTo(container, fromElement, toElement);
		if (wires.size() != count) {
			for (Wire wire : wires) {
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
	protected Set<Wire> getWiresTo(EObject container, WireDestination toElement) throws JaxenException {
		Set<Wire> results = new HashSet<Wire>();
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof Wire) {
				Wire w = (Wire) o;
				if (w.getTo().equals(toElement)) {
					results.add(w);
				}
			}
		}

		if (results.isEmpty()) {
			fail("No wires found to [" + toElement + "]");
		}

		return results;
	}
	
	/**
	 * It's not possible to do something like //iaml:wire[iaml:from='id']
	 * so we need to parse them manually.
	 *
	 * @param container
	 * @param fromElement
	 * @return the wire found or null
	 * @throws JaxenException
	 */
	protected Set<Wire> getWiresFrom(EObject container, WireSource fromElement) throws JaxenException {
		Set<Wire> results = new HashSet<Wire>();
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof Wire) {
				Wire w = (Wire) o;
				if (w.getFrom().equals(fromElement)) {
					results.add(w);
				}
			}
		}

		if (results.isEmpty()) {
			fail("No wires found from [" + fromElement + "]");
		}

		return results;
	}

	protected void assertNoWiresFrom(EObject container, WireSource fromElement, Class<? extends Wire> cls) throws JaxenException {
		for (Wire w : fromElement.getOutWires()) {
			if (cls.isInstance(w)) {
				fail("Unexpectedly found wire '" + w + "' of type '" + cls + "' from element '" + fromElement + "'");
			}
		}
	}

	protected void assertNoActionsFrom(EObject container, ActionSource fromElement, Class<? extends Action> cls) throws JaxenException {
		for (Action w : fromElement.getOutActions()) {
			if (cls.isInstance(w)) {
				fail("Unexpectedly found action '" + w + "' of type '" + cls + "' from element '" + fromElement + "'");
			}
		}
	}
	
	/**
	 * Get all wires to the given element of the given EObject type.
	 * 
	 * @param container
	 * @param toElement
	 * @param type
	 * @return
	 * @throws JaxenException
	 */
	protected Set<Wire> getWiresTo(EObject container, WireDestination toElement, Class<? extends Wire> type) throws JaxenException {
		return typeSelect(getWiresTo(container, toElement), type);
	}
	
	/**
	 * Get all wires from the given element of the given EObject type.
	 * 
	 * @param container
	 * @param fromElement
	 * @param type
	 * @return
	 * @throws JaxenException
	 */
	protected Set<Wire> getWiresFrom(EObject container, WireSource fromElement, Class<? extends Wire> type) throws JaxenException {
		return typeSelect(getWiresFrom(container, fromElement), type);
	}
	
	/**
	 * Get all elements of the set which are instances of the given
	 * type.
	 * 
	 * @param collection
	 * @param type
	 * @return
	 */
	public Set<Wire> typeSelect(Set<? extends Wire> collection, Class<? extends Wire> type) {
		Set<Wire> result = new HashSet<Wire>();
		for (Wire o : collection) {
			if (type.isInstance(o))
				result.add(o);
		}
		return result;
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
	protected Wire getWireBidirectional(EObject container, WireSource element1, WireDestination element2) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof Wire) {
				Wire w = (Wire) o;
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
	 * @see #getWiresBidirectional(EObject, WireSource, WireDestination, Class)
	 * @param container
	 * @param element1
	 * @param element2
	 * @throws JaxenException
	 */
	protected Set<Wire> getWiresBidirectional(EObject container, WireSource element1, WireSource element2) throws JaxenException {
		return getWiresBidirectional(container, element1, element2, Wire.class);
	}

	/**
	 * For bidirectional wires, get all wires connecting the
	 * two elements, with the given type.
	 */
	protected Set<Wire> getWiresBidirectional(EObject container, WireSource element1, WireSource element2, Class<? extends Wire> type) throws JaxenException {
		Set<Wire> edges = new HashSet<Wire>();
		for (Wire w : element1.getOutWires()) {
			if (type.isInstance(w)) {
				if (w.getFrom().equals(element1) && w.getTo().equals(element2))
					edges.add(w);
				if (w.getFrom().equals(element2) && w.getTo().equals(element1))
					edges.add(w);
			}
		}
		for (Wire w : element2.getOutWires()) {
			if (type.isInstance(w)) {
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
	 * @see #assertHasWiresFromTo(int, EObject, WireSource, WireSource)
	 * @see #getWiresBidirectional(EObject, WireSource, WireSource)
	 * @see #assertHasWiresBidirectional(int, EObject, WireSource, WireSource, Class)
	 * @param container
	 * @param element1
	 * @param element2
	 * @return the wires found
	 * @throws JaxenException
	 */
	public Set<Wire> assertHasWiresBidirectional(int count, EObject container, WireSource element1, WireSource element2) throws JaxenException {
		return assertHasWiresBidirectional(count, container, element1, element2, Wire.class);
	}
	
	/**
	 * Assert that a given number of bidirectional wires
	 * occur between the two elements, with the given type.
	 * 
	 * @return the wires found
	 */
	public Set<Wire> assertHasWiresBidirectional(int count, EObject container, WireSource element1, WireSource element2, Class<? extends Wire> type) throws JaxenException {
		
		Set<Wire> wires = getWiresBidirectional(container, element1, element2, type);
		if (wires.size() != count) {
			for (Wire wire : wires) {
				System.err.println(wire);
			}
			assertEquals("Expected " + count + " wires of type '" + type.getName() + "' connecting [" + element1 + "] and [" + element2 + "], found: " + wires.size(), count, wires.size());
		}
		return wires;
		
	}

	/**
	 * Assert that no given bidirectional wire exists.
	 * 
	 * @see #getWireBidirectional(EObject, WireSource, WireDestination)
	 * @param container
	 * @param element1
	 * @param element2
	 * @throws JaxenException
	 */
	public void assertNoWireBidirectional(EObject container, WireSource element1, WireDestination element2) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof Wire) {
				Wire w = (Wire) o;
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
	protected Wire getWireFrom(EObject container, EObject fromElement) throws JaxenException {
		List<?> wires = query(container, "//iaml:wires");
		for (Object o : wires) {
			if (o instanceof Wire) {
				Wire w = (Wire) o;
				if (w.getFrom().equals(fromElement))
					return w;
			}
		}

		fail("no wire found");
		return null;
	}
	
	/**
	 * <p>When we infer a model, we could assert that it is valid
	 * according to the checks. Generally, this is already called when
	 * generating code from the inferred model.</p>
	 * 
	 * <p>We are especially strict; we assert that there are no
	 * errors <em>or warnings</em> in the result.</p>
	 * 
	 * @throws Exception
	 */
	public void checkModelIsValid(EObject root) throws Exception {
		
		IFile target = getProject().getFile( getInferredModel().getName() );
		assertFalse("File '" + target + "' should not exist", target.exists());
		copyFileIntoWorkspace(getInferredModel(), target);
		assertTrue("File '" + target + "' should now exist", target.exists());
		
		CheckModelInstance check = new CheckModelInstance();
		IStatus result = check.checkModel(root, getProject().getProject(), new NullProgressMonitor());
		
		ModelInferenceTestCase.assertStatusIsOK(result);
		
	}

	/**
	 * Assert that the IStatus is ok.
	 * 
	 * @param status the status to check
	 * @see #assertStatusIsNotOK(IStatus)
	 * @throws Exception if there was a Throwable in the IStatus
	 */
	public static void assertStatusIsOK(IStatus status) throws Exception {
		if (!status.isOK()) {
			if (status.getException() != null) {
				// rethrow
				throw new RuntimeException(status.getMessage(), status.getException());
			}
			
			if (status.isMultiStatus()) {
				// build up the message to alert the developer
				MultiStatus ms = (MultiStatus) status;
				StringBuffer msg = new StringBuffer();
				msg.append("Status was not OK: [" + status.getPlugin() + "] " + status.getMessage());
				for (IStatus s : ms.getChildren()) {
					msg.append("\n").append(s.getMessage());
				}
				fail(msg.toString());
			}
			
			// default fail
			fail("Status was not OK: [" + status.getPlugin() + "] " + status.getMessage());
		}
	}
	
	/**
	 * Assert that the IStatus is <em>not</em> ok.
	 * 
	 * @param status the status to check
	 * @see #assertStatusIsOK(IStatus)
	 */
	public static void assertStatusIsNotOK(IStatus status) {
		if (status.isOK()) {
			// default fail
			fail("Status was unexpectedly OK: [" + status.getPlugin() + "] " + status.getMessage());
		}
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
		return inferer.getInferredModel();
	}

	@Override
	protected void tearDown() throws Exception {
		root = null;
		
		super.tearDown();
	}

	/**
	 * Create a new InternetApplication in the given file.
	 * 
	 * @param file
	 */
	public InternetApplication createNewInternetApplication(File file) {

		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource resource = resourceSet.createResource(uri);
		assertNotNull(resource);
		assertEquals("The new model should be empty.", 0, resource.getContents().size());

		// create a new object
		InternetApplication a = ModelFactory.eINSTANCE.createInternetApplication();
		resource.getContents().add(a);
		
		// return
		return a;
	}
	
	/**
	 * Save the changed, inferred model to a file for later reference.
	 *
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @returns the generated model file
	 */
	public File saveInferredModel(Resource resource) throws FileNotFoundException, IOException {
		// check that the inference folder exists
		File folder = new File("infer-output/");
		if (!(folder.exists() && folder.isDirectory())) {
			// make it
			boolean successful = folder.mkdir();
			if (!successful)
				throw new IOException("Could not make folder '" + folder + "'");
		}
		
		// it should now exist
		if (!folder.exists()) {
			throw new RuntimeException("Folder '" + folder + "' did not exist.");
		}
		if (!folder.isDirectory()) {
			throw new RuntimeException("Folder '" + folder + "' was not a directory.");
		}
		
		File tempJavaFile = new File("infer-output/" + this.getClass().getSimpleName() + ".iaml");
		Map<?,?> options = resource.getResourceSet().getLoadOptions();
		resource.save(new FileOutputStream(tempJavaFile), options);
		System.out.println("inferred model saved to: " + tempJavaFile.getAbsolutePath());
		return tempJavaFile;
	}
	
	/**
	 * Save the model stored in the given resource to the given file.
	 * 
	 * @param resource
	 * @param file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void saveModel(Resource resource, File file) throws FileNotFoundException, IOException {
		
		// save the model		
		Map<?,?> options = resource.getResourceSet().getLoadOptions();
		resource.save(new FileOutputStream(file), options);
		System.out.println("inferred model saved to: " + file.getAbsolutePath());
	}
	
}
