/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_1;

import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 *
 * @author jmwright
 */
public class FeedProducerComplete extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(FeedProducerComplete.class);
	}
	
	/**
	 * The 'Feed Item' form should have navigation buttons created.
	 * 
	 * @throws Exception
	 */
	public void testDatabaseTypes() throws Exception {
		Frame feed = assertHasFrame(root, "Target Feed");
		
		DomainIterator instance = assertHasDomainIterator(feed, "recent news");
		InputForm form = assertHasInputForm(feed, "Feed Item");
		
		assertHasSetWire(root, instance, form, "set");

		// generated
		assertGenerated(assertHasButton(form, "Next"));
		assertGenerated(assertHasButton(form, "Previous"));
		assertGenerated(assertHasButton(form, "First"));
		assertGenerated(assertHasButton(form, "Last"));
		assertGenerated(assertHasLabel(form, "Results"));
		
	}

}
