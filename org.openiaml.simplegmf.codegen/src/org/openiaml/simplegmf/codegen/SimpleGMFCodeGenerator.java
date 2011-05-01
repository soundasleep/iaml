/**
 * 
 */
package org.openiaml.simplegmf.codegen;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.util.ProgressMonitorAdapter;

/**
 * Implements code generation for SimpleGMF models.
 * 
 * @author jmwright
 *
 */
public class SimpleGMFCodeGenerator {
	
	public static final String PLUGIN_ID = "org.openiaml.docs"; 
	
	/**
	 * Generate code for a given model file into a given output directory.
	 * Does NOT deal with inference.
	 * 
	 */
	public IStatus generateCode(File file) {
		
		// reset exception-key map
		resetKeyToExceptionMap();
		
		/*
		 * We have to do some magic to enable OAW logging to go through
		 * our own class. We have to provide this information to 
		 * commons.logging directly.
		 * 
		 * Based on http://oaw-forum.itemis.de/forum/viewtopic.php?forum=1&showtopic=1486 (german)
		 */
		/*
		 * This is disabled because log4j can not support more than one
		 * ClassLoader at the same time, so it conflicts with the IAML classlaoder.
		 */
		//ClassLoader oldcl = Thread.currentThread().getContextClassLoader();
		
		try {
			// to enable custom logging
			//Thread.currentThread().setContextClassLoader(OawCodeGenerator.class.getClassLoader());
			
			String wfFile = "src/workflow/simplegmf.oaw";
			Map<String,String> properties = new HashMap<String,String>();
			properties.put("model", file.getAbsolutePath());
			properties.put("src-gen", ".");	// have to get absolute filename for output dir
			
			Map<String,Object> slotContents = new HashMap<String,Object>();

			try {
				executeWorkflow(wfFile, new NullProgressMonitor(), properties, slotContents);
			} catch (OperationCanceledException e) {
				// monitor was cancelled; OK
			} finally {
			}

			// refresh output folder
			return Status.OK_STATUS;
		} finally {
			// reset the classloader/log
			//Thread.currentThread().setContextClassLoader(oldcl);
		}
			
	}
	
	protected void executeWorkflow(String wfFile, IProgressMonitor monitor, Map<String, String> properties, Map<String, ?> slotContents) {
		new WorkflowRunner().run(wfFile,
				new ProgressMonitorAdapter(monitor), properties, slotContents);
		
	}

	/**
	 * <p>
	 * Construct a RuntimeException with the given message, and throw it.
	 * Useful in templates, as we can get a stack trace to problems, rather
	 * than using OAW's ERROR code, which only prints out text.
	 * </p>
	 * 
	 * <p>
	 * This includes a workaround for an OAW problem, where an exception thrown during
	 * workflow execution is renamed a WorkflowInterruption, and the causing exception is
	 * lost when saved to the error log. Our workaround is to serialise this exception
	 * into a special map ({@link #getExceptionForKey(String)}, which can then be unserialised
	 * by the log when it receives the error log message.
	 * </p>
	 * 
	 * <p>
	 * This workaround is saved in the thrown exception message, as "[original message] @key:[generated key]".
	 * The original exception can be retrieved through {@link #getExceptionForKey(String)}.
	 * </p>
	 * 
	 * @param message
	 */
	public static void throwException(String message) {
		Throwable e = new RuntimeException(message);
		String key = generateExceptionKey();
		keyToExceptionMap.put(key, e);
		throw new RuntimeException(e.getMessage() + " @key:" + key, e);
	}

	/**
	 * Generate a new exception key.
	 * 
	 * @return
	 */
	private static String generateExceptionKey() {
		keyToExceptionCount++;
		return "ex" + keyToExceptionCount;
	}

	public static Map<String,Throwable> keyToExceptionMap;
	public static int keyToExceptionCount;
	
	public static void resetKeyToExceptionMap() {
		keyToExceptionMap = new HashMap<String,Throwable>();
		keyToExceptionCount = 0;
	}
	
	public static Throwable getExceptionForKey(String key) {
		return keyToExceptionMap.get(key);
	}
	
	/**
	 * Get the exception for a given message. If no exception exists,
	 * null is returned.
	 * 
	 * @param message
	 * @return
	 */
	public static Throwable getExceptionForMessage(String message) {
		if (message.indexOf("@key:") < 0) {
			return null;
		}
		String key = message.substring(message.indexOf("@key:") + "@key:".length());
		// remove trailing whitespace
		if (key.indexOf(" ") >= 0) {
			key = key.substring(0, key.indexOf(" "));
		}
		if (key.indexOf("\r") >= 0) {
			key = key.substring(0, key.indexOf("\r"));
		}
		if (key.indexOf("\n") >= 0) {
			key = key.substring(0, key.indexOf("\n"));
		}
		if (key.indexOf("\t") >= 0) {
			key = key.substring(0, key.indexOf("\t"));
		}
		return keyToExceptionMap.get(key);
	}
	
}
