/**
 * 
 */
package org.openiaml.model.codegen.php;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.openarchitectureware.xpand2.output.FileHandle;
import org.openiaml.iacleaner.CleanerException;
import org.openiaml.iacleaner.IACleaner;
import org.openiaml.iacleaner.IAInlineCleaner;

/**
 * @author Jevon
 *
 */
public class IACleanerBeautifier implements org.openarchitectureware.xpand2.output.PostProcessor {

	private static boolean tracingEnabled = false;
	
	private static List<File> tracingCache = null;
	
	private static IProgressMonitor monitor = null;

	public static IProgressMonitor getMonitor() {
		return monitor;
	}

	public static void setMonitor(IProgressMonitor monitor) {
		IACleanerBeautifier.monitor = monitor;
	}

	/**
	 * When tracing is enabled, all written files will be added
	 * to a local tracing cache. This could help with caching
	 * the OAW generation results.
	 * 
	 * This method also resets the cache: {@link #tracingReset()}.
	 * 
	 * @param tracing
	 */
	public static void setTracingEnabled(boolean tracing) {
		tracingEnabled = tracing;
		tracingReset();
	}
	
	/**
	 * Reset the tracing cache. This must be called before any 
	 * tracing cache can be done.
	 */
	public static void tracingReset() {
		tracingCache = new ArrayList<File>();
	}
	
	
	public static List<File> getTracingCache() {
		return tracingCache;
	}
	
	/**
	 * Should we make a backup of the initial file in '.old'?
	 * 
	 * @return
	 */
	public boolean makeBackup() {
		return false;
	}

	/**
	 * We use the IACleaner to format the file.
	 * 
	 * @see org.openarchitectureware.xpand2.output.PostProcessor#afterClose(org.openarchitectureware.xpand2.output.FileHandle)
	 * @throws RuntimeException if an IOException or CleanerException occurs while trying to format the file
	 */
	@Override
	public void afterClose(FileHandle file) {
		try {
			// early bail?
			if (monitor != null && monitor.isCanceled())
				throw new OperationCanceledException();
			
			// make backup?
			if (makeBackup()) {
				File backup = new File( file.getTargetFile().getAbsolutePath() + ".old" );
				FileWriter fw = new FileWriter( backup );
				fw.write( IAInlineCleaner.readFile(file.getTargetFile()) );
				fw.close();
			}
			
			IACleaner cleaner = new IAInlineCleaner();
			String out = cleaner.cleanScript( file.getTargetFile() );
			
			// rewrite the file
			FileWriter fw2 = new FileWriter(file.getTargetFile());
			fw2.write(out);
			fw2.close();
			
			/*
			// chain onto output
			OutputInstrumentation oi = new OutputInstrumentation();
			try {
				oi.instrumentFile(file);
			} catch (InstrumentationException e) {
				throw new CleanerException(e);
			}
			*/
			
			// tracing enabled?
			if (tracingEnabled) {
				tracingCache.add(file.getTargetFile());
			}
			
		} catch (IOException e) {
			throw new RuntimeException("[" + file.getTargetFile() + "] IO Exception during prettifier: " + e.getMessage(), e);
		} catch (CleanerException e) {
			throw new RuntimeException("[" + file.getTargetFile() + "] Cleaner Exception during prettifier: " + e.getMessage(), e);
		} finally {
			// reset the monitor
			monitor = null;
		}
		
	}

	/* (non-Javadoc)
	 * @see org.openarchitectureware.xpand2.output.PostProcessor#beforeWriteAndClose(org.openarchitectureware.xpand2.output.FileHandle)
	 */
	@Override
	public void beforeWriteAndClose(FileHandle file) {
		// ignore	
	}

	/**
	 * Is tracing currently enabled?
	 * 
	 * @return
	 */
	public static boolean isTracingEnabled() {
		return tracingEnabled;
	}

}
