/**
 * 
 */
package org.openiaml.emf.properties.tests;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.xsd.XSDPackage;
import org.openiaml.docs.modeldoc.ModeldocPackage;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IPropertyInvestigator;
import org.openiaml.emf.properties.library.metamodel.AllMetamodelPropertiesLibrary;
import org.openiaml.emf.properties.library.metamodel.MMClassesCount;
import org.openiaml.model.model.ModelPackage;

/**
 * Test the metamodel metrics against those defined by 
 * Monperrus et al.
 * 
 * <p>However, the version of ECore that they use is <em>not</em> the
 * ECore that is actually present in EMF.
 * 
 * @author jmwright
 *
 */
public class TestEMFMetamodelProperties extends TestCase implements IEMFElementSelector {

	// we can't test anything
	
	private void investigatePackage(EPackage pkg) {
		System.out.println("[" + pkg.getName() + "]");
		
		for (IPropertyInvestigator m : AllMetamodelPropertiesLibrary.getAllMetamodelProperties(this)) {
			System.out.println(m.getName() + " = " + m.evaluate(pkg));
		}
	}
	
	/**
	 * Calculates the new values for the various metamodel properties.
	 */
	public void testGetActualValues() {
		investigatePackage(EcorePackage.eINSTANCE);
	}
	
	/**
	 * Calculates the values for IAML.
	 */
	public void testGetIAMLValues() {
		investigatePackage(ModelPackage.eINSTANCE);
	}
	
	/**
	 * Calculates the values for XSD.
	 */
	public void testGetXSDValues() {
		investigatePackage(XSDPackage.eINSTANCE);
	}
	
	/**
	 * Calculates the values for OCL.
	 */
	public void testGetOCLValues() {
		investigatePackage(org.eclipse.ocl.ecore.EcorePackage.eINSTANCE);
	}
	
	/**
	 * Calculates the values for UML.
	 */
	public void testGetUMLValues() {
		investigatePackage(UMLPackage.eINSTANCE);
	}
	
	/**
	 * Calculates the values for Modeldoc.
	 */
	public void testGetModeldocValues() {
		investigatePackage(ModeldocPackage.eINSTANCE);
	}
	
	/**
	 * Calculates the values for GMF Runtime Notation.
	 */
	public void testGetGMFRuntimeNotationValues() {
		investigatePackage(NotationPackage.eINSTANCE);
	}
	
	public void disabled_testNoC() {
		
		assertEquals(
				18,
				new MMClassesCount(this).evaluate( EcorePackage.eINSTANCE )
		);
		
	}

	@Override
	public boolean ignoreAttribute(EAttribute ref) {
		return false;
	}

	@Override
	public boolean ignoreClass(EClass ref) {
		return false;
	}

	@Override
	public boolean ignoreReference(EReference ref) {
		return false;
	}
	
}
