/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;

/**
 *  Issue 62: Inference on non-autosave DomainObjectInstance does not create 'save' operation
 *
 * @author jmwright
 *
 */
public class DomainInstanceSaveOperation extends EclipseInheritanceInterface {

	@Override
	public Class<? extends EclipseInheritanceInterface> getTestClass() {
		return DomainInstanceSaveOperation.class;
	}

	@Override
	public void checkNotInferredKnowledge(InternetApplication root) throws Exception {

		Page page = assertHasPage(root, "container");
		DomainObjectInstance di = assertHasDomainObjectInstance(page, "domain object instance");

		// page is otherwise empty
		assertEquals(1, page.getChildren().size());

		// instance is empty
		assertEquals(0, di.getAttributes().size());
		assertEquals(0, di.getOperations().size());
		
	}

	@Override
	public void checkInferredKnowledge(InternetApplication root) throws Exception {

		Page page = assertHasPage(root, "container");
		DomainObjectInstance di = assertHasDomainObjectInstance(page, "domain object instance");

		// page is otherwise empty
		assertEquals(1, page.getChildren().size());

		// instance now contains a 'save' method and 'exists?' method
		assertEquals(2, di.getOperations().size());

		assertContainsNamedElement(di.getOperations(), "save", true);
		assertContainsNamedElement(di.getOperations(), "exists?", true);

	}

}
