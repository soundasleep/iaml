/**
 * 
 */
package org.openiaml.docs.generation.codegen;

import java.io.FileWriter;
import java.io.IOException;

import org.openarchitectureware.xpand2.output.FileHandle;
import org.openiaml.model.tests.XmlTestCase;

/**
 * Extends {@link IACleanerBeautifier} to apply some
 * style changes to generated LaTeX code. In particular,
 * <code>foo- \command</code> should be cleaned up to
 * <code>foo-\command</code>.
 * 
 * @author jmwright
 *
 */
public class IACleanerBeautifierWithLaTeX extends IACleanerBeautifier {

	/* (non-Javadoc)
	 * @see org.openiaml.docs.generation.codegen.IACleanerBeautifier#afterClose(org.openarchitectureware.xpand2.output.FileHandle)
	 */
	@Override
	public void afterClose(FileHandle file) {
		// clean it up with the normal cleaner
		super.afterClose(file);
		
		// if this is a .tex file
		if (file.getTargetFile().getName().toLowerCase().endsWith(".tex")) {
			try {
				// read it
				String input = XmlTestCase.readFile(file.getTargetFile());
				
				// replace all instances
				String output = input.replace("- \\", "-\\");
				
				// rewrite the file
				FileWriter fw2 = new FileWriter(file.getTargetFile());
				fw2.write(output);
				fw2.close();
			} catch (IOException e) {
				throw new RuntimeException("[" + file.getTargetFile() + "] IO Exception during LaTeX cleanup: " + e.getMessage(), e);
			}
		}
	}

}
