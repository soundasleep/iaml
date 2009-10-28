/**
 * 
 */
package org.sosy_lab.crocopat.cli;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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

	/**
	 * An exception occured while executing Crocopat.
	 * 
	 * @author jmwright
	 *
	 */
	public class CrocopatException extends Exception {

		/**
		 * @param errors A non-empty list of error strings.
		 */
		public CrocopatException(String message) {
			super(message);
		}

		private static final long serialVersionUID = 1L;

	}
	
	/**
	 * An exception occured while executing Crocopat; that is,
	 * something was written to stderr.
	 * 
	 * @author jmwright
	 *
	 */
	public class CrocopatStderrException extends CrocopatException {

		private List<String> errors;
		
		/**
		 * @param errors A non-empty list of error strings.
		 */
		public CrocopatStderrException(List<String> errors) {
			super(errors.size() + " error(s), first: '" + errors.get(0) + "'");
			this.errors = errors;
		}

		public List<String> getErrors() {
			return errors;
		}

		private static final long serialVersionUID = 1L;

	}

	private static final String CROCOPAT_COMMAND = "crocopat/crocopat-2.1.4_win32.exe";

	/**
	 * Wrap the {@link Process} class with more sensible methods.
	 * 
	 * @author jmwright
	 *
	 */
	public static class ProcessWrapper {

		private InputStream in;
		private OutputStream stdout;
		private OutputStream stderr;

		/**
		 * 
		 * @param in can be null if there is no input stream
		 * @param stdout output stream to write stdout to
		 * @param stderr output stream to write stderr to
		 */
		public ProcessWrapper(InputStream in, OutputStream stdout, OutputStream stderr) {
			this.in = in;
			this.stdout = stdout;
			this.stderr = stderr;
		}
		
		public ProcessWrapper(OutputStream stdout, OutputStream stderr) {
			this(null, stdout, stderr);
		}
		
		/**
		 * Execute the given command.
		 * 
		 * @param command
		 * @throws IOException 
		 */
		public int execute(String[] command) throws IOException {
			Process proc = Runtime.getRuntime().exec(command);
			
			// check for any output/error stream
			checkStreamNonBlocking(proc.getInputStream(), stdout);
			checkStreamNonBlocking(proc.getErrorStream(), stderr);
			
			// write the rsf to the output stream
			if (in != null) {
				OutputStream out = proc.getOutputStream();
				int c;
				while ((c = in.read()) != -1) {
					out.write(c);
				}
				in.close();
				out.close();
			}
		
			int exit;
			while (true) {
				try {
					// check output/error streams
					checkStreamNonBlocking(proc.getInputStream(), stdout);
					checkStreamNonBlocking(proc.getErrorStream(), stderr);
					
					exit = proc.exitValue();
					break;	// finished
				} catch (IllegalThreadStateException e) {
					// expected
				}
			}
			
			// check final (blocking)
			checkStream(proc.getInputStream(), stdout);
			checkStream(proc.getErrorStream(), stderr);
			
			return exit;
		}

		/**
		 * If data is available in the given input stream, write it
		 * out to the given output stream. Must not block.
		 * 
		 * @throws IOException
		 */
		public void checkStreamNonBlocking(InputStream in, OutputStream out) throws IOException {
			// don't block
			while (in.available() > 0) {
				int c = in.read();
				if (c == -1)
					return;	// end of stream
				
				out.write(c);
			}			
		}

		/**
		 * If data is available in the given input stream, write it
		 * out to the given output stream. Will block until it
		 * reaches the end of the stream.
		 * 
		 * @throws IOException
		 */
		public void checkStream(InputStream in, OutputStream out) throws IOException {
			// don't block
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
		}
	}
	
	/**
	 * Execute Crocopat with the given RML and RSF input.
	 * Return a list of all output.
	 * 
	 * @param rml
	 * @param rsf
	 * @return a list of output strings
	 * @throws IOException 
	 * @throws CrocopatException if Crocopat wrote something to stderr
	 */
	public List<String> execute(InputStream rml, InputStream rsf) throws IOException, InterruptedException, CrocopatException {
		
		// write out the rml to files
		File rmlFile = File.createTempFile("temp", ".rml");
		writeFile(rml, rmlFile);
		rml.close();
		
		String[] command = new String[] {
			getCrocopatCommand(),
			rmlFile.getAbsolutePath(),
		};
		
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		ByteArrayOutputStream stderr = new ByteArrayOutputStream();
		ProcessWrapper pw = new ProcessWrapper(rsf, stdout, stderr);
		
		// this will block until all stdout/stderr has been saved
		int exit = pw.execute(command);

		// we can now parse for results
		// get all of the results from the input stream
		List<String> output = convertBytesToStrings(stdout.toByteArray());
		List<String> errors = convertBytesToStrings(stderr.toByteArray());
		
		if (!errors.isEmpty()) {
			throw new CrocopatStderrException(errors);
		}
		
		// did we get no errors, but an incorrect exit code?
		if (exit != 0) {
			throw new CrocopatException("Expected return value '0', got: " + exit);
		}
		
		return output;
	}
	
	/**
	 * Convert the given array of bytes into a list of strings; 
	 * list separator is newline character '\n'. Does not add
	 * empty lines.
	 * 
	 * @return
	 */
	protected List<String> convertBytesToStrings(byte[] bytes) {
		List<String> resultList = new ArrayList<String>();

		int j = 0;
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == '\n') {
				if (i != j) {
					// append to list
					resultList.add(new String(bytes, j, i-j));
				}
				j = i + 1;
			}
		}
		
		// add the last result
		if (j < bytes.length) {
			resultList.add(new String(bytes, j, bytes.length-j));
		}
		
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
