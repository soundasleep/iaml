/**
 * 
 */
package org.openiaml.model.impl;

import java.io.File;

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
	 * @see org.openiaml.model.FileReference#toFile()
	 */
	@Override
	public File toFile() {
		return new File(fileName);
	}

}
