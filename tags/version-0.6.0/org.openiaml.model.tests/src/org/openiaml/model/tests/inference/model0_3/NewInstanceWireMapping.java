/**
 *
 */
package org.openiaml.model.tests.inference.model0_3;

import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.domain.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSource;
import org.openiaml.model.model.domain.DomainType;
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
		DomainType user = assertHasDomainType(root, "User");
		assertNotGenerated(user);

		Frame page = assertHasFrame(root, "container");
		assertEquals("container", page.getName());

		assertEquals(0, page.getChildren().size());
		assertEquals(1, page.getIterators().size());
		assertEquals(0, page.getLoginHandlers().size());
		assertEquals(0, page.getAccessHandlers().size());
		DomainIterator obj = assertHasDomainIterator(page, "User instance");

		// the instance should be empty
		assertNull(obj.getCurrentInstance());
		
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.inference.EclipseInheritanceInterface#checkInferredKnowledge(org.openiaml.model.model.InternetApplication)
	 */
	@Override
	public void checkInferredKnowledge(InternetApplication root)
			throws Exception {
		
		DomainSource ds = assertHasDomainSource(root, "domain source");
		assertNotGenerated(ds);
		DomainType user = assertHasDomainType(root, "User");
		assertNotGenerated(user);

		Frame page = assertHasFrame(root, "container");
		assertEquals("container", page.getName());

		assertEquals(0, page.getChildren().size());
		assertEquals(1, page.getIterators().size());
		assertEquals(0, page.getLoginHandlers().size());
		assertEquals(0, page.getAccessHandlers().size());
		DomainIterator obj = assertHasDomainIterator(page, "User instance");

		// issue 241: there should now be a DomainInstance
		DomainInstance instance = obj.getCurrentInstance();
		assertGenerated(instance);
		
		// two attributes + generated primary key
		assertEquals(3, typeSelect(instance.getFeatureInstances(), DomainAttributeInstance.class).size());

		{
			DomainAttributeInstance attr = assertHasDomainAttributeInstance(instance, "username");
			assertEquals(attr.getName(), "username");
		}
		{
			DomainAttributeInstance attr = assertHasDomainAttributeInstance(instance, "email");
			assertEquals(attr.getName(), "email");
		}
		{
			DomainAttributeInstance attr = assertHasDomainAttributeInstance(instance, "generated primary key");
			assertEquals(attr.getName(), "generated primary key");
		}

	}

}
