/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_4;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * @implementation InputTextField
 * 		An {@model InputTextField} can contain additional {@model Label}s.
 * @example InputTextField
 * 		An {@model InputTextField} can contain additional {@model Label}s.
 */
public class TextFieldAnnotation extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(TextFieldAnnotation.class);
	}
	
	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
	}
	
	public void testLabelIsPresent() throws Exception {
		
		beginAtSitemapThenPage("Home");		
		assertLabelTextPresent("hello, world!");
		
	}
	
	/**
	 * If we update the 'update annotation' text field, the
	 * label is also updated.
	 * 
	 * @throws Exception
	 */
	public void testUpdateAnnotation() throws Exception {
		
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("update annotation");
			assertLabeledFieldEquals(target, "");	// shouldn't this be 'hello, world!'?
			setLabeledFormElementField(target, "a new annotation");
		}
		
		// label should have changed
		assertLabelTextNotPresent("hello, world!");
		assertLabelTextPresent("a new annotation");
		
	}
	
	/**
	 * If we update the annotation, the containing text field of the
	 * label does not change.
	 * 
	 * @throws Exception
	 */
	public void testUpdateAnnotationDoesNotChangeContainingLabel() throws Exception {
		
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("text field");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, "should not change");
		}
		
		// update the annotation
		{
			String target = getLabelIDForText("update annotation");
			assertLabeledFieldEquals(target, "");	// shouldn't this be 'hello, world!'?
			setLabeledFormElementField(target, "a new annotation");
		}
		
		// label should have changed
		assertLabelTextNotPresent("hello, world!");
		assertLabelTextPresent("a new annotation");
		
		// but the containing text field should not have
		{
			String target = getLabelIDForText("text field");
			assertLabeledFieldEquals(target, "should not change");
		}
		
	}

}
