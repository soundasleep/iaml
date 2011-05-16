/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.visual.Frame;
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

		Frame page = assertHasFrame(root, "container");
		DomainIterator di = assertHasDomainIterator(page, "domain object instance");

		// page is otherwise empty
		assertEquals(0, page.getChildren().size());
		assertEquals(1, page.getIterators().size());
		assertEquals(0, page.getLoginHandlers().size());
		assertEquals(0, page.getAccessHandlers().size());

		// instance is empty
		assertNull(di.getCurrentInstance());
		assertEquals(0, di.getOperations().size());

	}

	@Override
	public void checkInferredKnowledge(InternetApplication root) throws Exception {

		Frame page = assertHasFrame(root, "container");
		DomainIterator di = assertHasDomainIterator(page, "domain object instance");

		// page is otherwise empty
		assertEquals(0, page.getChildren().size());
		assertEquals(1, page.getIterators().size());
		assertEquals(0, page.getLoginHandlers().size());
		assertEquals(0, page.getAccessHandlers().size());

		// instance now contains a 'save' method
		assertEquals(1, di.getOperations().size());
		assertContainsNamedElement(di.getOperations(), "save", true);

		// and lots of conditions
		assertContainsNamedElement(di.getFunctions(), "results count is set", true);
		assertContainsNamedElement(di.getFunctions(), "not empty", true);

		// since DomainIterator.fieldValue makes absolutely no sense, there
		// should not be the fieldValue and associated condition
		assertHasNoFunction(di, "fieldValue is set");
		assertHasNoFieldValue(di);

		// there should be a Value 'results count'
		Value prop = di.getResults();
		assertGenerated(prop);
		assertTrue(prop.isReadOnly());

		// it's not stored in properties
		assertHasNoValue(di, "results count");

		// because the Value is read-only, no 'set results count' Operation is created
		assertHasNoOperation(di, "set results count");

	}

}
