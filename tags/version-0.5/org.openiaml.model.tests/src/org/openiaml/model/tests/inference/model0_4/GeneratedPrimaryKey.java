/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;

/**
 * DomainObjects that do not define a DomainAttribute with a primary
 * key should get one generated automatically.
 *
 * @author jmwright
 *
 */
public class GeneratedPrimaryKey extends EclipseInheritanceInterface {

	@Override
	public Class<? extends EclipseInheritanceInterface> getTestClass() {
		return GeneratedPrimaryKey.class;
	}

	@Override
	public void checkNotInferredKnowledge(InternetApplication root) throws Exception {
		DomainObject ds = assertHasDomainObject(root, "domain object");
		assertEquals(0, ds.getAttributes().size());
		assertEquals(0, ds.getOperations().size());
		assertNull(ds.getOnChange());

	}

	@Override
	public void checkInferredKnowledge(InternetApplication root) throws Exception {

		DomainObject ds = assertHasDomainObject(root, "domain object");
		assertEquals(1, ds.getAttributes().size());
		DomainAttribute generated = ds.getAttributes().get(0);
		assertEquals("generated primary key", generated.getName());
		assertTrue(generated.isIsGenerated());
		assertTrue(generated.isPrimaryKey());
		assertEquals(0, ds.getOperations().size());
		assertNotNull(ds.getOnChange());

	}

}
