/**
 * 
 */
package org.openiaml.model.tests.inference.model0_3;

import org.openiaml.model.diagram.custom.actions.RefreshObjectInstanceMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.InferenceTestCase;
import org.openiaml.model.tests.inference.InferenceActionTestCase;

/**
 * Tests automatic mapping of NewInstanceWires from DomainObjects
 * to DomainObjectInstances through NewInstanceWires
 * 
 * @author jmwright
 *
 */
public class NewInstanceWireMapping extends InferenceActionTestCase {

	@Override
	protected Class<? extends InferenceTestCase> getTestClass() {
		return NewInstanceWireMapping.class;
	}

	@Override
	public UpdateWithDroolsAction getAction() {
		return new RefreshObjectInstanceMappingsWithDrools();
	}

	@Override
	protected void initialTests() throws Exception {
		
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
	
	@Override
	protected void checkInferredKnowledge() throws Exception {

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
