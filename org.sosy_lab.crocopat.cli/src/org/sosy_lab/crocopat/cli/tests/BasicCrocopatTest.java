/**
 * 
 */
package org.sosy_lab.crocopat.cli.tests;

import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import org.sosy_lab.crocopat.cli.CrocopatCliPlugin;
import org.sosy_lab.crocopat.cli.ExecuteCrocopat;

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
		CrocopatCliPlugin instance = CrocopatCliPlugin.getInstance();
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
		assertTrue("InfiniteLoop p1", result.contains("InfiniteLoop\tp1\t"));
		assertTrue("InfiniteLoop p2", result.contains("InfiniteLoop\tp2\t"));
		assertTrue("InfiniteLoop p3", result.contains("InfiniteLoop\tp3\t"));
		
		System.out.println(result);
		
	}
	
}
