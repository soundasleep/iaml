/**
 * 
 */
package org.openiaml.model.codegen.php.coverage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for the code coverage tools.
 * 
 * @author jmwright
 *
 */
public class CoverageUtils {

	// prevent instantiation; all methods are static
	private CoverageUtils() {
		// empty
	}
	
	/**
	 * Recursively get all of the files in the given directory.
	 * Iterates over sub-directories.
	 * 
	 * @param dir
	 * @return
	 * @throws InstrumentationException 
	 */
	public static final List<File> getRecursiveDirectoryContents(File dir) throws InstrumentationException {
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
     * Read in a file into a string.
     * 
     * @throws IOException if an IO exception occurs
     */
    public static final String readFile(File sourceFile) throws IOException {
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
    public static final void writeFile(File file, String data) throws IOException {
        Writer output = new BufferedWriter(new FileWriter(file));
        output.write(data);
        output.close();
    }
    
	/**
	 * Count the occurrences of a given character in the given
	 * string.
	 * 
	 * @param string
	 * @param c
	 * @return
	 */
	public static final int countOccurrences(String string, char c) {
		int count = 0;
		int current = 0;
		while (true) {
			int pos = string.indexOf(c, current);
			if (pos == -1)
				return count;
			current = pos + 1;
			count++;
		}
	}

	/**
	 * Read in an InputStream into a string.
	 * 
	 * @param openStream
	 * @return
	 * @throws IOException if an IO exception occurs
	 */
	public static String readFile(InputStream reader) throws IOException {
        int bufSize = 128;
        StringBuffer sb = new StringBuffer(bufSize);
                        
        byte[] chars = new byte[bufSize];
        int numRead = 0;
        while ((numRead = reader.read(chars)) > -1) {
                sb.append(String.valueOf(chars).substring(0, numRead)); 
        }
        
        reader.close();
        return sb.toString();
	}

}
