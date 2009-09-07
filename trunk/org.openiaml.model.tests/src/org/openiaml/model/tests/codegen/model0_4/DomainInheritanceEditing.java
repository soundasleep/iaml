/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * Test the code generation of domain inheritance
 * when editing. In particular, this test case does
 * not set up an initial database.
 * 
 * @author jmwright
 *
 */
public class DomainInheritanceEditing extends CodegenTestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(DomainInheritanceEditing.class);
	}
	
	/**
	 * Just visiting the home page should not create a problem.
	 * 
	 * @throws Exception
	 */
	public void testHome() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
	}

	/**
	 * Since the initial database is empty,
	 * we cannot visit Person.
	 * 
	 * @throws Exception
	 */
	public void testPersonError() throws Exception {
		try {
			beginAtSitemapThenPage("get person");
			fail("Should not have been able to access 'person' page");
			assertNoProblem();
		} catch (FailingHttpStatusCodeException e) {
			// expected
		}
	}

	/**
	 * Since the initial database is empty,
	 * we cannot visit Student.
	 * Select student: enrolled = 'yesterday'
	 * 
	 * @throws Exception
	 */
	public void testStudentError() throws Exception {
		try {
			beginAtSitemapThenPage("get student");
			fail("Should not have been able to access 'student' page");
		} catch (FailingHttpStatusCodeException e) {
			// expected
		}
	}
	
	/**
	 * We create a new Person; we can then view this
	 * Person.
	 * 
	 * @throws Exception
	 */
	public void testCreatePerson() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("create a new person");
		assertNoProblem();
		
		String newName = "Test Name";
		{
			String field = getLabelIDForText("name");
			assertLabeledFieldEquals(field, "");
			setLabeledFormElementField(field, newName);
		}
		// this primary key is still rendered, because it's
		// not a generated primary key
		getLabelIDForText("id");
		
		// it is autosave, so we can immediately view the person page
		gotoSitemapThenPage(sitemap, "get person");		
		assertNoProblem();
		
		String name = getLabelIDForText("name");
		assertLabeledFieldEquals(name, newName);
	}
	
	/**
	 * We create a new person, but it does not
	 * have the correct name, so we cannot view it
	 * without an error.
	 * 
	 * @throws Exception
	 */
	public void testCreatePersonInvalid() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("create a new person");
		assertNoProblem();
		
		String newName = "Test Name 2";
		{
			String field = getLabelIDForText("name");
			assertLabeledFieldEquals(field, "");
			setLabeledFormElementField(field, newName);
		}
		// this primary key is still rendered, because it's
		// not a generated primary key
		getLabelIDForText("id");
		
		// it is autosave, so we can immediately try to
		// view the new person, but we will get an error
		try {
			gotoSitemapWithProblem(sitemap, "get person");
			fail("Expected to not be able to go to 'person' page");
		} catch (FailingHttpStatusCodeException e) {
			// expected
		}
	}
	
	/**
	 * We create a new Student; but without explicitly
	 * saving the new instance, we cannot view it
	 * until it is saved.
	 * 
	 * @throws Exception
	 */
	public void testCreateStudent() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("create a new student without autosave");
		assertNoProblem();
		
		String newName = "Test Student";
		{
			String field = getLabelIDForText("name");
			assertLabeledFieldEquals(field, "");
			setLabeledFormElementField(field, newName);
		}
		
		String newEnrolled = "yesterday";
		{
			String field = getLabelIDForText("enrolled");
			assertLabeledFieldEquals(field, "");
			setLabeledFormElementField(field, newEnrolled);
		}
		
		// autosave is off, so this page will not be saved
		try {
			gotoSitemapWithProblem(sitemap, "get student");
			fail("Expected to not be able to go to 'student' page");
		} catch (FailingHttpStatusCodeException e) {
			// expected
		}
		
		// but we can go back and continue editing
		gotoSitemapThenPage(sitemap, "create a new student without autosave");
		assertNoProblem();
		
		// the fields are still filled in
		{
			String field = getLabelIDForText("name");
			assertLabeledFieldEquals(field, newName);
		}
		{
			String field = getLabelIDForText("enrolled");
			assertLabeledFieldEquals(field, newEnrolled);
		}
		assertButtonPresentWithText("save student");
		clickButtonWithText("save student");
		assertNoProblem();
		
		// we can now view the student
		gotoSitemapThenPage(sitemap, "get student");
		assertNoProblem();
		
		{
			String field = getLabelIDForText("name");
			assertLabeledFieldEquals(field, newName);
		}
		{
			String field = getLabelIDForText("enrolled");
			assertLabeledFieldEquals(field, newEnrolled);
		}
	}
	
	/**
	 * We can create a Doctoral and view it.
	 * 
	 * @throws Exception
	 */
	public void testCreateDoctoral() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("create a new doctoral");
		assertNoProblem();
		
		String newName = "Test Student";
		{
			String field = getLabelIDForText("name");
			assertLabeledFieldEquals(field, "");
			setLabeledFormElementField(field, newName);
		}
		
		String newEnrolled = "yesterday";
		{
			String field = getLabelIDForText("enrolled");
			assertLabeledFieldEquals(field, "");
			setLabeledFormElementField(field, newEnrolled);
		}
		
		String newQualification = "bsc";
		{
			String field = getLabelIDForText("qualification");
			assertLabeledFieldEquals(field, "");
			setLabeledFormElementField(field, newQualification);
		}
		
		String newDegree = "science";
		{
			String field = getLabelIDForText("degree");
			assertLabeledFieldEquals(field, "");
			setLabeledFormElementField(field, newDegree);
		}
		
		// autosave is on, so we can immediately visit it
		gotoSitemapThenPage(sitemap, "get doctoral");
		assertNoProblem();
		
		checkDoctoralForm();
			
	}
	
	/**
	 * A specific Doctoral instance we are checking for.
	 * 
	 * @throws Exception
	 */
	private void checkDoctoralForm() throws Exception {
		{
			String field = getLabelIDForText("name");
			assertLabeledFieldEquals(field, "Test Student");
		}

		{
			String field = getLabelIDForText("enrolled");
			assertLabeledFieldEquals(field, "yesterday");
		}
		
		{
			String field = getLabelIDForText("qualification");
			assertLabeledFieldEquals(field, "bsc");
		}

		{
			String field = getLabelIDForText("degree");
			assertLabeledFieldEquals(field, "science");
		}
	}
	
	/**
	 * If we create a new Doctoral, we can reset the session
	 * and create a new Doctoral student.
	 * @throws Exception
	 */
	public void testCreateDoctoralSession() throws Exception {
		testCreateDoctoral();
		
		// this will also reset the session
		IFile sitemap = beginAtSitemapThenPage("create a new doctoral");
		assertNoProblem();
		
		String newName = "a new student";
		{
			String field = getLabelIDForText("name");
			assertLabeledFieldEquals(field, "");
			setLabeledFormElementField(field, newName);
		}
		
		String newEnrolled = "tomorrow";
		{
			String field = getLabelIDForText("enrolled");
			assertLabeledFieldEquals(field, "");
			setLabeledFormElementField(field, newEnrolled);
		}
		
		String newQualification = "ba";
		{
			String field = getLabelIDForText("qualification");
			assertLabeledFieldEquals(field, "");
			setLabeledFormElementField(field, newQualification);
		}
		
		String newDegree = "environmental studies";
		{
			String field = getLabelIDForText("degree");
			assertLabeledFieldEquals(field, "");
			setLabeledFormElementField(field, newDegree);
		}
		
		// we can immediately visit the doctoral page, but the
		// old doctoral object will still be rendered
		gotoSitemapThenPage(sitemap, "get doctoral");
		assertNoProblem();
		
		checkDoctoralForm();
	}
	
	/**
	 * If we set an invalid session property, then we will get
	 * an informative message when we try to create a new 
	 * Doctoral object.
	 * 
	 * @throws Exception
	 */
	public void testInvalidSession() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");
		
		setSession("session_init", "1");		
		setSession("new_object_scopes_12392019286_4_model_12392019209_4d0", "invalid");
		
		try {
			gotoSitemapThenPage(sitemap, "create a new doctoral");
			fail("Going to a page with an invalid session parameter set should fail");
		} catch (FailingHttpStatusCodeException e) {
			// expected
		}
		
		assertMatch("([iI]nvalid.+session|[sS]ession.+invalid)");
		
		// there should be a link to "reset your session"
		assertLinkPresent("session_reset");
		clickLink("session_reset");
		
		assertNoProblem();
		
		// we can now create a new doctoral as normal
		gotoSitemapThenPage(sitemap, "create a new doctoral");
		assertNoProblem();
	}
	
	/**
	 * Set a session variable. Uses the "set_session.php" script generated.
	 *  
	 * @param key
	 * @param value
	 */
	private void setSession(String key, String value) {
		// insert a session id
		IFile setSession = getProject().getFile("output/set_session.php");
		assertTrue("session file '" + setSession + "' should exist", setSession.exists());
		gotoPage(setSession.getProjectRelativePath().toString() + "?id=" + key + "&arg0=" + value);
		
		assertNoProblem();
	}
	
}
