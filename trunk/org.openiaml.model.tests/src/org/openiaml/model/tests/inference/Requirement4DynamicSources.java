/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.util.Set;

import junit.framework.AssertionFailedError;

import org.eclipse.emf.ecore.EObject;
import org.jaxen.JaxenException;
import org.openiaml.model.codegen.oaw.OawCodeGenerator;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * A simple test, testing OawCodeGenerator#resolveDynamicSet, and the
 * query given in the requirements model. This test makes sure the
 * XPath resolver is working correctly with the given model.
 * 
 * @author jmwright
 *
 */
public class Requirement4DynamicSources extends InferenceTestCase {

	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndInfer(ROOT + "../examples/requirements/4-dynamic_sources.iaml");
	}
	
	public void testXPathQuery() throws JaxenException {
		// there should be a dynamic set
		DynamicApplicationElementSet set = (DynamicApplicationElementSet) queryOne(root, "iaml:children[iaml:name='all pages']");
		
		// see what it resolves to
		Set<EObject> results = OawCodeGenerator.resolveDynamicSet(root, set);
		
		// there should be three results
		try {
			assertEquals(3, results.size());
		} catch (AssertionFailedError e) {
			// lets print out the contents
			for (EObject o : results) {
				System.err.println(o);
			}
			throw e;
		}
	}

}
