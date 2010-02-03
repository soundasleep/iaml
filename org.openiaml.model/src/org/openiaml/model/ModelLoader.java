/**
 * 
 */
package org.openiaml.model;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * A simple static class to help loading model instances.
 * 
 * @author jmwright
 *
 */
public class ModelLoader {
	
	/**
	 * Wraps uncaught exceptions with a caught exception.
	 * 
	 */
	public static class ModelLoadException extends Exception {

		private static final long serialVersionUID = 1L;
		
		public ModelLoadException(String message) {
			super(message);
		}
		
		public ModelLoadException(Throwable e) {
			super(e);
		}
		
		public ModelLoadException(String message, Throwable e) {
			super(message, e);
		}
		
	}
	
	/**
	 * Load the EMF model in the given filename.
	 * 
	 * @param filename the filename to load
	 * @return
	 * @throws ModelLoadException if the resource is null, or the resource contains too many elements
	 */
	public static EObject load(String filename) throws ModelLoadException {
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(filename);
		Resource resource = resourceSet.getResource(uri, true);
		
		if (resource == null)
			throw new ModelLoadException(new NullPointerException("Unexpected null resource in '" + filename + "'"));
		
		if (resource.getContents().size() != 1) {
			throw new ModelLoadException(new RuntimeException("Unexpected number of contents in the file '" + filename + "': " + resource.getContents().size())); 
		}
		
		return resource.getContents().get(0);
	}

	/**
	 * Load the EMF model in the given IFile.
	 * 
	 * @param o
	 * @return
	 * @throws ModelLoadException if the resource is null, or the resource contains too many elements
	 */
	public static EObject load(IFile o) throws ModelLoadException {
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(o.getLocation().toString());
		Resource resource = resourceSet.getResource(uri, true);
		
		if (resource == null)
			throw new ModelLoadException(new NullPointerException("Unexpected null resource in '" + o + "'"));
		
		if (resource.getContents().size() != 1) {
			throw new ModelLoadException(new RuntimeException("Unexpected number of contents in the file '" + o + "': " + resource.getContents().size())); 
		}
		
		return resource.getContents().get(0);
		
	}
	
}
