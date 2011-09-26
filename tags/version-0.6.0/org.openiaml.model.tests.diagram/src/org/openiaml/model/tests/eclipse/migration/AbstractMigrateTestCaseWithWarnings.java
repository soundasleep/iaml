/**
 *
 */
package org.openiaml.model.tests.eclipse.migration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.openiaml.model.tests.XmlTestCase;

/**
 * Extends {@link AbstractMigrateTestCase} to also expect a given
 * list of warnings.
 *
 * @author jmwright
 *
 */
public abstract class AbstractMigrateTestCaseWithWarnings extends AbstractMigrateTestCase {

	private IStatus resultStatus = null;
	
	/**
	 * Return a File that will contain a list of expected warnings in text format.
	 * 
	 * @return A file of expected warnings
	 */
	public abstract File getExpectedWarningsFile();
	
	/**
	 * Since 'domainStores' was removed in 0.5.1, we should see this warning
	 * message in the list of errors.
	 * 
	 * @throws Exception
	 */
	public void testExpectedWarnings() throws Exception {
		migrateModelOnly();

		// expected to be multistatus
		MultiStatus status = (MultiStatus) this.resultStatus;
		
		// get the warnings from a text file
		String[] warnings = XmlTestCase.readFile(getExpectedWarningsFile()).split("\n");
		List<String> expected = new ArrayList<String>();
		for (String w : warnings) {
			expected.add(w.trim());
		}
		
		for (IStatus i : status.getChildren()) {
			String message = i.getMessage().trim();
			
			// does it exist?
			if (!expected.contains(message)) {
				for (IStatus w : status.getChildren()) {
					System.err.println(w.getMessage());
				}
			}
			assertTrue("Does not contain message '" + message + "': " + expected, expected.contains(message));

			// remove it from the list of messages
			expected.remove(message);
		}
		
		// there should be no messages left
		if (!expected.isEmpty()) {
			for (String w : expected) {
				System.err.println(w);
			}
		}
		assertEquals("Unexpected warnings remain: " + expected, 0, expected.size());
		
	}
	/**
	 * We actually expect there to be some warnings.
	 */
	@Override
	public void assertStatusOK(IStatus status) throws Exception {
		resultStatus = status;
		if (status.getSeverity() == IStatus.WARNING && status instanceof MultiStatus) {
			return;
		}
		// if not a multi-warning status, continue
		super.assertStatusOK(status);
	}	
	
}
