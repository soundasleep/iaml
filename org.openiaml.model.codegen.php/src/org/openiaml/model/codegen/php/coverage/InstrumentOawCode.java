/**
 * 
 */
package org.openiaml.model.codegen.php.coverage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The intent of this class is to instrumentize 
 * 
 * @author jmwright
 *
 */
public class InstrumentOawCode {

	/**
	 * A special string to enable output instrumentation. This line
	 * should only be added to files that have actually been instrumented.
	 */
	public static final String ENABLE_OUTPUT_INSTRUMENTATION = "__enable_output_instrumentation__";
	
	/**
	 * The results of the instrumentation. If this is null
	 * when accessed, a new instrumentation file is created.
	 */
	private static Map<String, Map<String, Integer>> instrumented;
	private static String instrumentedFile;
	
	/**
	 * Instrument all the template files in the given directory.
	 * The results of this method MUST be undone by 
	 * {@link #postInstrumentTemplates(File)}.
	 * 
	 * @param templateDir
	 * @param destinationDir the directory to output the results of coverage
	 * @throws InstrumentationException 
	 */
	public void preInstrumentTemplates(File templateDir, File destinationDir) throws InstrumentationException {
		if (!templateDir.exists()) {
			throw new InstrumentationException("Template directory '" + templateDir + "' does not exist.");
		}
		if (!templateDir.isDirectory()) {
			throw new InstrumentationException("Template directory '" + templateDir + "' is not a directory.");
		}
		
		// are any of the files in this directory a ".preinstrument" file?
		// this means that previous instrumentation failed
		List<File> children = CoverageUtils.getRecursiveDirectoryContents(templateDir);
		File invalid = hasFileExtension(children, ".preinstrument");
		if (invalid != null) {
			throw new InstrumentationException("Previously instrumented file found: '" +invalid + "'"); 
		}
		
		// rename all .xpt files to .xpt.preinstrument
		for (File xpt : children) {
			if (xpt.getAbsolutePath().endsWith(".xpt")) {
				System.out.println("Instrumenting " + xpt + "...");
				
				File renamed = new File(xpt.getAbsolutePath() + ".preinstrument");
				boolean isRenamed = xpt.renameTo(renamed);
				if (!isRenamed) {
					throw new InstrumentationException("Could not rename file '" + xpt + "'");
				}
				
				// and now create a new one, which is instrumented
				try {
					String input = CoverageUtils.readFile(renamed);
					input = instrumentFile(destinationDir, xpt.getAbsolutePath(), input);
					CoverageUtils.writeFile(xpt, input);
				} catch (IOException e) {
					throw new InstrumentationException(e);
				}				
			}
		}
		
		System.out.println("Instrumentation complete");
	}
	
	/**
	 * Instrument the input file, and return the result.
	 * 
	 * NOTE that elements in the .xpt that use the '' or | characters
	 * will not be instrumented (until I get some sort of callback function
	 * implemented for regular expressions, so I can escape both " and ').
	 * 
	 * @param input
	 * @return
	 * @throws InstrumentationException 
	 */
	protected String instrumentFile(File destinationDir, String filename, String input) throws InstrumentationException {
		// we want to jump between each instance of «
		
		// we need to escape the strings a lot
		final String filenameEscaped = filename.replace("\\", "\\\\\\\\");
		final String destDir = destinationDir.getAbsolutePath().replace("\\", "\\\\\\\\");
		
		input = input.replaceAll("«((DEFINE|FILE|IF|ELSE|ELSEIF|FOREACH)[^'»]*?)»", "«$1»«EXPAND _instrument_template FOR _instrument('" + destDir + "', '" + filename + "', '__LINE_NUMBER__:$1')»");

		// add the instrumentation code at the bottom
		input += "\n\n«DEFINE _instrument_template FOR Object»«ENDDEFINE»";
		
		// replace all FILE templates with special tags to enable
		// output code coverage
		input = input.replaceAll("«(FILE[^'»]*?)»", "«$1»" + ENABLE_OUTPUT_INSTRUMENTATION);
		
		// output code coverage
		input = input.replaceAll("«((DEFINE|FILE|IF|ELSE|ELSEIF|FOREACH)[^'\\|»]*?)»", "«$1»__output_instrument(" + destDir + "|" + filenameEscaped + "|__LINE_NUMBER__:$1)__");
		
		// output coverage for individual template statements
		// this is necessary so we can see what parts of individual templates are actually being executed at run-time
		input = addRuntimeOutputInformation(input, destDir, filename);
		
		// replace all line numbers
		input = replaceLineNumbers(input);
		
		return input;
	}

	/**
	 * Add any runtime template information to the instrumented templates.
	 * 
	 * This is necessary so we can see what parts of individual templates are actually being executed at run-time.
	 * 
	 * @param input
	 * @param destDir 
	 * @param filename 
	 * @return
	 * @throws InstrumentationException 
	 */
	protected String addRuntimeOutputInformation(String input, String destDir, String filename) throws InstrumentationException {
		
		// find all code outside of « »s
		String[] bits = input.split("»");
		String output = bits[0] + "»";
		
		for (int i = 1; i < bits.length; i++) {
			String[] sub = bits[i].split("«");
			if (sub.length == 2) {
				output += addRuntimeOutputBlock(sub[0], destDir, filename) + "«" + sub[1]; 
			} else if (sub.length == 1) {
				output += bits[i];
			} else {
				throw new InstrumentationException("Unexpectedly found more than 2 '«'s in '" + bits[i] + "'");
			}
			output += "»";
		}
		
		return output;
		
	}

