/**
 * 
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openiaml.model.codegen.php.CustomOAWLog.LogExtender;

/**
 * @author jmwright
 *
 */
public class TimedOAWLogExtender implements LogExtender {
	
	private boolean started = false;
	private String lastKey = "(new log)";
	private long lastKeyTime = System.currentTimeMillis();
	
	private File file; // the file to write to

	/**
	 * @param timedLogFile
	 */
	public TimedOAWLogExtender(String timedLogFile) {
		this.file = new File(timedLogFile);
		
		// write out that we have started a new extender
		writeToFile("(log loaded)", 0);
	}

	/**
	 * Handles OAW logging.
	 */
	@Override
	public void log(int type, Object obj, Throwable t) {
		String message = obj.toString();
		if (message.contains("workflow completed in")) {
			// completed
			started = false;
		} else if (message.contains("running workflow:")) {
			// starting
			started = true;
		}
		
		if (started) {
			// get the key of the message
			// format: 'Workflow - ...'
			String key = calculateKey(message);
			
			if (key != null) {	
				logDirect(key);
				
			}
		}
	}
	
	/**
	 * Log the given key directly.
	 */
	public void logDirect(String key) {
		if (key.equals(lastKey)) {
			// ignore
		} else {
			// the key has changed - mark down the current key time
			// and write it to file
			long newKeyTime = System.currentTimeMillis();
			
			writeToFile(lastKey, newKeyTime - lastKeyTime);
			
			lastKey = key;
			lastKeyTime = newKeyTime;
		}
		
	}
	
	/**
	 * Calculate the key from the message, or null.
	 * 
	 * @param message
	 * @return
	 */
	private String calculateKey(String message) {
		if (message.startsWith("Adding generated EPackage"))
			return "oaw: " + message; 
		if (message.startsWith("Checking configuration of"))
			return "oaw: checking configuration";
		if (message.startsWith("Reader:"))
			return "oaw: loading model";
		if (message.startsWith("CheckComponent:"))
			return "oaw: checking model";
		if (message.startsWith("Generator:"))
			return "oaw: starting generator";
		if (message.startsWith("Opening file")) {
			// get the filename
			int pos = message.lastIndexOf('\\');
			if (pos == -1)
				message.lastIndexOf('/');
			if (pos == -1)
				return "oaw: opening file";
			return "oaw: opening file " + message.substring(pos + 1);
		}
		if (message.startsWith("Written"))
			return "oaw: generation complete";
		if (message.startsWith("workflow completed"))
			return "oaw: workflow completed";
		
		return "oaw: other";
	}

	/**
	 * Write down the time (l) for the current key to have executed.
	 * Writes it to a file.
	 * 
	 * @param key
	 * @param l
	 */
	private void writeToFile(String key, long l) {
		try {
			FileWriter fw = new FileWriter(file, true);
			fw.write(key);
			fw.write(",");
			fw.write(Long.toString(l));
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
}
