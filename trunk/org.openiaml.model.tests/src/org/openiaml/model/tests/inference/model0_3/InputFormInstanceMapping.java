/**
 * 
 */
package org.openiaml.model.tests.inference.model0_3;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.jaxen.JaxenException;
import org.openiaml.model.diagram.custom.actions.RefreshFormMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.RefreshObjectInstanceMappingsWithDrools;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Tests automatic mapping of SyncWires between InputForms and
 * DomainObjectInstances
 * 
 * @author jmwright
 *
 */
public class InputFormInstanceMapping extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/**
	 * Make sure the model is loaded properly.
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(InputFormInstanceMapping.class);
		checkNotInferredKnowledge(root);
	}
	
	/**
	 * Complete model inference.
	 * 
	 * @throws Exception
	 */
	public void testDefaultInference() throws Exception {
		root = loadAndInfer(InputFormInstanceMapping.class, true);
		checkInferredKnowledge(root);
	}
	
	/**
	 * Inference through only the custom action. Doesn't do
	 * anything, because SyncWires are separate to the
	 * DomainObjectInstance mapping.
	 * 
	 * @throws JaxenException
	 */
	public void testActionInference() throws Exception {
		root = loadDirectly(InputFormInstanceMapping.class);
		RefreshFormMappingsWithDrools action =
			new RefreshFormMappingsWithDrools();
		
		action.refreshMappings(root, createHandler(), new NullProgressMonitor());
		
		checkNotInferredKnowledge(root);
	}

	/**
	 * Inference through only the custom action. This should
	 * fully comply with the entire model.
	 * 
	 * @throws JaxenException
	 */
	public void testBothActionsInference() throws Exception {
		root = loadDirectly(InputFormInstanceMapping.class);
		{
			RefreshObjectInstanceMappingsWithDrools action =
				new RefreshObjectInstanceMappingsWithDrools();
			
			action.refreshMappings(root, createHandler(), new NullProgressMonitor());
		}
		{
			RefreshFormMappingsWithDrools action =
				new RefreshFormMappingsWithDrools();
			
			action.refreshMappings(root, createHandler(), new NullProgressMonitor());
		}

		// should now all be there
		checkInferredKnowledge(root);
	}

	/**
	 * Test that the correct new knowledge has not yet been added.
	 * 
	 * @param root
	 * @throws Exception
	 */
	protected void checkNotInferredKnowledge(InternetApplication root) throws Exception {

		DomainStore ds = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='a domain store']");
		assertEquals(1, ds.getChildren().size());
		assertEquals("User", ds.getChildren().get(0).getName());

		Page page = (Page) queryOne(root, "iaml:children[iaml:name='container']");
		assertEquals("container", page.getName());
		
		// the form should be empty
		InputForm form = (InputForm) queryOne(page, "iaml:children[iaml:name='target input form']");
		assertEquals(0, form.getChildren().size());
		
		assertEquals(3, page.getChildren().size());
		DomainObjectInstance obj = (DomainObjectInstance) queryOne(page, "iaml:children[iaml:name='User instance']");
		
		// the instance should be empty
		assertEquals(0, obj.getAttributes().size());
		
		// the untargeted form should remain empty
		InputForm ignore = (InputForm) queryOne(page, "iaml:children[iaml:name='unrelated input form']");
		assertEquals(0, ignore.getChildren().size());

	}


	/**
	 * Test that the correct new knowledge has been added.
	 * 
	 * @param root
	 * @throws Exception
	 */
	protected void checkInferredKnowledge(InternetApplication root) throws Exception {

		DomainStore ds = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='a domain store']");
		assertEquals(1, ds.getChildren().size());
		assertEquals("User", ds.getChildren().get(0).getName());

		Page page = (Page) queryOne(root, "iaml:children[iaml:name='container']");
		assertEquals("container", page.getName());
		
		// the instance should NOT be empty
		assertEquals(3, page.getChildren().size());
		DomainObjectInstance obj = (DomainObjectInstance) queryOne(page, "iaml:children[iaml:name='User instance']");
		assertEquals("User instance", obj.getName());
		assertEquals(2, obj.getAttributes().size());
		
		// get the domain attribute instances
		DomainAttributeInstance username = (DomainAttributeInstance) queryOne(obj, "iaml:attributes[iaml:name='username']");
		assertEquals(username.getName(), "username");

		DomainAttributeInstance email = (DomainAttributeInstance) queryOne(obj, "iaml:attributes[iaml:name='email']");
		assertEquals(email.getName(), "email");
		
		// the form should NOT be empty
		InputForm form = (InputForm) queryOne(page, "iaml:children[iaml:name='target input form']");
		assertEquals(2, form.getChildren().size());
		
		{
			InputTextField text = (InputTextField) queryOne(form, "iaml:children[iaml:name='username']");
			assertEquals("username", text.getName());
			
			// should have a sync wire
			assertHasWiresBidirectional(1, root, text, username);
		}
		{
			InputTextField text = (InputTextField) queryOne(form, "iaml:children[iaml:name='email']");
			assertEquals("email", text.getName());
			
			// should have a sync wire
			assertHasWiresBidirectional(1, root, text, email);

		}
		
		// the untargeted form should remain empty
		InputForm ignore = (InputForm) queryOne(page, "iaml:children[iaml:name='unrelated input form']");
		assertEquals(0, ignore.getChildren().size());

	}
	/**
	 * Assert that the given object is of the given class or higher.
	 * 
	 * @param class1
	 * @param object
	 */
	protected void assertInstanceOf(Class<?> class1, Object object) {
		if (class1.isInstance(object)) {
			// ok
		} else {
			fail("Expected object instance '" + class1 + "', got '" + object.getClass() + "': " + object);
		}	
	}

}
