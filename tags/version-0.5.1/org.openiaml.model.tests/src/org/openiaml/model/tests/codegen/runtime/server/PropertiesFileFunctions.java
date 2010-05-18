/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.server;

import java.io.File;

import org.eclipse.core.resources.IFile;

/**
 * Checks property file functions.
 * Resolves issue 166.
 * 
 * @author jmwright
 *
 */
public class PropertiesFileFunctions extends PhpCodegenTestCase {

	/**
	 * @throws Exception
	 */
	public void testReadMissingFile() throws Exception {
		// a property file that doesnt exist returns array()
		assertPhpResult(0, "count(load_properties('nothing.properties'))");
	}
	
	/**
	 * @throws Exception
	 */
	public void testReadExistingFile() throws Exception {
		// copy a file
		copyFileIntoWorkspace(new File(ROOT + "codegen/runtime/server/sample.properties"), getProject().getFile("output/sample.properties"));

		String init = "$p = load_properties('sample.properties');";

		// we can read this one
		assertPhpResult(6, init, "count($p)");
		
		// we can read the keys OK
		assertPhpResult("default", init, "get_property($p, 'not here', 'default')");
		assertPhpResult(false, init, "get_property($p, 'not here')");

		assertPhpResult("apple", init, "get_property($p, 'fruit')");
		assertPhpResult("cat", init, "get_property($p, 'animal')");
		assertPhpResult("another line with white space", init, "get_property($p, 'line.with.white.space')");
		assertPhpResult("one two threefour five", init, "get_property($p, 'line.with.newlines')");
		assertPhpResult("one \ntwo ", init, "get_property($p, 'actual.newlines')");
		assertPhpResult("ok", init, "get_property($p, 'comment')");

	}
	
	/**
	 * @throws Exception
	 */
	public void testModifyExistingFile() throws Exception {
		// copy a file
		copyFileIntoWorkspace(new File(ROOT + "codegen/runtime/server/sample.properties"), getProject().getFile("output/sample.properties"));

		{
			String init = "$p = load_properties('sample.properties');";
	
			// read it
			assertPhpResult("apple", init, "get_property($p, 'fruit')");
		}
		
		// set it
		{
			String init = "$p = load_properties('sample.properties'); $p = set_property('sample.properties', $p, 'fruit', 'banana');";
	
			// read it
			assertPhpResult("banana", init, "get_property($p, 'fruit')");
		}

		// add a key that doesn't exist yet
		{
			String init = "$p = load_properties('sample.properties'); $p = set_property('sample.properties', $p, 'new', 'orange');";
	
			// read it
			assertPhpResult("orange", init, "get_property($p, 'new')");
		}

	}
	
	/**
	 * @throws Exception
	 */
	public void testSetWithNewlines() throws Exception {
		// copy a file
		copyFileIntoWorkspace(new File(ROOT + "codegen/runtime/server/sample.properties"), getProject().getFile("output/sample.properties"));
		
		// set it
		{
			String init = "$p = load_properties('sample.properties'); $p = set_property('sample.properties', $p, 'fruit', 'one\ntwo\nthree');";
	
			// read it
			assertPhpResult("one\ntwo\nthree", init, "get_property($p, 'fruit')");
		}

		// add a key that doesn't exist yet
		{
			String init = "$p = load_properties('sample.properties'); $p = set_property('sample.properties', $p, 'new', \"one \n two \n three\");";
	
			// read it
			assertPhpResult("one \n two \n three", init, "get_property($p, 'new')");
		}

		// make sure that the set above was stored correctly
		{
			String init = "$p = load_properties('sample.properties');";
	
			// read it
			assertPhpResult("one \n two \n three", init, "get_property($p, 'new')");
		}
		

		// add a key that doesn't exist yet
		{
			String init = "$p = load_properties('sample.properties'); $p = set_property('sample.properties', $p, 'new', \"one\\\n\\\\four\");";
	
			// read it
			assertPhpResult("one\\\n\\four", init, "get_property($p, 'new')");
		}

		// make sure that the set above was stored correctly
		{
			String init = "$p = load_properties('sample.properties');";
	
			// read it
			assertPhpResult("one\\\n\\four", init, "get_property($p, 'new')");
		}
		
	}
	
	/**
	 * @throws Exception
	 */
	public void testCreateNewProperties() throws Exception {

		// should not exist yet
		IFile created = getProject().getFile("output/created.properties");
		assertFalse("Created file '" + created + "' already exists", created.exists());

		// set it
		{
			String init = "$p = load_properties('created.properties'); $p = set_property('created.properties', $p, 'fruit', 'banana');";
	
			// read it
			assertPhpResult("banana", init, "get_property($p, 'fruit')");
		}
		
		// and the file should exist in the project
		getProject().refreshProject();
		assertTrue("Created file '" + created + "' does not exist", created.exists());

	}

}
