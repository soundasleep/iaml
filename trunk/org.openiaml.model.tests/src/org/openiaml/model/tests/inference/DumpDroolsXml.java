/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.drools.DroolsXmlDumper;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * Dump the XML involved in the rule bases.
 * 
 * @author jmwright
 *
 */
public class DumpDroolsXml extends InferenceTestCase {
	
	public void setUp() throws Exception {
		super.setUp();		// set up project
	}
	
	public void testDumpXml() throws Exception {
		DroolsXmlDumper dump = new DroolsXmlDumper();
		Map<String,String> results = dump.getRuleXmls();
	
		for (String f : results.keySet()) {
			
			// who knows what format XmlDump is supplied in?
			// we will assume UTF-8 as the dumped XML is UTF-8
			InputStream source = new ByteArrayInputStream(results.get(f).getBytes("UTF-8")); 

			//IFile out = project.getFile(f.replaceAll("^[A-Za-z0-9_\\-]", "") + ".xml");
			String name = f.substring(f.lastIndexOf("/"));
			IFile out = project.getFile(name + ".xml");
			//project.getFolder(out.getProjectRelativePath()).create(true, true, monitor);
			
			System.out.println("Wrote " + out);
			out.create(source, true, monitor);

		}
		
		refreshProject();
	}

}
