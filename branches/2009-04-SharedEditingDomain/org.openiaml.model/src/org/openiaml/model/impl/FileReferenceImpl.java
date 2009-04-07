/**
 * 
 */
package org.openiaml.model.impl;

import java.io.File;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.openiaml.model.FileReference;

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
	public File toFile(URI relativePath) {
		URI self = URI.createURI(fileName);
		URI resolved = self.resolve(relativePath);
		URI tofs = CommonPlugin.resolve(resolved);
		return new File(tofs.toFileString());
	}

}
