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
		logDirect("overhead", "(log loaded)", "", "");
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
			String[] key = calculateKey(message);
			
			if (key != null) {	
				logDirect(key[0], key[1], key[2], key[3]);
				
			}
		}
	}
	
	/**
	 * Log the given key directly.
	 */
	public void logDirect(String key1, String key2, String key3, String key4) {
		String key = new StringBuffer(key1).append(',').append(key2).append(',').append(key3).append(',').append(key4).toString();
		if (key.equals(lastKey)) {
			// ignore
		} else {
			// the key has changed - mark down the current key time
			// and write it to file
			long newKeyTime = System.currentTimeMillis();
			
			writeToFile(lastKey, newKeyTime, newKeyTime - lastKeyTime);
			
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
	private String[] calculateKey(String message) {
		if (message.startsWith("Adding generated EPackage"))
			return new String[]{"oaw", message, "", ""}; 
		if (message.startsWith("Checking configuration of"))
			return new String[]{"oaw", "checking configuration", "", ""};
		if (message.startsWith("Reader:"))
			return new String[]{"oaw", "loading model", "", ""};
		if (message.startsWith("CheckComponent:"))
			return new String[]{"oaw", "checking model", "", ""};
		if (message.startsWith("Generator:"))
			return new String[]{"oaw", "starting generator", "", ""};
		if (message.startsWith("Opening file")) {
			message = message.replace('\\', '/');
			String[] bits = message.split("/");
			switch (bits.length) {
				case 0:
					return new String[]{"oaw", "opening file", "", ""};
				case 1:
					return new String[]{"oaw", "opening file", bits[0], ""};
				default:
					return new String[]{"oaw", "opening file", bits[bits.length-2], bits[bits.length-1]};
			}
		}
		if (message.startsWith("Written"))
			return new String[]{"oaw", "generation complete", "", ""};
		if (message.startsWith("workflow completed"))
			return new String[]{"oaw", "workflow complete", "", ""};
		
		return new String[]{"oaw", "other", "", ""};
	}

	/**
	 * Write down the time (l) for the current key to have executed.
	 * Writes it to a file.
	 * 
	 * @param key
	 * @param l
	 */
	private void writeToFile(String key1, long now, long diff) {
		try {
			FileWriter fw = new FileWriter(file, true);
			fw.write(key1);
			fw.write(",");
			fw.write(Long.toString(now));
			fw.write(",");
			fw.write(Long.toString(diff));
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
}
