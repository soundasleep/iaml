/**
 * 
 */
package org.openiaml.model.tests.drools;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.inference.EcoreCreateElementsHelper;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelFactory;
import org.openiaml.model.tests.XmlTestCase;

/**
 * We want to check the sanity of Drools rules.
 * 
 * If we are going through the iterative process described in our
 * paper (TODO) and have to add all the elements obtained in a queue,
 * we want to make sure that the order of insertion of the queue
 * does not modify the resulting output.
 * 
 * We achieve this by creating two separate rules which, if the
 * queue order does matter will both fire, but if the order is not
 * important neither of the rules will fire.
 * 
 * @author jmwright
 *
 */
public class DroolsQueueTest extends XmlTestCase {
	
	public class DroolsQueueEngine extends DroolsInferenceEngine {

		public DroolsQueueEngine(ICreateElements handler) {
			super(handler, false);
		}

		private List<String> ruleFiles = Arrays.asList(
				"/rules/test-queue.drl"
				);
		
		/**
		 * Get the list of rule files used.
		 * 
		 * @see #addRuleFile(String)
		 * @return
		 */
		@Override
		public List<String> getRuleFiles() {
			return ruleFiles;
		}
		
		/**
		 * We need to extend this to use the testing classloader,
		 * not the drools plugin classloader.
		 */
		@Override
		public InputStream loadResourceAsStream(String filename) {
			return DroolsQueueEngine.class.getResourceAsStream( filename );
		}

		@Override
		public void create(EObject model, boolean logRuleSource,
				IProgressMonitor monitor) throws InferenceException {
			// use the correct cache
			setRuleBaseCache(new RuleBaseCache());
			super.create(model, logRuleSource, monitor);
			// future references should not use our cached values either
			resetRuleBaseCache();
		}
		
	}
	
	/**
	 * Test that the order doesn't matter.
	 * 
	 * @throws Exception
	 */
	public void testDroolsQueue() throws Exception {
		// obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		Resource resource = resSet.createResource(URI.createURI("drools-queue.iaml"));
		
		// create the model
		InternetApplication root = ModelFactory.eINSTANCE.createInternetApplication();
		assertNotNull(root);
		assertNotEqual(root.getName(), "created successfully");
		
		// initially empty
		assertEquals(0, root.getDomainStores().size());
		assertEquals(0, root.getScopes().size());

		// infer new elements
		EcoreCreateElementsHelper helper = new EcoreInferenceHandler(resource);
		DroolsQueueEngine engine = new DroolsQueueEngine(helper);
		
		engine.create(root, new NullProgressMonitor());
		
		// check that it did actually create stuff
		assertEquals(1, root.getDomainStores().size());
		assertEquals(1, root.getScopes().size());
		
		// it should also fire new rules in the rule file
		assertEquals(root.getName(), "created successfully");
		
	}

	/**
	 * Test to make sure elements are being created at the appropriate
	 * iterations.
	 * 
	 * @throws Exception
	 */
	public void testDroolsQueueCount() throws Exception {
		// obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		Resource resource = resSet.createResource(URI.createURI("drools-queue.iaml"));
		
		// create the model
		InternetApplication root = ModelFactory.eINSTANCE.createInternetApplication();
		assertNotNull(root);

		// infer new elements
		EcoreCreateElementsHelper helper = new EcoreInferenceHandler(resource);
		DroolsQueueEngine engine = new DroolsQueueEngine(helper);
		
		engine.create(root, new NullProgressMonitor());

		Map<Integer, Integer> queue = engine.getQueueElementsAdded();
		assertNotNull(queue);
		
		assertEquals((int) queue.get(0), 2);
		for (int i = 1; i < DroolsQueueEngine.INSERTION_ITERATION_LIMIT; i++) {
			assertEquals((int) queue.get(i), 0);
		}
		for (int i = 0; i < 10; i++) {
			assertNull(queue.get(DroolsQueueEngine.INSERTION_ITERATION_LIMIT + i));
		}
		assertNull(queue.get(-1));
		
	}
	
}
