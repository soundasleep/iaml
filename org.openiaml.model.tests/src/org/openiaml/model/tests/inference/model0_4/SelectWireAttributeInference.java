/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;

/**
 * Issue 68: SelectWire does not synchronise Attributes between Object and Instance
 *
 * @author jmwright
 *
 */
public class SelectWireAttributeInference extends EclipseInheritanceInterface {

	@Override
	public Class<? extends EclipseInheritanceInterface> getTestClass() {
		return SelectWireAttributeInference.class;
	}

	@Override
	public void checkNotInferredKnowledge(InternetApplication root) throws Exception {

		Page page = assertHasPage(root, "container");
		DomainObject dobj = assertHasDomainObject(page, "a domain object");
		DomainObjectInstance di = assertHasDomainObjectInstance(page, "instance");
		InputForm form = assertHasInputForm(page, "form");

		// the domain object has an attribute
		assertEquals(1, dobj.getAttributes().size());

		// the instance does not have it yet
		assertEquals(0, di.getAttributes().size());

		// or the form
		assertEquals(0, form.getChildren().size());

	}

	@Override
	public void checkInferredKnowledge(InternetApplication root) throws Exception {

		Page page = assertHasPage(root, "container");
		DomainObject dobj = assertHasDomainObject(page, "a domain object");
		DomainObjectInstance di = assertHasDomainObjectInstance(page, "instance");
		InputForm form = assertHasInputForm(page, "form");

		// the domain object now has two attributes (one generated key)
		assertEquals(2, dobj.getAttributes().size());
		assertHasDomainAttribute(dobj, "attribute");
		DomainAttribute key = assertHasDomainAttribute(dobj, "generated primary key");
		assertTrue(key.isPrimaryKey());
		assertTrue(key.isIsGenerated());

		// the instance has both of these values
		assertEquals(2, di.getAttributes().size());
		assertHasDomainAttributeInstance(di, "attribute");
		assertHasDomainAttributeInstance(di, "generated primary key");

		// the form only has 'attribute'
		assertEquals(1, form.getChildren().size());
		assertHasInputTextField(form, "attribute");
		
	}

}
