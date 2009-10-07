/**
 * 
 */
package org.openiaml.model.owl.actions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openiaml.model.custom.actions.ProgressEnabledAction;
import org.openiaml.model.owl.rdf.EcoreRDFWriter;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author jmwright
 *
 */
public class TranslateIAMLToRDFAction extends ProgressEnabledAction<IFile> {

	private String filename;

	/**
	 * Load a model file directly.
	 * Assumes that it will only contain one element (and tests this with JUnit).
	 */
	protected EObject loadModelDirectly(String filename) {
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(filename);
		org.eclipse.emf.ecore.resource.Resource resource = resourceSet.getResource(uri, true);
		if (resource == null) {
			throw new NullPointerException("Could not load resource '" + filename + "': loaded null");
		}
		if (resource.getContents().size() != 1) {
			throw new RuntimeException("There should only be one contents in the model file, found: " + resource.getContents().size());
		}
		return resource.getContents().get(0);
	}

	/**
	 * Translate '/var/foo.iaml' into '/var/foo.rdf'. 
	 * Essentially ignores the final file extension.
	 * 
	 * @param filename
	 * @return
	 */
	public String getRDFFilename(String filename) {
		if (filename.indexOf(".") == -1) {
			throw new RuntimeException("Filename '" + filename + "' should end in .xxx");
		}
		return filename.substring(0, filename.lastIndexOf(".")) + ".rdf";
	}
	
	/**
	 * Do the actual translation from EObject to RDF.
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public IStatus doTranslate(IFile file) throws FileNotFoundException {
		
		// load the .iaml file
		EObject iaml = loadModelDirectly(file.getLocation().toString());
		
		// create a writer
		EcoreRDFWriter writer = new EcoreRDFWriter();
		Model model = writer.transform(iaml);
		
		// save to .rdf
		filename = getRDFFilename(file.getLocation().toString());

		OutputStream pipe = new FileOutputStream(filename);
		model.write(pipe);
		
		return Status.OK_STATUS;
		
	}
	
	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#execute(java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus execute(IFile individual, IProgressMonitor monitor) {
		try {
			IStatus result = doTranslate(individual);
			
			if (result.isOK()) {
				showInformation("RDF Translation", "RDF translation successful: " + filename);
			}
			
			return result;
		} catch (FileNotFoundException e) {
			return errorStatus(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IFile individual, String message) {
		return "Could not translate '" + individual + "' to RDF: " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Translating IAML instance to RDF";
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getSelection(java.lang.Object[])
	 */
	@Override
	public List<IFile> getSelection(Object[] selection) {
		final List<IFile> ifiles = new ArrayList<IFile>();
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					ifiles.add((IFile) o);
				}
			}
		}
		
		return ifiles;
	}

}
