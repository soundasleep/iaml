/**
 * 
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;

/**
 * @author jmwright
 *
 */
public class ModelSourceResolver {

	private String absolutePathRoot;
	
	/**
	 * Cannot be called by clients.
	 */
	private ModelSourceResolver() {
		// empty
		
		// initialise
		absolutePathRoot = getAbsolutePathRoot();
	}
	
	/**
	 * Automatically find the model file (.iaml) for the given class.
	 * 
	 * @param class1
	 * @return
	 */
	public String getModelFileForClass(Class<?> class1) {
		// check that the resolved path actually exists
		File f = new File(getAbsolutePathRoot());
		if (!f.exists())
			throw new IllegalArgumentException("Resolved absolute path '" + getAbsolutePathRoot() + "' does not exist");
		if (!f.isDirectory())
			throw new IllegalArgumentException("Resolved absolute path '" + getAbsolutePathRoot() + "' is not a directory");

		return getAbsolutePathRoot() + getModelPathForPackage(class1.getPackage().getName()) + class1.getSimpleName() + ".iaml";
		
	}

	/**
	 * Get the absolute path root of the testing plugin in the
	 * current filesystem.
	 * 
	 * @return
	 */
	public String getAbsolutePathRoot() {
		if (absolutePathRoot == null) {
			try {
				absolutePathRoot = FileLocator.resolve(Platform.getBundle("org.openiaml.model.tests").getEntry("/")).getPath();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		return absolutePathRoot;
	}

	private static ModelSourceResolver instance;
	
	public static ModelSourceResolver getInstance() {
		if (instance == null) {
			instance = new ModelSourceResolver();
		}
		return instance;
	}

	/**
	 * Automatically find the path for the given fully qualified package name,
	 * relative to the root of the testing bundle.
	 * 
	 * @param pkg the package, e.g. <code>org.openiaml.model.tests.codegen.model0_1</code>
	 * @return the resulting path, ending with '/'
	 */
	public String getModelPathForPackage(String pkg) {
		String[] bits = pkg.split("\\.");
		if (bits.length < 2) {
			return ModelTestCase.ROOT + "inference/";
		}
		
		// xxx.inference.model0_1
		// xxx.codegen.model0_1
		if (bits[bits.length - 2].equals("inference") || bits[bits.length - 2].equals("codegen")) {		
			return ModelTestCase.ROOT + bits[bits.length - 2] + "/" + bits[bits.length - 1] + "/";
		}

		// xxx.inference
		// xxx.codegen
		if (bits[bits.length - 1].equals("inference") || bits[bits.length - 1].equals("codegen")) {		
			return ModelTestCase.ROOT + bits[bits.length - 1] + "/";
		}

		throw new IllegalArgumentException("Unexpected package: " + pkg);
	}
	
	
}
