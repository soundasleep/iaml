/**
 *
 */
package org.openiaml.model.tests.inference.model0_3;

import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;

/**
 * Tests automatic mapping of SyncWires between InputForms and
 * DomainObjectInstances
 *
 * @author jmwright
 *
 */
public class InputFormInstanceMapping extends EclipseInheritanceInterface {

	@Override
	public Class<? extends EclipseInheritanceInterface> getTestClass() {
		return InputFormInstanceMapping.class;
	}
	
	/**
	 * Test that the correct new knowledge has not yet been added.
	 *
	 * @param root
	 * @throws Exception
	 */
	public void checkNotInferredKnowledge(InternetApplication root) throws Exception {

		DomainStore ds = assertHasDomainStore(root, "a domain store");
		assertEquals(1, ds.getChildren().size());
		assertEquals("User", ds.getChildren().get(0).getName());

		Page page = assertHasPage(root, "container");
		assertEquals("container", page.getName());

		// the form should be empty
		InputForm form = assertHasInputForm(page, "target input form");
		assertEquals(0, form.getChildren().size());

		assertEquals(3, page.getChildren().size());
		DomainObjectInstance obj = assertHasDomainObjectInstance(page, "User instance");

		// the instance should be empty
		assertEquals(0, obj.getAttributes().size());

		// the untargeted form should remain empty
		InputForm ignore = assertHasInputForm(page, "unrelated input form");
		assertEquals(0, ignore.getChildren().size());

	}


	/**
	 * Test that the correct new knowledge has been added.
	 *
	 * @param root
	 * @throws Exception
	 */
	public void checkInferredKnowledge(InternetApplication root) throws Exception {

		DomainStore ds = assertHasDomainStore(root, "a domain store");
		assertEquals(1, ds.getChildren().size());
		assertEquals("User", ds.getChildren().get(0).getName());

		Page page = assertHasPage(root, "container");
		assertEquals("container", page.getName());

		// the instance should NOT be empty
		assertEquals(3, page.getChildren().size());
		DomainObjectInstance obj = assertHasDomainObjectInstance(page, "User instance");
		assertEquals("User instance", obj.getName());

		// two attributes + generated primary key
		assertEquals(3, obj.getAttributes().size());

		// get the domain attribute instances
		DomainAttributeInstance username = assertHasDomainAttributeInstance(obj, "username");
		assertEquals(username.getName(), "username");

		DomainAttributeInstance email = assertHasDomainAttributeInstance(obj, "email");
		assertEquals(email.getName(), "email");

		// the form should NOT be empty
		InputForm form = assertHasInputForm(page, "target input form");
		assertEquals(2, form.getChildren().size());

		{
			InputTextField text = assertHasInputTextField(form, "username");
			assertEquals("username", text.getName());

			// should have a sync wire
			assertHasWiresBidirectional(1, root, text, username);
		}
		{
			InputTextField text = assertHasInputTextField(form, "email");
			assertEquals("email", text.getName());

			// should have a sync wire
			assertHasWiresBidirectional(1, root, text, email);

		}

		// the untargeted form should remain empty
		InputForm ignore = assertHasInputForm(page, "unrelated input form");
		assertEquals(0, ignore.getChildren().size());

	}

}
