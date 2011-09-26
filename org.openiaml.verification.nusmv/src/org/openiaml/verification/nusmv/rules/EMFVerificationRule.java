/**
 * 
 */
package org.openiaml.verification.nusmv.rules;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openiaml.verification.nusmv.VerificationException;
import org.openiaml.verification.nusmv.VerificationRule;
import org.openiaml.verification.nusmv.oaw.OAWGenerator;
import org.osgi.framework.Bundle;

/**
 * Abstract class hiding the verification rule implementation for
 * an Eclipse-based rule implementation.
 * 
 * @author jmwright
 *
 */
public abstract class EMFVerificationRule implements VerificationRule {
	
	/**
	 * Get the current bundle to load files from.
	 * 
	 * @return
	 */
	public abstract Bundle getBundle();
	
	/* (non-Javadoc)
	 * @see org.openiaml.verification.nusmv.VerificationRule#exportToSMV(org.eclipse.emf.ecore.EObject, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public InputStream exportToSMV(EObject model, IProgressMonitor monitor)
			throws VerificationException {

		try {
			monitor.beginTask("Exporting model to SMV", 5);
			
			// if the output file exists, remove it
			File output1 = new File(getOutputFile());		
			if (output1.exists()) {
				monitor.subTask("Deleting existing file");
				output1.delete();
			}
			monitor.worked(1);
			
			OAWGenerator gen = new OAWGenerator();
			
			// first save the model to file
			File modelFile;
			try {
				monitor.subTask("Exporting model to XMI");
				modelFile = saveToFile(model, "test.iaml");
			} catch (IOException e1) {
				throw new VerificationException(e1);
			}
			monitor.worked(1);
			
			// now generate it
			monitor.subTask("Generating SMV template using OAW");
			IStatus status = gen.generateCode(modelFile, getWorkflowFile());
			if (!status.isOK()) {
				throw new VerificationException("Generation was not successful: " + status.getMessage(), status.getException());
			}
			monitor.worked(2);
			
			// load the file we wrote
			monitor.subTask("Loading generated template");
			File output = new File(getOutputFile());		
			if (!output.exists()) {
				throw new VerificationException("File '" + output + "' does not exist.");
			}
			
			// open it as an input stream
			try {
				return new BufferedInputStream(new FileInputStream(output));
			} catch (FileNotFoundException e) {
				throw new VerificationException(e);
			}
			
		} finally {
			monitor.done();
		}
		
	}
	
	/**
	 * Save the given EObject to the target filename.
	 * 
	 * @param model
	 * @param filename the filename to save to, e.g. <code>foo.iaml</code>
	 * @return
	 * @throws IOException 
	 */
	protected File saveToFile(EObject model, String filename) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		File modelFile = new File(filename);
        URI fileURI = URI.createFileURI(modelFile
                .getAbsolutePath());
        Resource resource = resourceSet.createResource(fileURI);
        resource.getContents().add(model);
        resource.save(Collections.EMPTY_MAP);
        
        return modelFile;
	}

	/**
	 * Get the workflow file, e.g. <code>src/workflow/loop.oaw</code>
	 * 
	 * @return
	 */
	public abstract String getWorkflowFile();

	/**
	 * Get the expected generated output file, e.g. <code>output/check.smv</code>
	 * 
	 * @return
	 */
	public abstract String getOutputFile();

	/**
	 * Get the given file in our current bundle. 
	 * 
	 * @see #getBundle()
	 * @param filename
	 * @return
	 * @throws IOException 
	 */
	public URL getResolvedFile(String filename) throws IOException {
		URL file = getBundle().getEntry(filename);
		
		if (file == null)
			throw new NullPointerException("Could not resolve filename '" + filename + "' in bundle '" + getBundle() + "'");
		
		return FileLocator.resolve(file);
	}

}
