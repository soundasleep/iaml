/**
 * 
 */
package org.openiaml.model.impl;

import java.io.File;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.FileReference;
import org.openiaml.model.model.domain.DomainSource;

/**
 * @author jmwright
 *
 */
public class FileReferenceImpl implements FileReference {

	private String fileName;
	
	/**
	 * @param filename
	 */
	public FileReferenceImpl(Object fileName) {
		if (!(fileName instanceof String || fileName == null)) {
			throw new IllegalArgumentException(fileName + " is not a String (" + fileName.getClass() + ")");
		}
		this.fileName = (String) fileName;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.FileReference#serialize()
	 */
	@Override
	public String toString() {
		return fileName;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.FileReference#toFile(org.eclipse.emf.common.util.URI)
	 */
	@Override
	public File toFile(URI absolutePath) {
		return resolveFilePath(absolutePath, this.fileName);
	}
	
	/**
	 * Resolve a given file name to a given relative URI path.
	 * 
	 * @param absolutePath
	 * @param fileName
	 * @return the resolved File
	 */
	public static File resolveFilePath(URI absolutePath, String fileName) {
		if (absolutePath.isRelative()) {
			throw new IllegalArgumentException("Absolute path '" + absolutePath + "' cannot be relative.");
		}
		URI self = URI.createURI(fileName);
		URI resolved;
		try {
			resolved = self.resolve(absolutePath);
		} catch (IllegalArgumentException e) {
			// a more useful error message
			throw new IllegalArgumentException("Failed resolving '" + fileName + "' to relative path '" + absolutePath + "': " + e.getMessage(), e);
		}
		URI tofs = CommonPlugin.resolve(resolved);
		return new File(tofs.toFileString());
	}
	
	/**
	 * Get the internal file name associated with this FileReference.
	 * 
	 * @return
	 */
	protected String getFileName() {
		return fileName;
	}

	/**
	 * We need to override equals() to compare FileReferences.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FileReferenceImpl) {
			return getFileName().equals(((FileReferenceImpl) obj).getFileName());
		} else {
			return false;
		}
	}

	/**
	 * Since we override {@link #equals(Object)}, we need to also
	 * override hashCode.
	 */
	@Override
	public int hashCode() {
		return fileName.hashCode();
	}

	
	/**
	 * Resolve the FileReference in the given object against the
	 * containing EResource of the object, and return a path as a string.
	 * 
	 * @param obj the DomainSource to resolve
	 * @return <code>null</code> if <code>obj.file</code> is null or not set
	 */
	public static String resolveFileReference(DomainSource obj) {
		if (obj.getFile() == null || !(obj.getFile() instanceof FileReferenceImpl))
			return null;
		
		return ((FileReferenceImpl) obj.getFile()).getFileName();
	}

}
