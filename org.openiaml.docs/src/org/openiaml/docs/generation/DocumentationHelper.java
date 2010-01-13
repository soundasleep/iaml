/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.openarchitectureware.expression.ast.DeclaredParameter;
import org.openarchitectureware.expression.ast.Identifier;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.ModelDocumentation;

/**
 * Abstract helper methods for generating documentation.
 * 
 * @author jmwright
 *
 */
public abstract class DocumentationHelper implements ILoader {
	
	/**
	 * Find an existing EMFClass for the given name.
	 * Does not create a new one.
	 * 
	 * @param o
	 * @return the class found, or null
	 */
	public EMFClass getEMFClassFor(ModelDocumentation root, String name) {
		for (EMFClass cls : root.getClasses()) {
			if (cls.getTargetClass().getName().equals(name)) {
				// found it
				return cls;
			}
			
		}
		
		return null;
	}
	
	/**
	 * Find an existing EMFClass for the given class.
	 * Does not create a new one.
	 * 
	 * @param o
	 * @return the class found, or null
	 */
	public EMFClass getEMFClassFor(ModelDocumentation root, EClass c) {
		for (EMFClass cls : root.getClasses()) {
			if (cls.getTargetClass().equals(c)) {
				// found it
				return cls;
			}
			
		}
		
		return null;
	}

	/**
	 * @param root
	 * @param declaredParameter
	 * @return
	 */
	protected EMFClass mapOAWType(ModelDocumentation root, DeclaredParameter declaredParameter) {
		return mapOAWIdentifier(root, declaredParameter.getType());
	}

	/**
	 * Map the Identifier (the context) to an EMFClass in the root.
	 * 
	 * @param type
	 * @return
	 */
	protected EMFClass mapOAWIdentifier(ModelDocumentation root, Identifier type) {
		for (EMFClass cls : root.getClasses()) {
			if (identifierMatches(type, cls.getTargetClass())) {
				return cls;
			}
		}
		
		// could not find
		System.err.println("Could not identify type '" + type + "'");
		return null;
	}

	/**
	 * @param type
	 * @param cls
	 * @return
	 */
	public boolean identifierMatches(Identifier type, EClass ref) {
		String[] bits = type.getValue().split("::");
		
		if (ref.getName().equals(bits[bits.length - 1])) {
			// same name
			EPackage currentPackage = ref.getEPackage();
			for (int i = bits.length - 2; i >= 0; i--) {
				if (currentPackage == null) {
					// bail 
					return false;
				}
				if (!currentPackage.getName().equals(bits[i])) {
					// bail
					return false;
				}
				currentPackage = currentPackage.getESuperPackage();
			}
			
			// we should be at the root of the package heirarchy
			if (currentPackage != null)
				return false;
			
			// everything matches!
			return true;
		}
		
		return false;
	}
	
	/**
	 * Converts <code>/foo/bar/bar.drl</code> into <code>foo.baz</code>
	 * and <code>baz</code>.
	 * 
	 * @param file
	 * @return
	 */
	protected String[] getSimpleFilename(String file) {
		
		String sep = "/";
		file = file.substring(0, file.lastIndexOf("."));
		String pkg = file.substring(0, file.lastIndexOf(sep));
		// remove leading /'s
		while (pkg.startsWith(sep)) {
			pkg = pkg.substring(1);
		}
		pkg = pkg.replace(sep, ".");
		
		String name = file.substring(file.lastIndexOf(sep) + 1);

		return new String[] { pkg, name };
		
	}

	
	/**
	 * Load a file completely into an array of chars.
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
	protected char[] readFile(File f) throws IOException {
		
		final int SIZE = 1024;
		StringBuffer buf = new StringBuffer();
		
		FileReader fr = new FileReader(f);
		while (true) {
			CharBuffer buffer = CharBuffer.allocate(SIZE);
			int len = fr.read(buffer);
			if (len == -1)
				break;
			buf.append(buffer.array(), 0, len);
		}
		fr.close();
		return buf.toString().toCharArray();

	}
	
	/**
	 * Is the given filename an image?
	 * 
	 * @param filename
	 * @return
	 */
	public boolean isImage(String filename) {
		String f = filename.toLowerCase();
		return f.endsWith(".png") || f.endsWith(".gif") || f.endsWith(".jpg")
			|| f.endsWith(".ico");
	}
	
}
