/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.model.Value;
import org.openiaml.model.model.messaging.Email;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 *
 * @author jmwright
 */
public class EmailExplicit extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(EmailExplicit.class);
	}

	/**
	 * The properties of e-mail should be copied over.
	 *
	 * @throws Exception
	 */
	public void testPropertiesCopiedOver() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		Email email = assertHasEmail(home, "Email");

		Value to = assertHasValue(email, "to");
		assertGenerated(to);
		assertEquals(to.getDefaultValue(), email.getTo());

		Value toName = assertHasValue(email, "toName");
		assertGenerated(toName);
		assertEquals(toName.getDefaultValue(), email.getToName());

		Value from = assertHasValue(email, "from");
		assertGenerated(from);
		assertEquals(from.getDefaultValue(), email.getFrom());

		Value fromName = assertHasValue(email, "fromName");
		assertGenerated(fromName);
		assertEquals(fromName.getDefaultValue(), email.getFromName());

		Value subject = assertHasValue(email, "subject");
		assertGenerated(subject);
		assertEquals(subject.getDefaultValue(), email.getSubject());

		// the 'content' static value was already there
		Value content = assertHasValue(email, "content");
		assertTrue(content.isReadOnly());
		assertNotGenerated(content);

	}


}
