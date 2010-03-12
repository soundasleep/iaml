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

		if (class1.getPackage().getName().contains("codegen.functions")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "codegen/functions/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.model0_1")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "codegen/model0_1/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.model0_2")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "codegen/model0_2/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.model0_3")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "codegen/model0_3/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.model0_4_1")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "codegen/model0_4_1/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.model0_4_2")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "codegen/model0_4_2/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.model0_4_3")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "codegen/model0_4_3/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.model0_4_4")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "codegen/model0_4_4/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.model0_4")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "codegen/model0_4/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("codegen.runtime")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "codegen/runtime/" + class1.getSimpleName() + ".iaml";
		}
		
		// TODO move other inference tests into separate test folders
		if (class1.getPackage().getName().contains("inference.model0_3")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "inference/model0_3/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("inference.model0_4_1")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "inference/model0_4_1/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("inference.model0_4_2")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "inference/model0_4_2/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("inference.model0_4_3")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "inference/model0_4_3/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("inference.model0_4_4")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "inference/model0_4_4/" + class1.getSimpleName() + ".iaml";
		}
		if (class1.getPackage().getName().contains("inference.model0_4")) {
			return getAbsolutePathRoot() + ModelTestCase.ROOT + "inference/model0_4/" + class1.getSimpleName() + ".iaml";
		}
		
		return getAbsolutePathRoot() + ModelTestCase.ROOT + "inference/" + class1.getSimpleName() + ".iaml";

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
	
	
}
