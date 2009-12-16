/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.util.ArrayList;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.openiaml.model.tests.CodegenTestCase;

/** 
 * If an exception is thrown in the code generation templates,
 * we should catch it.
 * 
 * @author jmwright
 *
 */
public class TestProgressMonitor extends CodegenTestCase {

	/**
	 * Make sure that when an exception is thrown, operation halts. 
	 */
	public void testDisplaysProgress() throws Exception {
		final List<String> subtasks = new ArrayList<String>();
		
		// set up a special monitor
		monitor = new NullProgressMonitor() {
			@Override
			public void subTask(String name) {
				super.subTask(name);
				subtasks.add(name);
			}
		};
		
		// do codegen
		root = loadAndCodegen(TestProgressMonitor.class);
		
		// check for all these subtasks
		String[] wanted = {
				"Loading rulebase",
				// cannot test these, because they are done by a SubProgressMonitor
				// "Creating file visual_120b22b5aea_5b.php..."
			};
		
		try {
			for (String w : wanted) {
				assertTrue("Subtask '" + w + "' wasn't called by the progress monitor", subtasks.contains(w));
			}
		} catch (AssertionFailedError e) {
			// show all subtasks that were called
			for (String f : subtasks) {
				System.out.println(f);
				throw e;
			}
		}
		
	}

}
