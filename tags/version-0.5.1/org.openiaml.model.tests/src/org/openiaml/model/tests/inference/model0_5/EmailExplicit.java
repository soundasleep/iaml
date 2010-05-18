/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.model.Property;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.scopes.Email;
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
		
		Property to = assertHasProperty(email, "to");
		assertGenerated(to);
		assertEquals(to.getDefaultValue(), email.getTo());

		Property toName = assertHasProperty(email, "toName");
		assertGenerated(toName);
		assertEquals(toName.getDefaultValue(), email.getToName());

		Property from = assertHasProperty(email, "from");
		assertGenerated(from);
		assertEquals(from.getDefaultValue(), email.getFrom());

		Property fromName = assertHasProperty(email, "fromName");
		assertGenerated(fromName);
		assertEquals(fromName.getDefaultValue(), email.getFromName());

		Property subject = assertHasProperty(email, "subject");
		assertGenerated(subject);
		assertEquals(subject.getDefaultValue(), email.getSubject());

		// the 'content' static value was already there
		StaticValue content = assertHasStaticValue(email, "content");
		assertNotGenerated(content);
		
	}
	

}
