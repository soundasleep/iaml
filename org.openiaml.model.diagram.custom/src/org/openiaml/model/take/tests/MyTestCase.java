package org.openiaml.model.take.tests;

import iaml.generated2.ActionGeneratePage;
import iaml.generated2.ActionHasGeneratedPage;
import iaml.generated2.KBGenerated;
import iaml.generated2.NeedsGeneratedPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import junit.framework.TestCase;
import nz.org.take.rt.ResultSet;

import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.openiaml.model.diagram.custom.commands.TestDeriveElementsCommand;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.Page;

/**
 * Here we will temporarily test the Take integration, to make sure it operates as expected.
 * TODO move into the testing plugin once we get the tests working.
 * 
 * @author jmwright
 *
 */
public class MyTestCase extends TestCase {
	
	InternetApplication root;

	/**
	 * Load the example model.
	 */
	public void setUp() throws IOException {
		XMIResourceImpl resource = new XMIResourceImpl();
		File source = new File("src/org/openiaml/model/take/tests/test.iaml");
		assertTrue("source test file exists", source.exists());
		
		resource.load( new FileInputStream(source), new HashMap<Object,Object>());
		assertTrue("root of test model is an InternetApplication", resource.getContents().get(0) instanceof InternetApplication);
		root = (InternetApplication) resource.getContents().get(0);
	}
	
	/**
	 * Tests loading the content of the model. (passes)
	 */
	public void testModel() {
		assertEquals(root.getName(), "test");
		assertEquals(root.getChildren().size(), 1);
		assertTrue(root.getChildren().get(0) instanceof Page);
		Page p = (Page) root.getChildren().get(0);
		assertEquals(p.getName(), "page");
		assertEquals(p.getChildren().size(), 2);	// two input forms
		for (ApplicationElement e : p.getChildren()) {
			assertTrue(e instanceof InputForm);
		}
	}


	/**
	 * This should just query the external facts source. (passes)
	 * 
	 * - app_name[app, 'test']
	 */
	public void testGetAppName() {
		// try deriving command
		TestDeriveElementsCommand te = new TestDeriveElementsCommand();
		KBGenerated kb = te.executeKnowledgeBase(root);
		
		ResultSet<ActionGeneratePage> rs = kb.getPagesToGenerate(root);
		assertTrue(rs.hasNext());
		while (rs.hasNext()) {
			ActionGeneratePage r = rs.next();
			System.out.println("r = " + r);
		}
	}	

	/**
	 * This should just query the external facts source. (passes)
	 * 
	 * - app_name[app, 'test']
	 */
	public void testGetAppName2() {
		// try deriving command
		TestDeriveElementsCommand te = new TestDeriveElementsCommand();		
		KBGenerated kb = te.executeKnowledgeBase(root);
		
		int beforeSize = root.getChildren().size();
		
		ResultSet<ActionHasGeneratedPage> rs = kb.getHasGeneratedPages(root);
		assertTrue(rs.hasNext());
		while (rs.hasNext()) {
			ActionHasGeneratedPage r = rs.next();
			System.out.println("r = " + r);
			//assertNotSame(root, r.app);		// always true, i.e. it doesn't clone IApp
		}
		
		// did it change the children of the InternetApplication?
		int afterSize = root.getChildren().size();
		
		assertNotSame(beforeSize, afterSize);
		
	}	

	/**
	 * This should just query the external facts source. (passes)
	 * 
	 * - app_name[app, 'test']
	 */
	public void testGetNeededPages() {
		// try deriving command
		TestDeriveElementsCommand te = new TestDeriveElementsCommand();		
		KBGenerated kb = te.executeKnowledgeBase(root);
		
		int beforeSize = root.getChildren().size();
		
		ResultSet<NeedsGeneratedPage> rs = kb.getPagesNeededToGenerate(root);
		assertTrue(rs.hasNext());
		while (rs.hasNext()) {
			NeedsGeneratedPage r = rs.next();
			System.out.println("r = " + r);
			//assertNotSame(root, r.app);		// always true, i.e. it doesn't clone IApp
		}
		
		// did it change the children of the InternetApplication?
		int afterSize = root.getChildren().size();
		
		assertNotSame(beforeSize, afterSize);
		
	}	
	
	/**
	 * Tests deriving pages. (fails)
	 * Because our InternetApplication has a name "test", we should get a generated page ("page_wrapper")
	 * 
	 * - if app_name[app, 'test'] then generated_app_children[app, page_wrapper]
	 * 
	 */
	/*
	public void testDerivePage() {
		// try deriving command
		TestDeriveElementsCommand te = new TestDeriveElementsCommand();
		KBGenerated kb = te.executeKnowledgeBase(root);
		
		ResultSet<GeneratedAppChildren> rs = kb.getApplicationChildren(root);
		assertTrue(rs.hasNext());	// there should be at least one generated page
		// (but instead, it is crashing with a NullPointerException)
		for (GeneratedAppChildren n : new IteratorWrapper<GeneratedAppChildren>(rs)) {
			System.out.println("testDerivePage> " + n);
			System.out.println("generated element: " + n.element);
		}

		// print out the derivation log
		System.out.println("count = " + rs.getDerivationController().getDerivationCount());
		System.out.println("depth = " + rs.getDerivationController().getDepth());
		for (DerivationLogEntry e : rs.getDerivationLog()) {
			System.out.println(e.getName() + ": " + e.getCategory());
		}
	
	}*/

	/**
	 * This should just query the external facts source, and then
	 * derive an additional property about it ('test_from_external') (fails)
	 *  
	 * - rule_test_external: if app_name[app, 'test'] then test_from_external[app, 'hello world']
	 */
	/*
	public void testDeriveFromExternal() {
		// try deriving command
		TestDeriveElementsCommand te = new TestDeriveElementsCommand();
		KBGenerated kb = te.executeKnowledgeBase(root);
		
		ResultSet<TestFromExternal> rs = kb.getTestFromExternal(root);
		// print out the derivation log
		assertTrue(rs.hasNext());
		TestFromExternal b = rs.next();
		assertEquals(b.app, root);
		assertEquals(b.string, "hello world");		// the app's name should be test
		assertFalse(rs.hasNext());
	}*/

	/**
	 * Try a simple derivation. Expected: b[app] (fails)
	 * 
	 * - test1: if a[app] then b[app]
	 * - fact1: a[app]
	 */
	/*
	public void testDeriveSimpleFact() {
		// try deriving command
		TestDeriveElementsCommand te = new TestDeriveElementsCommand();
		KBGenerated kb = te.executeKnowledgeBase(root);
		
		ResultSet<TestQueryB> rs = kb.getTestQueryB(root);
		// Constants.app = root;
		// print out the derivation log

		for (DerivationLogEntry e : rs.getDerivationLog()) {
			System.out.println(e.getName() + ": " + e.getCategory());
		}

		assertTrue(rs.hasNext());
		TestQueryB b = rs.next();
		assertEquals(b.app, root);
		assertFalse(rs.hasNext());
	}
	*/

	/**
	 * A simple wrapper to wrap around a ResourceIterator so we can get a normal iterator() from it.
	 * TODO make Take's ResourceIterator also Iterable
	 * 
	 * @author jmwright
	 * @param <T>
	 */
	private class IteratorWrapper<T> implements Iterable<T> {
		private Iterator<T> it;
		public IteratorWrapper(Iterator<T> it) {
			this.it = it;
		}
		
		@Override
		public Iterator<T> iterator() {
			return it;
		}
	}
	
}
