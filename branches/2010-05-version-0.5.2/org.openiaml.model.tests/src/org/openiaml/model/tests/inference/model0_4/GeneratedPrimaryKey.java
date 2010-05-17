/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.domain.DomainSchema;
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
		DomainSchema ds = assertHasDomainSchema(root, "domain object");
		assertEquals(0, ds.getAttributes().size());

	}

	@Override
	public void checkInferredKnowledge(InternetApplication root) throws Exception {

		DomainSchema ds = assertHasDomainSchema(root, "domain object");
		assertEquals(1, ds.getAttributes().size());
		DomainAttribute generated = ds.getAttributes().get(0);
		assertEquals("generated primary key", generated.getName());
		assertGenerated(generated);
		assertTrue(generated.isPrimaryKey());

	}

}
