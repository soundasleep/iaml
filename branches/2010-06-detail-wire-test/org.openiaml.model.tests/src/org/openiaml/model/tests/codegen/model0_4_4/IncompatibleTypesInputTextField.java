/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_4;

import org.eclipse.core.runtime.IStatus;
import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Checks that if we have an {@model InputTextField} with a different type
 * to its contained {@model Property} fieldValue, an exception is thrown.
 * 
 * @author jmwright
 *
 */
public class IncompatibleTypesInputTextField extends CodegenTestCase {
	
	public void testExceptionInvestgation() throws Exception {
		TransformationException ex = null;
		try {
			root = loadAndCodegen(IncompatibleTypesInputTextField.class);
		} catch (TransformationException e) {
			// pass
			ex = e;
		}
		assertNotNull("An exception should have been thrown.", ex);

		assertNotNull(ex.getChildren());
		assertNotEqual(0, ex.getChildren().length);
		boolean found = false;
		for (IStatus s : ex.getChildren()) {
			if (s.getMessage().startsWith("Type '" + BuiltinDataTypes.TYPE_INTEGER + "' is incompatible with contained fieldValue type '" + BuiltinDataTypes.TYPE_EMAIL + "'")) {
				found = true;
			}
		}
		assertTrue("Could not find expected status message", found);
		
	}

}
