/**
 * 
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.diagram.custom.actions.RefreshDomainStoreMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.tests.InferenceTestCase;
import org.openiaml.model.tests.inference.InferenceActionTestCase;

/**
 * DomainObjects that do not define a DomainAttribute with a primary
 * key should get one generated automatically.
 * 
 * @author jmwright
 *
 */
public class GeneratedPrimaryKey extends InferenceActionTestCase {

	@Override
	protected Class<? extends InferenceTestCase> getTestClass() {
		return GeneratedPrimaryKey.class;
	}

	@Override
	public UpdateWithDroolsAction getAction() {
		return new RefreshDomainStoreMappingsWithDrools();
	}

	@Override
	protected void initialTests() throws Exception {
		
		DomainObject ds = (DomainObject) queryOne(root, "iaml:children[iaml:name='domain object']");
		assertEquals(0, ds.getAttributes().size());
		assertEquals(0, ds.getOperations().size());
		assertEquals(0, ds.getEventTriggers().size());
		
	}
	
	@Override
	protected void checkInferredKnowledge() throws Exception {

		DomainObject ds = (DomainObject) queryOne(root, "iaml:children[iaml:name='domain object']");
		assertEquals(1, ds.getAttributes().size());
		DomainAttribute generated = ds.getAttributes().get(0);
		assertEquals("generated primary key", generated.getName());
		assertTrue(generated.isIsGenerated());
		assertTrue(generated.isPrimaryKey());
		assertEquals(0, ds.getOperations().size());
		assertEquals(0, ds.getEventTriggers().size());
		
	}

	
}
