/**
 * 
 */
package org.sosy_lab.crocopat.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jmwright
 *
 */
public class ExecuteCrocopat {

	private static final String CROCOPAT_COMMAND = "crocopat/crocopat-2.1.4_win32.exe";

	/**
	 * Execute Crocopat with the given RML and RSF input.
	 * Return a list of all output.
	 * 
	 * @param rml
	 * @param rsf
	 * @return
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public List<String> execute(InputStream rml, InputStream rsf) throws IOException, InterruptedException {
		
		// write out the rml to files
		File rmlFile = File.createTempFile("temp", ".rml");
		writeFile(rml, rmlFile);
		rml.close();
		
		String[] command = new String[] {
			getCrocopatCommand(),
			rmlFile.getAbsolutePath(),
		};
		Process proc = Runtime.getRuntime().exec(command);
		
		// write the rsf to the output stream
		OutputStream out = proc.getOutputStream();
		int c;
		while ((c = rsf.read()) != -1) {
			out.write(c);
		}
		rsf.close();
		out.close();
		
		// wait until completed
		proc.waitFor();
		
		// get all of the results from the input stream
		InputStream result = proc.getInputStream();
		StringBuffer buf = new StringBuffer();
		List<String> resultList = new ArrayList<String>();
		
		while ((c = result.read()) != -1) {
			if (c == '\n') {
				// append to list
				if (buf.length() != 0) {
					// ignore empty strings 
					resultList.add(buf.toString());
				}
				buf = new StringBuffer();
			} else {
				buf.append((char) c);
			}
		}
		result.close();
		
		// add the last result
		if (buf.length() != 0) {
			resultList.add(buf.toString());
		}
		
		// return the result
		return resultList;
	}

	/**
	 * @return
	 * @throws IOException 
	 */
	public String getCrocopatCommand() throws IOException {
		String url = CrocopatCliPlugin.getInstance().getResolvedFile(CROCOPAT_COMMAND).toExternalForm();
		if (url.startsWith("file:/")) {
			url = url.substring("file:/".length());
		} else {
			throw new RuntimeException("Did not expect crocopat command to not begin with 'file:/': " + url);
		}
		return url;
	}

	/**
	 * Write the given input stream to the given file. Does not close the
	 * InputStream.
	 * 
	 * @throws IOException 
	 * 
	 */
	public void writeFile(InputStream is, File f) throws IOException {
		FileWriter fw = new FileWriter(f);
		int c;
		while ((c = is.read()) != -1) {
			fw.write(c);
		}
		fw.close();
	}

	/**
	 * Read in a file into a string.
	 * 
	 * @throws IOException if an IO exception occurs
	 */
	public static String readFile(File sourceFile) throws IOException {
		if (!sourceFile.exists()) {
			throw new IOException("File " + sourceFile.getAbsolutePath() + " does not exist.");
		}
		
		int bufSize = 128;
		StringBuffer sb = new StringBuffer(bufSize);
		BufferedReader reader = new BufferedReader(new FileReader(sourceFile), bufSize);
				
		char[] chars = new char[bufSize];
		int numRead = 0;
		while ((numRead = reader.read(chars)) > -1) {
			sb.append(String.valueOf(chars).substring(0, numRead));	
		}
		
		reader.close();
		return sb.toString();
	}
	
	
}
