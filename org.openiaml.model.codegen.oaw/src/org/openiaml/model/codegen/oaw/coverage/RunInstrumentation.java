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

		System.out.println("Instrumentation applied.");
	}

}
