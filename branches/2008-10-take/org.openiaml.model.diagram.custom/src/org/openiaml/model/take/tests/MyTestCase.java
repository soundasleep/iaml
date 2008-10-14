package org.openiaml.model.take.tests;

import iaml.generated2.InternetApplication_Children;
import iaml.generated2.KBGenerated;
import iaml.generated2.NamedElement_Name;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.openiaml.model.diagram.custom.commands.TestDeriveElementsCommand;
import org.openiaml.model.model.InternetApplication;

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
	
	public void test1() throws ExecutionException {
		// try deriving command
		TestDeriveElementsCommand te = new TestDeriveElementsCommand();
		KBGenerated kb = te.executeKnowledgeBase(root);
		for (InternetApplication_Children n : new IteratorWrapper<InternetApplication_Children>(kb.getAppChildren(root))) {
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
