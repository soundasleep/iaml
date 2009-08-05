/**
 * 
 */
package org.openiaml.model.codegen.oaw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openarchitectureware.xpand2.output.FileHandle;
import org.openiaml.iacleaner.CleanerException;
import org.openiaml.iacleaner.IAInlineCleaner;
import org.openiaml.model.codegen.oaw.coverage.InstrumentationException;
import org.openiaml.model.codegen.oaw.coverage.OutputInstrumentation;

/**
 * @author Jevon
 *
 */
public class IACleanerBeautifier implements org.openarchitectureware.xpand2.output.PostProcessor {

	/**
	 * We use the IACleaner to format the file.
	 * 
	 * @see org.openarchitectureware.xpand2.output.PostProcessor#afterClose(org.openarchitectureware.xpand2.output.FileHandle)
	 * @throws RuntimeException if an IOException or CleanerException occurs while trying to format the file
	 */
	@Override
	public void afterClose(FileHandle file) {
		try {
			// make backup
			{
				File backup = new File( file.getTargetFile().getAbsolutePath() + ".old" );
				FileWriter fw = new FileWriter( backup );
				fw.write( IAInlineCleaner.readFile(file.getTargetFile()) );
				fw.close();
			}
			
			
			/*
			IACleaner cleaner = new IAInlineCleaner();
			String out = cleaner.cleanScript( file.getTargetFile() );
			
			// rewrite the file
			FileWriter fw2 = new FileWriter(file.getTargetFile());
			fw2.write(out);
			fw2.close();
			*/
			
			// chain onto output
			OutputInstrumentation oi = new OutputInstrumentation();
			try {
				oi.instrumentFile(file);
			} catch (InstrumentationException e) {
				throw new CleanerException(e);
			}
			
		} catch (IOException e) {
			throw new RuntimeException("[" + file.getTargetFile() + "] IO Exception during prettifier: " + e.getMessage(), e);
		} catch (CleanerException e) {
			throw new RuntimeException("[" + file.getTargetFile() + "] Cleaner Exception during prettifier: " + e.getMessage(), e);
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
