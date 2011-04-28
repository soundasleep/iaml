/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.domain.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
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

		Frame page = assertHasFrame(root, "container");
		DomainSchema dobj = assertHasDomainSchema(root, "a domain object");
		DomainIterator di = assertHasDomainIterator(page, "select");
		InputForm form = assertHasInputForm(page, "form");

		// the domain object has an attribute
		assertEquals(1, dobj.getAttributes().size());
		assertHasDomainAttribute(dobj, "attribute");

		// the iterator does not yet contain an instance
		assertNull(di.getCurrentInstance());

		// or the form
		assertEquals(0, form.getChildren().size());

	}

	@Override
	public void checkInferredKnowledge(InternetApplication root) throws Exception {

		Frame page = assertHasFrame(root, "container");
		DomainSchema dobj = assertHasDomainSchema(root, "a domain object");
		DomainIterator di = assertHasDomainIterator(page, "select");
		InputForm form = assertHasInputForm(page, "form");

		// the domain object now has two attributes (one generated key)
		assertEquals(2, dobj.getAttributes().size());
		assertHasDomainAttribute(dobj, "attribute");
		DomainAttribute key = assertHasDomainAttribute(dobj, "generated primary key");
		assertTrue(key.isPrimaryKey());
		assertTrue(key.isIsGenerated());

		// an instance is created
		DomainInstance instance = di.getCurrentInstance();
		assertGenerated(instance);
		
		// the instance has both of these values
		assertEquals(2, typeSelect(instance.getFeatureInstances(), DomainAttributeInstance.class).size());
		assertHasDomainAttributeInstance(instance, "attribute");
		assertHasDomainAttributeInstance(instance, "generated primary key");

		// the form only has 'attribute'
		assertEquals(1, form.getChildren().size());
		assertHasInputTextField(form, "attribute");
		
	}

}
