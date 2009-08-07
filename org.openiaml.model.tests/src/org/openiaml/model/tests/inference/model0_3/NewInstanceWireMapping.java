/**
 * 
 */
package org.openiaml.model.tests.inference.model0_3;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.jaxen.JaxenException;
import org.openiaml.model.diagram.custom.actions.RefreshObjectInstanceMappingsWithDrools;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Tests automatic mapping of NewInstanceWires from DomainObjects
 * to DomainObjectInstances through NewInstanceWires
 * 
 * @author jmwright
 *
 */
public class NewInstanceWireMapping extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/**
	 * Make sure the model is loaded properly.
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(NewInstanceWireMapping.class);
		
		DomainStore ds = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='a domain store']");
		assertEquals(1, ds.getChildren().size());
		assertEquals("User", ds.getChildren().get(0).getName());

		Page page = (Page) queryOne(root, "iaml:children[iaml:name='container']");
		assertEquals("container", page.getName());
		
		assertEquals(1, page.getChildren().size());
		DomainObjectInstance obj = (DomainObjectInstance) page.getChildren().get(0);
		assertEquals("User instance", obj.getName());
		
		// the instance should be empty
		assertEquals(0, obj.getAttributes().size());
		
	}
	
	/**
	 * Complete model inference.
	 * 
	 * @throws Exception
	 */
	public void testDefaultInference() throws Exception {
		root = loadAndInfer(NewInstanceWireMapping.class);
		checkInferredKnowledge(root);
	}
	
	/**
	 * Inference through the custom action.
	 * 
	 * @throws JaxenException
	 */
	public void testActionInference() throws Exception {
		root = loadDirectly(NewInstanceWireMapping.class);
		RefreshObjectInstanceMappingsWithDrools action =
			new RefreshObjectInstanceMappingsWithDrools();
		
		action.refreshMappings(root, createHandler(), new NullProgressMonitor());
		
		checkInferredKnowledge(root);
	}

	/**
	 * Test that the correct new knowledge has been added.
	 * 
	 * @param root
	 * @throws Exception
	 */
	protected void checkInferredKnowledge(InternetApplication root) throws Exception {

		DomainStore ds = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='a domain store']");
		assertEquals(1, ds.getChildren().size());
		assertEquals("User", ds.getChildren().get(0).getName());

		Page page = (Page) queryOne(root, "iaml:children[iaml:name='container']");
		assertEquals("container", page.getName());
		
		assertEquals(1, page.getChildren().size());
		DomainObjectInstance obj = (DomainObjectInstance) page.getChildren().get(0);
		assertEquals("User instance", obj.getName());
		
		// the instance should NOT be empty
		// but contain three attributes (two plus a generated key)
		assertEquals(3, obj.getAttributes().size());
		
		{
			DomainAttributeInstance attr = (DomainAttributeInstance) queryOne(obj, "iaml:attributes[iaml:name='username']");
			assertEquals(attr.getName(), "username");
		}
		{
			DomainAttributeInstance attr = (DomainAttributeInstance) queryOne(obj, "iaml:attributes[iaml:name='email']");
			assertEquals(attr.getName(), "email");
		}
		{
			DomainAttributeInstance attr = (DomainAttributeInstance) queryOne(obj, "iaml:attributes[iaml:name='generated primary key']");
			assertEquals(attr.getName(), "generated primary key");
		}
		
	}
	
}
