/**
 * 
 */
package org.openiaml.model.codegen.oaw.coverage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
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
		List<File> children = getRecursiveDirectoryContents(templateDir);
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
					String input = readFile(renamed);
					input = instrumentFile(destinationDir, xpt.getAbsolutePath(), input);
					writeFile(xpt, input);
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
	 * NOTE that elements in the .xpt that use the '' characters
	 * will not be instrumented (until I get some sort of callback function
	 * implemented for regular expressions, so I can escape both " and ').
	 * 
	 * @param input
	 * @return
	 */
	protected String instrumentFile(File destinationDir, String filename, String input) {
		// we want to jump between each instance of «
		
		// we need to escape the strings a lot
		filename = filename.replace("\\", "\\\\\\\\");
		String destDir = destinationDir.getAbsolutePath().replace("\\", "\\\\\\\\");
		
		input = input.replaceAll("«((DEFINE|FILE|IF|ELSE|ELSEIF|FOREACH)[^'»]*?)»", "«$1»«EXPAND _instrument_template FOR _instrument('" + destDir + "', '" + filename + "', '$1')»");

		// add the instrumentation code at the bottom
		input += "\n\n«DEFINE _instrument_template FOR Object»«ENDDEFINE»";
		
		return input;
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
	 * Recursively get all of the files in the given directory.
	 * Iterates over sub-directories.
	 * 
	 * @param dir
	 * @return
	 * @throws InstrumentationException 
	 */
	protected List<File> getRecursiveDirectoryContents(File dir) throws InstrumentationException {
		if (!dir.exists()) {
			throw new InstrumentationException("Directory '" + dir + "' does not exist.");
		}
		if (!dir.isDirectory()) {
			throw new InstrumentationException("Directory '" + dir + "' is not a directory.");
		}
		
		List<File> results = new ArrayList<File>();
		String[] children = dir.list();
		for (String child : children) {
			File f = new File(dir.getAbsolutePath() + "/" + child);
			if (!f.exists()) {
				throw new InstrumentationException("Child file '" + f + "' does not exist.");
			}
			if (f.isDirectory()) {
				if (!child.equals(".svn")) {
					results.addAll(getRecursiveDirectoryContents(f));
				}
			} else {
				results.add(f);
			}
		}
		
		return results;
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
		
		List<File> children = getRecursiveDirectoryContents(templateDir);
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
    
    /**
     * Write a string to a file. If the file exists, it will be
     * overwritten. 
     * 
     * @throws IOException if an IO exception occurs
     */
    public static void writeFile(File file, String data) throws IOException {
        Writer output = new BufferedWriter(new FileWriter(file));
        output.write(data);
        output.close();
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
    		instrumentedFile = "instrumented-" + System.currentTimeMillis() + ".dump";
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
		
		writeFile(f, buf.toString());
	}
	
}
