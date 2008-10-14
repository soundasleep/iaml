package org.openiaml.model.take.tests;

import iaml.generated2.GeneratedAppChildren;
import iaml.generated2.KBGenerated;

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
		
		ResultSet<GeneratedAppChildren> rs = kb.generated_app_children_10(root);
		// print out the derivation log
		for (DerivationLogEntry e : rs.getDerivationLog()) {
			System.out.println(e.getName() + ": " + e.getCategory());
		}
		assertTrue(rs.hasNext());
		for (GeneratedAppChildren n : new IteratorWrapper<GeneratedAppChildren>(rs)) {
			System.out.println("hello");
			System.out.println(n);
		}
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
