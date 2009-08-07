/**
 * 
 */
package org.openiaml.model.tests.inference.model0_3;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.jaxen.JaxenException;
import org.openiaml.model.diagram.custom.actions.RefreshDomainStoreMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Tests automatic mapping of DomainStores when connected to
 * Properties files
 * 
 * @author jmwright
 *
 */
public class PropertiesFileMapping extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/**
	 * Make sure the model is loaded properly.
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(PropertiesFileMapping.class);
		
		DomainStore ds = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='my domain store']");
		assertEquals(0, ds.getChildren().size());
		assertEquals(0, ds.getAttributes().size());
		assertEquals(0, ds.getOperations().size());
		assertEquals(0, ds.getEventTriggers().size());
		
	}
	
	/**
	 * Complete model inference.
	 * 
	 * @throws Exception
	 */
	public void testDefaultInference() throws Exception {
		root = loadAndInfer(PropertiesFileMapping.class);
		checkInferredKnowledge(root);
	}
	
	/**
	 * Inference through the custom action.
	 * 
	 * @throws JaxenException
	 */
	public void testActionInference() throws Exception {
		root = loadDirectly(PropertiesFileMapping.class);
		UpdateWithDroolsAction action =
			new RefreshDomainStoreMappingsWithDrools();
		
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

		DomainStore ds = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='my domain store']");
		assertEquals(1, ds.getChildren().size());	// one domain object called 'properties'
		assertEquals(0, ds.getAttributes().size());
		assertEquals(0, ds.getOperations().size());
		assertEquals(0, ds.getEventTriggers().size());
		
		DomainObject dobj = (DomainObject) queryOne(ds, "iaml:children[iaml:name='properties']");
		System.out.println(dobj);
		System.out.println(dobj.getAttributes());
		assertEquals(4, dobj.getAttributes().size());	// four attributes (one is a generated primary key)
		assertEquals(0, dobj.getOperations().size());
		assertEquals(0, dobj.getEventTriggers().size());
		
		{
			DomainAttribute attribute = (DomainAttribute) queryOne(dobj, "iaml:attributes[iaml:name='fruit']");
			assertEquals(attribute.getName(), "fruit");
		}
		{
			DomainAttribute attribute = (DomainAttribute) queryOne(dobj, "iaml:attributes[iaml:name='animal']");
			assertEquals(attribute.getName(), "animal");
		}
		{
			DomainAttribute attribute = (DomainAttribute) queryOne(dobj, "iaml:attributes[iaml:name='empty']");
			assertEquals(attribute.getName(), "empty");
		}
		{
			DomainAttribute attribute = (DomainAttribute) queryOne(dobj, "iaml:attributes[iaml:name='generated primary key']");
			assertEquals(attribute.getName(), "generated primary key");
			assertTrue(attribute.isPrimaryKey());
		}
		
	}
	
}
