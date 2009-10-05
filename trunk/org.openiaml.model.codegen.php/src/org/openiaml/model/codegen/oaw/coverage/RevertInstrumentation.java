/**
 * 
 */
package org.openiaml.model.codegen.oaw.coverage;

import java.io.File;

/**
 * Manually instrument all of the OAW code.
 * Reverses the operation in {@link RunInstrumentation}.
 * 
 * @author jmwright
 *
 */
public class RevertInstrumentation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File dir = new File("src/template");
		InstrumentOawCode inst = new InstrumentOawCode();
		
		try {
			inst.postInstrumentTemplates(dir);
		} catch (InstrumentationException e) {
			e.printStackTrace();
		}
		
		System.out.println("Instrumentation reverted.");
	}

}
