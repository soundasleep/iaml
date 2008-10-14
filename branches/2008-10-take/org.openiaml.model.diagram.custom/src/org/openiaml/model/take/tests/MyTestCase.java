package org.openiaml.model.take.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.openiaml.model.model.InternetApplication;

public class MyTestCase extends TestCase {
	
	InternetApplication root;

	public void setUp() throws IOException {
		XMIResourceImpl resource = new XMIResourceImpl();
		File source = new File("src/org/openiaml/model/take/tests/test.iaml");
		System.out.println(source.getCanonicalPath());
		assertTrue("source test file exists", source.exists());
		
		resource.load( new FileInputStream(source), new HashMap<Object,Object>());
		for (EObject eo : resource.getContents()) {
			System.out.println(eo);
		}
	}
	
	public void test1() {
		assertTrue(true);
	}
	
}
