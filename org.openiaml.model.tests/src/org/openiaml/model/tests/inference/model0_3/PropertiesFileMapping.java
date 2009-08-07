/**
 * 
 */
package org.openiaml.model.tests.inference.model0_3;

import org.openiaml.model.diagram.custom.actions.RefreshDomainStoreMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.tests.InferenceTestCase;
import org.openiaml.model.tests.inference.InferenceActionTestCase;

/**
 * Tests automatic mapping of DomainStores when connected to
 * Properties files
 * 
 * @author jmwright
 *
 */
public class PropertiesFileMapping extends InferenceActionTestCase {

	@Override
	protected Class<? extends InferenceTestCase> getTestClass() {
		return PropertiesFileMapping.class;
	}

	@Override
	public UpdateWithDroolsAction getAction() {
		return new RefreshDomainStoreMappingsWithDrools();
	}

	@Override
	protected void initialTests() throws Exception {
		
		DomainStore ds = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='my domain store']");
		assertEquals(0, ds.getChildren().size());
		assertEquals(0, ds.getAttributes().size());
		assertEquals(0, ds.getOperations().size());
		assertEquals(0, ds.getEventTriggers().size());
				
	}
	
	@Override
	protected void checkInferredKnowledge() throws Exception {

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
