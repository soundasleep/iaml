/**
 * 
 */
package org.sosy_lab.crocopat.cli.tests;

import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import org.sosy_lab.crocopat.cli.ExecuteCrocopat;
import org.sosy_lab.crocopat.cli.ExecuteCrocopat.CrocopatException;

/**
 * @author jmwright
 *
 */
public class BasicCrocopatTest extends TestCase {

	/**
	 * A basic test for Crocopat integration.
	 * 
	 */
	public void testBasic() throws Exception {
		CrocopatCliTestsPlugin instance = CrocopatCliTestsPlugin.getInstance();
		assertNotNull(instance);
		
		URL rml;
		URL rsf;
		try {
			rml = instance.getResolvedFile("tests/test.rml");
			assertNotNull(rml);
			rsf = instance.getResolvedFile("tests/test.rsf");
			assertNotNull(rsf);
		} catch (NullPointerException e) {
			throw new RuntimeException("A file might be missing from the bundle: " + e.getMessage(), e);
		}

		// get the crocopat output
		ExecuteCrocopat exec = new ExecuteCrocopat();
		List<String> result = exec.execute(rml.openStream(), rsf.openStream());
		
		assertNotNull("Result should not be null", result);
		assertEquals(3, result.size());
		System.out.println(result);
		assertTrue("InfiniteLoop p1", result.contains("InfiniteLoop\tp1\t"));
		assertTrue("InfiniteLoop p2", result.contains("InfiniteLoop\tp2\t"));
		assertTrue("InfiniteLoop p3", result.contains("InfiniteLoop\tp3\t"));	
		
	}

	/**
	 * Test an invalid file being loaded.
	 * 
	 */
	public void testInvalid1() throws Exception {
		CrocopatCliTestsPlugin instance = CrocopatCliTestsPlugin.getInstance();
		assertNotNull(instance);
		
		URL rml;
		URL rsf;
		try {
			rml = instance.getResolvedFile("tests/invalid.rml");
			assertNotNull(rml);
			rsf = instance.getResolvedFile("tests/test.rsf");
			assertNotNull(rsf);
		} catch (NullPointerException e) {
			throw new RuntimeException("A file might be missing from the bundle: " + e.getMessage(), e);
		}

		// get the crocopat output
		ExecuteCrocopat exec = new ExecuteCrocopat();
		try {
			exec.execute(rml.openStream(), rsf.openStream());
			fail("Should have thrown an exception");
		} catch (CrocopatException e) {
			// expected
		}
		
	}
	
	/**
	 * Test an invalid file being loaded.
	 */
	public void testInvalid2() throws Exception {
		CrocopatCliTestsPlugin instance = CrocopatCliTestsPlugin.getInstance();
		assertNotNull(instance);
		
		URL rml;
		URL rsf;
		try {
			rml = instance.getResolvedFile("tests/test.rml");
			assertNotNull(rml);
			rsf = instance.getResolvedFile("tests/invalid.rsf");
			assertNotNull(rsf);
		} catch (NullPointerException e) {
			throw new RuntimeException("A file might be missing from the bundle: " + e.getMessage(), e);
		}

		// get the crocopat output
		ExecuteCrocopat exec = new ExecuteCrocopat();
		try {
			exec.execute(rml.openStream(), rsf.openStream());
			fail("Should have thrown an exception");
		} catch (CrocopatException e) {
			// expected
		}
		
	}
	
	/**
	 * Test both invalid files being loaded.
	 * 
	 */
	public void testInvalid3() throws Exception {
		CrocopatCliTestsPlugin instance = CrocopatCliTestsPlugin.getInstance();
		assertNotNull(instance);
		
		URL rml;
		URL rsf;
		try {
			rml = instance.getResolvedFile("tests/invalid.rml");
			assertNotNull(rml);
			rsf = instance.getResolvedFile("tests/invalid.rsf");
			assertNotNull(rsf);
		} catch (NullPointerException e) {
			throw new RuntimeException("A file might be missing from the bundle: " + e.getMessage(), e);
		}

		// get the crocopat output
		ExecuteCrocopat exec = new ExecuteCrocopat();
		try {
			exec.execute(rml.openStream(), rsf.openStream());
			fail("Should have thrown an exception");
		} catch (CrocopatException e) {
			// expected
		}
		
	}
	
}
