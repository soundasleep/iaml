package org.openiaml.model.take.tests;

import iaml.generated2.GeneratedAppChildren;
import iaml.generated2.InternetApplication_Name;
import iaml.generated2.KBGenerated;
import iaml.generated2.TestFromExternal;
import iaml.generated2.TestQueryB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import junit.framework.TestCase;
import nz.org.take.rt.DerivationLogEntry;
import nz.org.take.rt.ResultSet;

import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.openiaml.model.diagram.custom.commands.TestDeriveElementsCommand;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.Page;

/**
 * Here we will temporarily test the Take integration, to make sure it operates as expected.
 * TODO move into the testing plugin.
 * 
 * @author jmwright
 *
 */
public class MyTestCase extends TestCase {
	
	InternetApplication root;

	public void setUp() throws IOException {
		XMIResourceImpl resource = new XMIResourceImpl();
		File source = new File("src/org/openiaml/model/take/tests/test.iaml");
		assertTrue("source test file exists", source.exists());
		
		resource.load( new FileInputStream(source), new HashMap<Object,Object>());
		assertTrue("root of test model is an InternetApplication", resource.getContents().get(0) instanceof InternetApplication);
		root = (InternetApplication) resource.getContents().get(0);
	}
	
	/**
	 * Test the contents of the model.
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
	
	public void testDerivePage() {
		// try deriving command
		TestDeriveElementsCommand te = new TestDeriveElementsCommand();
		KBGenerated kb = te.executeKnowledgeBase(root);
		
		ResultSet<GeneratedAppChildren> rs = kb.getApplicationChildren(root);
		// print out the derivation log
		System.out.println("count = " + rs.getDerivationController().getDerivationCount());
		System.out.println("depth = " + rs.getDerivationController().getDepth());
		for (DerivationLogEntry e : rs.getDerivationLog()) {
			System.out.println(e.getName() + ": " + e.getCategory());
		}
		assertTrue(rs.hasNext());
		for (GeneratedAppChildren n : new IteratorWrapper<GeneratedAppChildren>(rs)) {
			System.out.println("testDerivePage> " + n);
		}
	}

	/**
	 * This should just query the external facts source
	 */
	public void testGetAppName() {
		// try deriving command
		TestDeriveElementsCommand te = new TestDeriveElementsCommand();
		KBGenerated kb = te.executeKnowledgeBase(root);
		
		ResultSet<InternetApplication_Name> rs = kb.getAppName(root);
		// print out the derivation log
		/*
		for (DerivationLogEntry e : rs.getDerivationLog()) {
			System.out.println(e.getName() + ": " + e.getCategory());
		}
		*/
		assertTrue(rs.hasNext());
		InternetApplication_Name b = rs.next();
		assertEquals(b.app, root);
		assertEquals(b.string, "test");		// the app's name should be test
		assertFalse(rs.hasNext());
	}

	/**
	 * This should just query the external facts source, and then
	 *  derive an additional property about it
	 */
	public void testDeriveFromExternal() {
		// try deriving command
		TestDeriveElementsCommand te = new TestDeriveElementsCommand();
		KBGenerated kb = te.executeKnowledgeBase(root);
		
		ResultSet<TestFromExternal> rs = kb.getTestFromExternal(root);
		// print out the derivation log
		/*
		for (DerivationLogEntry e : rs.getDerivationLog()) {
			System.out.println(e.getName() + ": " + e.getCategory());
		}
		*/
		assertTrue(rs.hasNext());
		TestFromExternal b = rs.next();
		assertEquals(b.app, root);
		assertEquals(b.string, "hello world");		// the app's name should be test
		assertFalse(rs.hasNext());
	}

	public void testDeriveSimpleFact() {
		// try deriving command
		TestDeriveElementsCommand te = new TestDeriveElementsCommand();
		KBGenerated kb = te.executeKnowledgeBase(root);
		
		ResultSet<TestQueryB> rs = kb.getTestQueryB(root);
		// print out the derivation log
		/*
		for (DerivationLogEntry e : rs.getDerivationLog()) {
			System.out.println(e.getName() + ": " + e.getCategory());
		}
		*/
		assertTrue(rs.hasNext());
		TestQueryB b = rs.next();
		assertEquals(b.app, root);
		assertFalse(rs.hasNext());
	}

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
