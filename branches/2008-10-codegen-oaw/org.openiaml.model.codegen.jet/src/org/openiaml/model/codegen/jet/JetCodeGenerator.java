/**
 * 
 */
package org.openiaml.model.codegen.jet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jet.JET2Platform;
import org.openiaml.model.codegen.ICodeGenerator;

/**
 * @author jmwright
 *
 */
public class JetCodeGenerator implements ICodeGenerator {
	
	public static final String PLUGIN_ID = "org.openiaml.model.codegen.jet";
	
	/* (non-Javadoc)
	 * @see org.openiaml.model.codegen.ICodeGenerator#generateCode(java.lang.String, java.lang.String)
	 */
	@Override
	public IStatus generateCode(IFile file, IProgressMonitor monitor) {

		/*
		File f = new File(filename);

		if (!(f.exists() && f.canRead()))
			return new Status(Status.ERROR, PLUGIN_ID, "Could not read file " + filename);
		String fileContents;
		try {
			fileContents = openFile(f);
		} catch (IOException e) {
			return new Status(Status.ERROR, PLUGIN_ID, "Could not open file " + filename, e);
		}
		
		Map<String,Object> variables = new HashMap<String,Object>();
		
		/*
		// we need to set this parameter ourselves
		variables.put("org.eclipse.jet.resource.project.name", outputDir);
		// set any other parameters here
		
		// run the transformation on a certain JET plugin, loading the data as XML
		// (although the JET plugin should define the real loader)
		return JET2Platform.runTransformOnString("org.openiaml.model.codegen.jet", 
				fileContents, "xml", variables, monitor);
				*/
		
		// can we do this automatically now?
		// TODO check and remove comment block above
		Map<String,Object> variables = new HashMap<String,Object>();
		return JET2Platform.runTransformOnResource("org.openiaml.model.codegen.jet", file, variables, monitor);

	}


	/**
	 * Helper method: open a file and return its entire contents as a string
	 * 
	 * @param f The file to read
	 * @return The contents of the file
	 * @throws IOException If the file could not be opened
	 */
	private String openFile(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		StringBuffer sb = new StringBuffer();
		String s = null;
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		return sb.toString();
	}	
	
}
