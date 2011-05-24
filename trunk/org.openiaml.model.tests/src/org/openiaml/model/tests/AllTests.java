package org.openiaml.model.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.openiaml.model.tests.codegen.AllCodegenTests;
import org.openiaml.model.tests.drools.AllDroolsTests;
import org.openiaml.model.tests.inference.AllInferenceTests;
import org.openiaml.model.tests.migration.AllMigrationTests;
import org.openiaml.model.tests.model.AllModelTests;
import org.openiaml.model.tests.release.AllReleaseTests;

/**
 * All tests.
 * 
 * @see AllInferenceTests
 * @see AllCodegenTests
 * @see AllEclipseTests
 * @author jmwright
 *
 */
public class AllTests {
	
	/**
	 * Compile all the suites.
	 * 
	 * @return
	 */
    public static Test suite() { 
        TestSuite suite = new TestSuite("All Tests");

        // delete all infer-output models
        suite.addTestSuite(ClearInferredModels.class);

        suite.addTest(AllInferenceTests.suite());
        suite.addTest(AllCodegenTests.suite());
        suite.addTest(AllDroolsTests.suite());
        suite.addTest(AllReleaseTests.suite());
        suite.addTest(AllMigrationTests.suite());
        suite.addTest(AllModelTests.suite());

        // analyse for unused elements
        suite.addTestSuite(IdentifyUnusedMetamodelFeatures.class);
        suite.addTestSuite(IdentifyUnusedInferenceRules.class);
        
        // commit inferred models
        suite.addTestSuite(CommitInferredModels.class);

        return suite; 
    }
    
    /**
     * A helper "test case" that simply deletes all of the folders in infer-output.
     * 
     */
    public static class ClearInferredModels extends TestCase {
    	public ClearInferredModels() {
    		
    	}
    	
    	public void testClearInferredModels() throws Exception {
    		File infer = new File("infer-output");
    		assertTrue(infer.exists());
    		assertTrue(infer.isDirectory());
    		
    		// check that the batch script exists
    		assertTrue(new File("infer-output/clear_models.bat").exists());
    		
    		Runtime.getRuntime().exec(
    				"cmd /c start clear_models.bat",
    				getEnvironmentVariables(),
    				infer);
    	}

    }
    
    /**
     * A helper "test case" that simply commits all of the files in infer-output.
     * 
     */
	public static class CommitInferredModels extends TestCase {
		public CommitInferredModels() {
			
		}
		
		public void testCommitInferredModels() throws Exception {
    		File infer = new File("infer-output");
    		assertTrue(infer.exists());
    		assertTrue(infer.isDirectory());
    		
    		// check that the batch script exists
    		assertTrue(new File("infer-output/commit_models.bat").exists());
    		
    		Runtime.getRuntime().exec(
    				"cmd /c start commit_models.bat", 
    				getEnvironmentVariables(),
    				infer);
    	}
	}

	private static String[] getEnvironmentVariables() {
		Map<String, String> env = System.getenv();
		List<String> buf = new ArrayList<String>();
		for (String key : env.keySet()) {
			buf.add(key + "=" + env.get(key));
		}
		return buf.toArray(new String[] {});
	}
	
}
