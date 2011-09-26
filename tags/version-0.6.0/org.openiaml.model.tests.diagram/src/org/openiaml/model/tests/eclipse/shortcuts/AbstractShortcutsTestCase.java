/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.eclipse.AbstractModelEclipseTestCase;

/**
 * Sets up and tears down shortcut test cases.
 * 
 * @author jmwright
 *
 */
public abstract class AbstractShortcutsTestCase extends AbstractModelEclipseTestCase<IFile> {

	@Override
	public String getRoot() {
		return "src/org/openiaml/model/tests/eclipse/shortcuts/";
	}
	
}
