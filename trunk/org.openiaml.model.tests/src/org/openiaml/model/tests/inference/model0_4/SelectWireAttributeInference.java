/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.model.diagram.custom.actions.RefreshFormMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.RefreshObjectInstanceMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.inference.InferenceActionTestCase;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Issue 68: SelectWire does not synchronise Attributes between Object and Instance
 *
 * @author jmwright
 *
 */
public class SelectWireAttributeInference extends InferenceActionTestCase {

	@Override
	protected Class<? extends InferenceTestCase> getTestClass() {
		return SelectWireAttributeInference.class;
	}

	@Override
	public List<UpdateWithDroolsAction> getActionList() {
		List<UpdateWithDroolsAction> result = new ArrayList<UpdateWithDroolsAction>();
		result.add(new RefreshObjectInstanceMappingsWithDrools());
		result.add(new RefreshFormMappingsWithDrools());
		return result;
	}

	@Override
	protected void initialTests() throws Exception {

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
	protected void checkInferredKnowledge() throws Exception {

		Page page = assertHasPage(root, "container");
		DomainObject dobj = assertHasDomainObject(page, "a domain object");
		DomainObjectInstance di = assertHasDomainObjectInstance(page, "instance");
		InputForm form = assertHasInputForm(page, "form");

		// the domain object now has two attributes (one generated key)
		assertEquals(2, dobj.getAttributes().size());
		queryOne(dobj, "iaml:attributes[iaml:name='attribute']");
		DomainAttribute key = assertHasDomainAttribute(dobj, "generated primary key");
		assertTrue(key.isPrimaryKey());
		assertTrue(key.isIsGenerated());

		// the instance has both of these values
		assertEquals(2, di.getAttributes().size());
		queryOne(di, "iaml:attributes[iaml:name='attribute']");
		queryOne(di, "iaml:attributes[iaml:name='generated primary key']");

		// the form only has 'attribute'
		assertEquals(1, form.getChildren().size());
		queryOne(form, "iaml:children[iaml:name='attribute']");

	}

	/**
	 * Should not be used. Throws an exception.
	 *
	 * @deprecated
	 * @see org.openiaml.model.tests.inference.InferenceActionTestCase#getAction()
	 */
	@Override
	public UpdateWithDroolsAction getAction() {
		throw new UnsupportedOperationException("getAction() is not supported.");
	}

}
