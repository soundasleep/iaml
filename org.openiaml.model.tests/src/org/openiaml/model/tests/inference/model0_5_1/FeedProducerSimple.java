/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_1;

import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.QueryParameter;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.wires.NavigateAction;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * We should be creating a lot of new elements in here.
 *
 * @author jmwright
 */
public class FeedProducerSimple extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(FeedProducerSimple.class);
	}
	
	/**
	 * The 'View News' page needs to require a 'generated primary key'
	 * parameter.
	 * 
	 * @throws Exception
	 */
	public void testViewNewsPage() throws Exception {
		
		DomainStore store = assertHasDomainStore(root, "Database");
		DomainObject news = assertHasDomainObject(store, "News");
		
		Frame view = assertHasFrame(root, "View News");
		assertNotGenerated(view);
		
		InputForm viewForm = assertHasInputForm(view, "View News");
		assertGenerated(viewForm);
		
		QueryParameter qp = assertHasQueryParameter(view, "generated primary key");
		assertGenerated(qp);
		
		DomainObjectInstance instance = assertHasDomainObjectInstance(view, "Current News instance");
		assertGenerated(instance);
		
		SelectWire select = assertHasSelectWire(root, news, instance);
		assertGenerated(select);
		assertEquals(1, select.getLimit());	// selecting one!
		
		// the order doesn't matter here
		
		// parameter
		assertGenerated(assertHasParameterEdge(root, qp, select, "generated primary key"));
		
		// a SetWire from the instance to the form
		assertGenerated(assertHasSetWire(root, instance, viewForm));
		
	}
	
	/**
	 * Since we are only selecting one on the 'view news' page, we should
	 * not have any navigation buttons.
	 * 
	 * @throws Exception
	 */
	public void testViewNewsPageHasNoNavigationButtons() throws Exception {
		Frame view = assertHasFrame(root, "View News");
		InputForm viewForm = assertHasInputForm(view, "View News");
		
		assertHasNoButton(viewForm, "Next");
		assertHasNoButton(viewForm, "next");
		assertHasNoButton(viewForm, "Previous");
		assertHasNoButton(viewForm, "First");
		assertHasNoButton(viewForm, "Last");
		assertHasNoButton(viewForm, "Reset");
		
	}
	
	/**
	 * Test the contents of the 'Feed Item'
	 * 
	 * @throws Exception
	 */
	public void testTargetFeedForm() throws Exception {
		Frame feed = assertHasFrame(root, "Target Feed");
		InputForm form = assertHasInputForm(feed, "Feed Item");
		
		// a Label 'title'
		assertGenerated(assertHasLabel(form, "title"));
		assertGenerated(assertHasLabel(form, "description"));
		assertGenerated(assertHasLabel(form, "updated"));
		
		// a Link 'link'
		Button button = assertHasButton(form, "link");
		assertGenerated(button);
	}
	
	/**
	 * The 'link' button should have an action to the 'view news' page
	 * 
	 * @throws Exception
	 */
	public void testButtonHasNavigateAction() throws Exception {
		Frame view = assertHasFrame(root, "View News");

		Frame feed = assertHasFrame(root, "Target Feed");
		InputForm form = assertHasInputForm(feed, "Feed Item");

		// a Property 'generated primary key'
		Property pk = assertHasProperty(form, "generated primary key");
		assertGenerated(pk);
		
		// a Link 'link'
		Button button = assertHasButton(form, "link");
		EventTrigger onClick = button.getOnClick();
		
		NavigateAction nav = assertHasNavigateAction(root, onClick, view);
		
		// with a parameter
		ParameterEdge param = assertHasParameterEdge(root, pk, nav);
		assertEquals(param.getName(), "generated primary key");
		
	}
	
	/**
	 * The 'generated primary key' on the InputForm should be Set
	 * by the instance.
	 * 
	 * @throws Exception
	 */
	public void testFormPKIsSet() throws Exception {
		Frame feed = assertHasFrame(root, "Target Feed");
		InputForm form = assertHasInputForm(feed, "Feed Item");
		
		DomainObjectInstance instance = assertHasDomainObjectInstance(feed, "recent news");
		assertNotGenerated(instance);
		
		DomainAttributeInstance attribute = assertHasDomainAttributeInstance(instance, "generated primary key");
		assertGenerated(instance);

		// a Property 'generated primary key'
		Property pk = assertHasProperty(form, "generated primary key");
		assertGenerated(pk);
		
		// a SetWire
		assertHasSetWire(root, attribute, pk);
		
	}
	
	/**
	 * The 'Feed Item' form should have navigation buttons created.
	 * 
	 * @throws Exception
	 */
	public void testDatabaseTypes() throws Exception {
		Frame feed = assertHasFrame(root, "Target Feed");
		InputForm form = assertHasInputForm(feed, "Feed Item");

		// generated
		assertGenerated(assertHasButton(form, "Next"));
		assertGenerated(assertHasButton(form, "Previous"));
		assertGenerated(assertHasButton(form, "First"));
		assertGenerated(assertHasButton(form, "Last"));
		assertGenerated(assertHasLabel(form, "Results"));
		
	}

}
