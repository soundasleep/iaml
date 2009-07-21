/**
 * 
 */
package org.openiaml.model.codegen.oaw.coverage;

import java.io.File;

/**
 * Manually instrument all of the OAW code.
 * 
 * @author jmwright
 *
 */
public class RunInstrumentation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File dir = new File("src/template");
		File dest = new File("instrument/");
		dest.mkdir();
		InstrumentOawCode inst = new InstrumentOawCode();
		
		try {
			inst.preInstrumentTemplates(dir, dest);
		} catch (InstrumentationException e) {
			e.printStackTrace();
		}
		
		// we also need to delete the instrumentation files that
		// already exist
		for (File f : new File[]{ 
				new File("instrument/instrumented.dump"),
				new File("instrument/php-instrumented.dump"),
				new File("instrument/php-instrumented.dump.raw"),
				new File("instrument/javascript-instrumented.dump"),
				new File("instrument/javascript-instrumented.dump.raw")}) {
			if (f.exists()) {
				System.out.println("Deleting '" + f + "'...");
				boolean deleted = f.delete();
				if (!deleted) {
					throw new RuntimeException("Could not delete '" + f + "'");
				}
			}
		}

		System.out.println("Instrumentation applied.");
	}

}