	/**
	 * This is the actually generated text, outside of all of the templates.
	 * @param destDir 
	 * @param filename 
	 * 
	 * @param string
	 * @return
	 */
	private String addRuntimeOutputBlock(String input, String destDir, String filename) {
		// TODO do not replace these characters within strings (follow iacleaner example)
		input = input.replaceAll("(}|;)", "$1__runtime_instrument(" + destDir + "|" + filename + "|RUNTIME:__LINE_NUMBER__:$1)__");
		
		return input;
	}

	/**
	 * Replace all __LINE_NUMBER__ with the line number in the file.
	 * 
	 * @param input
	 * @return
	 */
	protected String replaceLineNumbers(String input) {
		String buffer = input; // content taken from here..
		String output = ""; // ..and placed into here
		int currentCount = 1;	// start line numbers at 1
		
		while (true) {
			int pos = buffer.indexOf("__LINE_NUMBER__");
			if (pos == -1) {
				output += buffer;
				return output; 
			}
			String before = buffer.substring(0, pos);
			buffer = buffer.substring(pos + "__LINE_NUMBER__".length());
			int count = countOccurrences(before, '\n');
			currentCount += count;
			output += before + currentCount;
		}
	}

	/**
	 * Count the occurrences of a given character in the given
	 * string.
	 * 
	 * @see CoverageUtils#countOccurrences(String, char)
	 * @param string 
	 * @param c
	 * @return
	 */
	private int countOccurrences(String string, char c) {
		return CoverageUtils.countOccurrences(string, c);
	}

	/**
	 * Does any element in the list of files have the given file extension?
	 * 
	 * @param children
	 * @param string
	 * @return
	 */
	protected File hasFileExtension(List<File> children, String string) {
		for (File f : children) {
			if (f.getAbsolutePath().endsWith(string)) {
				return f;
			}
		}
		return null;
	}

	/**
	 * Revert any changes from template instrumentation. Needs to revert
	 * ALL changes made by {@link #preInstrumentTemplates(File, String)}.
	 * 
	 * @param templateDir
	 * @throws InstrumentationException 
	 */
	public void postInstrumentTemplates(File templateDir) throws InstrumentationException {
		if (!templateDir.exists()) {
			throw new InstrumentationException("Template directory '" + templateDir + "' does not exist.");
		}
		if (!templateDir.isDirectory()) {
			throw new InstrumentationException("Template directory '" + templateDir + "' is not a directory.");
		}
		
		List<File> children = CoverageUtils.getRecursiveDirectoryContents(templateDir);
		for (File f : children) {
			if (f.getAbsolutePath().endsWith(".xpt.preinstrument")) {
				System.out.println("Reverting " + f + "...");

				// rename the file, but overwrite it if it exists
				String newName = f.getAbsolutePath();
				newName = newName.substring(0, newName.lastIndexOf(".xpt.preinstrument")) + ".xpt";
				
				File exists = new File(newName);
				if (exists.exists()) {
					// delete it
					boolean deleted = exists.delete();
					if (!deleted) {
						throw new InstrumentationException("Could not delete file '" + exists + "' to replace with '" + f + "'");
					}
				}
				
				// do rename
				f.renameTo(exists);
			}
		}
		
	}

    /**
     * This method is called from the OAW templates to log
     * the code that is actually executed. 
     * 
     * @param filename
     * @param statement
     */
    public static void instrument(String destinationDir, String filename, String statement) {
    	if (instrumentedFile == null) {
    		// create a new instrumented file
    		instrumentedFile = "instrumented.dump";
    	}
    	
    	if (instrumented == null) {
    		instrumented = new HashMap<String, Map<String, Integer>>();
    	}
    	
    	if (!instrumented.containsKey(filename)) {
    		instrumented.put(filename, new HashMap<String,Integer>());
    	}
    	
    	if (!instrumented.get(filename).containsKey(statement)) {
    		instrumented.get(filename).put(statement, 1);
    	} else {
    		int value = instrumented.get(filename).get(statement);
    		instrumented.get(filename).put(statement, value + 1);
    	}
    	
    	// save to file
    	try {
			saveInstrumentation(destinationDir);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    }

	/**
	 * @throws IOException 
	 * 
	 */
	private static void saveInstrumentation(String destinationDir) throws IOException {
		File f = new File(destinationDir + "/" + instrumentedFile);
		
		StringBuffer buf = new StringBuffer();
		for (String file : instrumented.keySet()) {
			buf.append(file).append(":\n");
			for (String statement : instrumented.get(file).keySet()) {
				buf.append("\t").append(statement).append(": ").append(instrumented.get(file).get(statement)).append("\n");
			}
		}
		
		CoverageUtils.writeFile(f, buf.toString());
	}
	
}
