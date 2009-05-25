/**
 * 
 */
package org.openiaml.model.codegen.oaw.coverage;

import java.io.File;
import java.io.IOException;

/**
 * The aim of this method is to take our template files and
 * the results of the instrumentation, and generate a pretty format
 * of the code coverage for HTML.
 * 
 * @author jmwright
 *
 */
public class OutputCoveredCode {

	/**
	 * @param args
	 * @throws InstrumentationException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InstrumentationException, IOException {
		File oawDump = new File("instrument/instrumented.dump");
		File phpDump = new File("instrument/php-instrumented.dump");
		File jsDump = new File("instrument/javascript-instrumented.dump");
		File templatesDir = new File("src/template");
		File outputDir = new File("instrument/output");

		InstrumentOawCode ia = new InstrumentOawCode();
		ia.outputCoveredCode(oawDump, phpDump, jsDump, templatesDir, outputDir);
	}

}
