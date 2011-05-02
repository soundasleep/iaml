/**
 * 
 */
package org.openiaml.simplegmf.codegen;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * A simple static class to help loading model instances.
 * Copied from org.openiaml.model.
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
	 * Load the EMF model in the given file.
	 * 
	 * @param f the file to load
	 * @return
	 * @throws ModelLoadException if the resource is null, or the resource contains too many elements
	 */
	public static EObject load(File f) throws ModelLoadException {
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(f.getAbsolutePath());
		Resource resource = resourceSet.getResource(uri, true);
		
		if (resource == null)
			throw new ModelLoadException(new NullPointerException("Unexpected null resource in '" + f + "'"));
		
		if (resource.getContents().size() != 1) {
			throw new ModelLoadException(new RuntimeException("Unexpected number of contents in the file '" + f + "': " + resource.getContents().size())); 
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
	
	/**
	 * Load the EMF model in the given URI.
	 * 
	 * @param uri
	 * @return
	 * @throws ModelLoadException if the resource is null, or the resource contains too many elements
	 */
	public static EObject load(URI uri) throws ModelLoadException {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(uri, true);
		
		if (resource == null)
			throw new ModelLoadException(new NullPointerException("Unexpected null resource in '" + uri + "'"));
		
		if (resource.getContents().size() != 1) {
			throw new ModelLoadException(new RuntimeException("Unexpected number of contents in the file '" + uri + "': " + resource.getContents().size())); 
		}
		
		return resource.getContents().get(0);
		
	}

	/**
	 * Get the default save options passed to EMF.
	 * 
	 * @return
	 */
	public static Map<?, ?> getSaveOptions() {
		return new HashMap<Object, Object>();
	}
	
}
