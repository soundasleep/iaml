/**
 * 
 */
package org.openiaml.model.tests.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.ModelFactory;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.components.ComponentsFactory;
import org.openiaml.model.model.components.ComponentsPackage;
import org.openiaml.model.model.domain.DomainFactory;
import org.openiaml.model.model.domain.DomainPackage;
import org.openiaml.model.model.operations.OperationsFactory;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.scopes.ScopesFactory;
import org.openiaml.model.model.scopes.ScopesPackage;
import org.openiaml.model.model.users.UsersFactory;
import org.openiaml.model.model.users.UsersPackage;
import org.openiaml.model.model.visual.VisualFactory;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.model.wires.WiresFactory;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * Test model elements
 * 
 * @author jmwright
 *
 */
public class ModelTestCase extends TestCase {

	private static Map<EPackage,EFactory> factoryMap;
	
	/**
	 * <p>Set up a map of all available packages to their
	 * respective factories.</p>
	 * 
	 * <p>By going over all elements and finding the factories later,
	 * as opposed to iterating over existing factories, we will make
	 * sure that any new packages will throw an assertion rather
	 * than being silently ignored.</p>
	 */
	public static Map<EPackage,EFactory> getFactoryMap() {
		if (factoryMap == null) {
			factoryMap = new HashMap<EPackage,EFactory>();
			factoryMap.put( ModelPackage.eINSTANCE , ModelFactory.eINSTANCE );
			factoryMap.put( VisualPackage.eINSTANCE , VisualFactory.eINSTANCE );
			factoryMap.put( ScopesPackage.eINSTANCE , ScopesFactory.eINSTANCE );
			factoryMap.put( OperationsPackage.eINSTANCE , OperationsFactory.eINSTANCE );
			factoryMap.put( WiresPackage.eINSTANCE , WiresFactory.eINSTANCE );
			factoryMap.put( ComponentsPackage.eINSTANCE , ComponentsFactory.eINSTANCE );
			factoryMap.put( DomainPackage.eINSTANCE , DomainFactory.eINSTANCE );
			factoryMap.put( UsersPackage.eINSTANCE , UsersFactory.eINSTANCE );
		}
		return factoryMap;
	}

	/**
	 * Ensure that all elements in this model, that
	 * can have generated IDs, are generated.
	 */
	public void testGeneratedIDs() {
		try {
			iterateGeneratedIDs(ModelPackage.eINSTANCE);
		} catch (AssertionFailedError e) {
			System.err.println(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Iterate over a package, checking its elements
	 * for generated IDs.
	 * 
	 * @param pkg
	 */
	protected void iterateGeneratedIDs(EPackage pkg) {
		// get all classes from this package
		List<EClassifier> classes = pkg.getEClassifiers(); 
		
		int done = 0;
		for (EClassifier c : classes) {
			// instantiate all non-abstract classes in this package
			if (c instanceof EClass && !((EClass) c).isAbstract()) {
				EClass target = (EClass) c;
				
				// we should know of the factory to create the model element
				EFactory factory = getFactoryMap().get( target.getEPackage() );
				assertNotNull("Couldn't find a factory for package: " + target.getEPackage(), factory);
				EObject obj;
				try {
					obj = factory.create( target );
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("Could not create '" + target + "' in EPackage '" + target.getEPackage() + "' from factory: " + factory, e);
				}
				
				if (obj instanceof GeneratedElement) {
					GeneratedElement ge = (GeneratedElement) obj;
					
					assertNotEmpty("element '" + ge.getClass().getPackage().getName() + "." + ge.getClass().getSimpleName() + "' should have a generated ID.", ge.getId());
					done++;
				}
			}
		}
		
		// special check: ignore Domain package
		if (!pkg.equals(DomainPackage.eINSTANCE)) {
			// make sure we've done at least one
			assertNotEqual("We should have checked generated IDs for at least one class in package '" + pkg, 0, done);
		}
		
		// iterate over sub-packages
		for (EPackage sub : pkg.getESubpackages()) {
			iterateGeneratedIDs(sub);
		}
		
	}

	/**
	 * Assert that the given values are not the same.
	 * @param message
	 * @param a
	 * @param b
	 */
	public void assertNotEqual(String message, int a, int b) {
		if (a == b) {
			fail(message + " [a = " + a + ", b = " + b + "]");
		}
		
	}

	/**
	 * Assert that a given string is not null or empty.
	 * 
	 * @param message Message to display if assertion fails
	 * @param string The string to test
	 */
	protected void assertNotEmpty(String message, String string) {
		assertNotNull(message, string);
		assertFalse(message, string.isEmpty());
	}

	/**
	 * Get all of the EClasses in the system.
	 * 
	 * @return
	 */
	public static Set<EClass> getAllEClasses() {
		Set<EClass> classes = new HashSet<EClass>();
		
		for (EPackage pkg : getFactoryMap().keySet()) {
			for (EClassifier cf : pkg.getEClassifiers()) {
				if (cf instanceof EClass) {
					classes.add((EClass) cf);
				}
			}
		}
		
		return classes;
	}
	
}
