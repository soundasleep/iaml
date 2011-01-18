/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_3;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jwebunit.api.IElement;

import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.tests.codegen.DatabaseCodegenHelper;
import org.openiaml.model.tests.codegen.DatabaseCodegenTest;
import org.openiaml.model.tests.codegen.model0_5.MapsCodegenTestCase;

/**
 * Tests all of the visual elements in IAML; that is,
 * all {@model VisibleThing}s. This can therefore be useful for screenshots etc.
 * 
 * @example VisibleThing,InputForm,InputTextField,Label,Map,MapPoint,IteratorList,Hidden
 * 		An example model to render all {@model VisibleThing}s in the
 * 		IAML metamodel.
 * 
 */
public class IamlVisualElements extends MapsCodegenTestCase implements DatabaseCodegenTest {

	private DatabaseCodegenHelper helper;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(getClass());
		
		// need to initialise the database as well
		helper = new DatabaseCodegenHelper(this, this);
		helper.initialiseDatabase();
	}
	
	/**
	 * Tests {@model Frame}.
	 */
	public void testFrame() throws Exception {
		beginAtSitemapThenPage("Frame");
		assertNoProblem();
	}
	
	/**
	 * Tests {@model InputForm}.
	 */
	public void testInputForm() throws Exception {
		beginAtSitemapThenPage("Frame");
		assertNoProblem();
		
		assertHasInputForm("Input Form");
		
	}

	/**
	 * Tests {@model InputTextField}.
	 */
	public void testInputTextField() throws Exception {
		beginAtSitemapThenPage("Frame");
		assertNoProblem();
		
		{
			String target = getLabelIDForText("Input Text Field");
			assertLabeledFieldEquals(target, "");
		}
		
	}

	/**
	 * Tests {@model Button}.
	 */
	public void testButton() throws Exception {
		beginAtSitemapThenPage("Frame");
		assertNoProblem();
		
		assertButtonPresentWithText("Button");
		
	}
	
	/**
	 * Tests {@model Map}.
	 */
	public void testMap() throws Exception {
		beginAtSitemapThenPage("Frame");
		assertNoProblem();
		
		assertHasMap("Map");
		
	}
	
	/**
	 * Tests {@model MapPoint}.
	 */
	public void testMapPoint() throws Exception {
		beginAtSitemapThenPage("Frame");
		assertNoProblem();
		
		IElement map = assertHasMap("Map");
		assertHasMapPoint(map, "Map Point");
		
		// TODO no way to check the location of the map point?
	}
	
	/**
	 * Tests {@model Label}.
	 */
	public void testLabel() throws Exception {
		beginAtSitemapThenPage("Frame");
		assertNoProblem();
		
		assertLabelTextExactlyPresent("Label");
	}
	
	/**
	 * Tests {@model Hidden}.
	 */
	public void testHidden() throws Exception {
		beginAtSitemapThenPage("Frame");
		assertNoProblem();
		
		// Hidden things aren't visible!
		assertLabelTextNotPresent("Hidden");
	}
	
	/**
	 * Tests {@model IteratorList}.
	 */
	public void testIteratorList() throws Exception {
		beginAtSitemapThenPage("Frame");
		assertNoProblem();
		
		assertLabelTextExactlyPresent("Scope");
		assertLabelTextExactlyPresent("Frame");
		assertLabelTextExactlyPresent("Visible Thing");
		assertLabelTextExactlyPresent("Input Form");
		assertLabelTextExactlyPresent("Label");
	}
	
	/**
	 * Assert that a named input form - that is, a form with a contained H2
	 * element with the given name - exists.
	 * 
	 * @see InputForm
	 * @param string the form title to search for
	 */
	private void assertHasInputForm(String string) {
		IElement found = null;
		StringBuffer foundStrings = new StringBuffer();
		
		List<IElement> h2s = getElementsByXPath("//form/h2");
		for (IElement h2 : h2s) {
			foundStrings.append(h2.getTextContent()).append(", ");
			if (string.equals(h2.getTextContent())) {
				// found it
				assertNull("Found a duplicate form for title '" + string + "': " + found, found);
				found = h2;
			}
		}

		assertNotNull("Found no forms for title '" + string + "', forms found: " + foundStrings, found);
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.codegen.DatabaseCodegenTest#getDatabaseInitialisers1()
	 */
	@Override
	public List<String> getDatabaseInitialisers1() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE Iterator_List_contents (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, key VARCHAR(64), value VARCHAR(64))");
		s.add("INSERT INTO Iterator_List_contents (generated_primary_key, key, value) VALUES (12, 'Frame', 'Scope')");
		s.add("INSERT INTO Iterator_List_contents (generated_primary_key, key, value) VALUES (23, 'Input Form', 'Visible Thing')");
		s.add("INSERT INTO Iterator_List_contents (generated_primary_key, key, value) VALUES (45, 'Label', 'Visible Thing')");
		return s;
	}

	
}
