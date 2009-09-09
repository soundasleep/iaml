/**
 * 
 */
package org.openiaml.model.tests.eclipse.actions;

import org.openiaml.model.tests.eclipse.AbstractModelEclipseTestCase;

/**
 * Sets up and tears down actions test cases.
 * 
 * @author jmwright
 *
 */
public abstract class AbstractActionTestCase<T> extends AbstractModelEclipseTestCase<T> {

	@Override
	public String getRoot() {
		return "src/org/openiaml/model/tests/eclipse/actions/";
	}

}
