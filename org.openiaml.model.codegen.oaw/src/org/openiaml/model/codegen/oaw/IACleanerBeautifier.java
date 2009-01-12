/**
 * 
 */
package org.openiaml.model.codegen.oaw;

import java.io.FileWriter;
import java.io.IOException;

import org.openarchitectureware.xpand2.output.FileHandle;
import org.openiaml.iacleaner.IACleaner;
import org.openiaml.iacleaner.IACleaner.CleanerException;

/**
 * @author Jevon
 *
 */
public class IACleanerBeautifier implements org.openarchitectureware.xpand2.output.PostProcessor {

	/**
	 * We use the IACleaner to format the file.
	 * 
	 * @see org.openarchitectureware.xpand2.output.PostProcessor#afterClose(org.openarchitectureware.xpand2.output.FileHandle)
	 */
	@Override
	public void afterClose(FileHandle file) {
		try {
			IACleaner cleaner = new IACleaner();
			String out = cleaner.cleanScript( file.getTargetFile() );
			
			// rewrite the file
			FileWriter fw = new FileWriter(file.getTargetFile());
			fw.write(out);
			fw.close();
		} catch (IOException e) {
			throw new RuntimeException("IO Exception during prettifier: " + e.getMessage(), e);
		} catch (CleanerException e) {
			throw new RuntimeException("Cleaner Exception during prettifier: " + e.getMessage(), e);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.openarchitectureware.xpand2.output.PostProcessor#beforeWriteAndClose(org.openarchitectureware.xpand2.output.FileHandle)
	 */
	@Override
	public void beforeWriteAndClose(FileHandle file) {
		// ignore	
	}

}
