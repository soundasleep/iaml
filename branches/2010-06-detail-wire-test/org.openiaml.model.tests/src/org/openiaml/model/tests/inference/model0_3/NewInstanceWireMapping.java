/**
 *
 */
package org.openiaml.model.tests.inference.model0_3;

import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.domain.DomainSource;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;

/**
 * Tests automatic mapping of NewInstanceWires from DomainObjects
 * to DomainObjectInstances through NewInstanceWires
 *
 * @author jmwright
 *
 */
public class NewInstanceWireMapping extends EclipseInheritanceInterface {
	
	@Override
	public Class<? extends EclipseInheritanceInterface> getTestClass() {
		return NewInstanceWireMapping.class;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.inference.EclipseInheritanceInterface#checkNotInferredKnowledge(org.openiaml.model.model.InternetApplication)
	 */
	@Override
	public void checkNotInferredKnowledge(InternetApplication root)
			throws Exception {

		DomainSource ds = assertHasDomainSource(root, "domain source");
		assertNotGenerated(ds);
		DomainSchema user = assertHasDomainSchema(root, "User");
		assertNotGenerated(user);

		Frame page = assertHasFrame(root, "container");
		assertEquals("container", page.getName());

		assertEquals(0, page.getChildren().size());
		assertEquals(1, page.getElements().size());
		DomainIterator obj = assertHasDomainIterator(page, "User instance");

		// the instance should be empty
		assertEquals(0, obj.getAttributes().size());
		
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.inference.EclipseInheritanceInterface#checkInferredKnowledge(org.openiaml.model.model.InternetApplication)
	 */
	@Override
	public void checkInferredKnowledge(InternetApplication root)
			throws Exception {
		
		DomainSource ds = assertHasDomainSource(root, "domain source");
		assertNotGenerated(ds);
		DomainSchema user = assertHasDomainSchema(root, "User");
		assertNotGenerated(user);

		Frame page = assertHasFrame(root, "container");
		assertEquals("container", page.getName());

		assertEquals(0, page.getChildren().size());
		assertEquals(1, page.getElements().size());
		DomainIterator obj = assertHasDomainIterator(page, "User instance");

		// the instance should NOT be empty
		// but contain three attributes (two plus a generated key)
		assertEquals(3, obj.getAttributes().size());

		{
			DomainAttributeInstance attr = assertHasDomainAttributeInstance(obj, "username");
			assertEquals(attr.getName(), "username");
		}
		{
			DomainAttributeInstance attr = assertHasDomainAttributeInstance(obj, "email");
			assertEquals(attr.getName(), "email");
		}
		{
			DomainAttributeInstance attr = assertHasDomainAttributeInstance(obj, "generated primary key");
			assertEquals(attr.getName(), "generated primary key");
		}

	}

}
