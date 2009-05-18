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

import org.openarchitectureware.xpand2.output.FileHandle;

/**
 * Instruments the generated output in order to allow for
 * code coverage.
 * 
 * @author Jevon
 *
 */
public class OutputInstrumentation implements org.openarchitectureware.xpand2.output.PostProcessor {

	public static long PHP_IDENTIFIER = System.currentTimeMillis();
	
	/**
	 * We use the IACleaner to format the file.
	 * 
	 * @see org.openarchitectureware.xpand2.output.PostProcessor#afterClose(org.openarchitectureware.xpand2.output.FileHandle)
	 * @throws RuntimeException if an IOException or CleanerException occurs while trying to format the file
	 */
	@Override
	public void afterClose(FileHandle file) {
		try {
			String instrumented = instrument(file.getTargetFile());
			if (instrumented == null)
				return;		// do nothing
			
			// write output
			writeFile(file.getTargetFile(), instrumented);
			
			// also, write the helper file
			// TODO actually load this from the .php file provided
			String in = "<?php"
					+ "\n/* "
					+ "\n * this is special code that should be included in instrumented"
					+ "\n * code, which allows us to perform instrumentation of:" 
					+ "\n *" 
					+ "\n * 1. PHP (via inline calls)"
					+ "\n * 2. HTML (via inline calls)"
					+ "\n * 3. Javascript (via AJAX calls) TODO"
					+ "\n */"

					+ "\nfunction php_instrument_oaw($destination, $oaw_file, $oaw_line) {"
					+ "\n	$file = $destination . '/' . 'php-instrument-" + PHP_IDENTIFIER + ".dump.raw';"
					+ "\n	"
					+ "\n	// load"
					+ "\n	if (file_exists($file)) {"
					+ "\n		$serialized = unserialize(file_get_contents($file));"
					+ "\n	} else {"
					+ "\n		$serialized = array();"
					+ "\n	}"
					+ "\n"	
					+ "\n	// mark"
					+ "\n	if (!isset($serialized[$oaw_file])) {"
					+ "\n		$serialized[$oaw_file] = array();"
					+ "\n	}"
					+ "\n	if (!isset($serialized[$oaw_file][$oaw_line])) {"
					+ "\n		$serialized[$oaw_file][$oaw_line] = 0;"
					+ "\n	}"
					+ "\n	$serialized[$oaw_file][$oaw_line]++;"
					+ "\n	"
					+ "\n	// save"
					+ "\n	file_put_contents($file, serialize($serialized));"
					+ "\n	"
					+ "\n	// also write an easy-to-read version"
					+ "\n	$file = $destination . '/' . 'php-instrument-" + PHP_IDENTIFIER + ".dump';"
					+ "\n	$out = '';"
					+ "\n	foreach ($serialized as $key => $value) {"
					+ "\n		$out .= \"$key:\n\";"
					+ "\n		foreach ($value as $k => $v) {"
					+ "\n			$out .= \"\t$k: $v\n\";"
					+ "\n		}"
					+ "\n	}"
					+ "\n	file_put_contents($file, $out);"
					+ "\n}";
			String fn = file.getTargetFile().getParent() + File.separator + "oaw_coverage.php";
			writeFile(new File(fn), in);
		} catch (IOException e) {
			throw new RuntimeException("[" + file.getTargetFile() + "] IO Exception during prettifier: " + e.getMessage(), e);
		} catch (InstrumentationException e) {
			throw new RuntimeException("[" + file.getTargetFile() + "] Instrumentation Exception during prettifier: " + e.getMessage(), e);
		}
		
	}

	/**
	 * Open the given file, instrument it, and return the instrumented
	 * code.
	 * 
	 * Returns null if the file should not be instrumented.
	 * 
	 * @param targetFile
	 * @return
	 * @throws IOException 
	 */
	protected String instrument(File targetFile) throws InstrumentationException, IOException {
		String input = readFile(targetFile);
		
		if (!input.contains(InstrumentOawCode.ENABLE_OUTPUT_INSTRUMENTATION)) {
			// we shouldn't instrument this file, it doesn't have code instrumentation info
			return null;
		}
		
		// remove this key
		input = input.replace(InstrumentOawCode.ENABLE_OUTPUT_INSTRUMENTATION, "");
		
		// what type of instrumentation should we do?
		String fileType = targetFile.getAbsolutePath().substring(targetFile.getAbsolutePath().lastIndexOf(".") + 1).toLowerCase();
		if (fileType.equals("html")) {
			input = instrumentHtml(input);
		} else if (fileType.equals("php")) {
			input = instrumentPhp(input);
		} else if (fileType.equals("js")) {
			input = instrumentJs(input);
		} else {
			input = instrumentDefault(input);
		}
		
		return input;
	}

	/**
	 * @param input
	 * @return
	 * @throws InstrumentationException 
	 */
	protected String instrumentJs(String input) throws InstrumentationException {
		return instrumentDefault(input);
	}
	
	/**
	 * @param input
	 * @return
	 * @throws InstrumentationException 
	 */
	protected String instrumentHtml(String input) throws InstrumentationException {
		// TODO add ajax calls?
		return instrumentDefault(input);
	}
	
	/**
	 * Instrument the PHP script in input; this script is guaranteed
	 * to be within <?php... ?> blocks.
	 * @param input
	 * @return
	 * @throws InstrumentationException 
	 */
	protected String instrumentPhpScript(String input) throws InstrumentationException {
		String[] bits = input.split("__output_instrument");
		String output = "";
		
		for (String bit : bits) {
			if (!bit.isEmpty()) {
				if (bit.charAt(0) == '(') {
					// do instrumentation
					int end = bit.indexOf("__");	// end of the instrumentation code
					// removes (brackets), and splits on ; - there should be three
					String code = bit.substring(0, end - 1).substring(1);
					String[] args = code.split(";");
					if (args.length != 3) {
						throw new InstrumentationException("Output instrumentation code '" + code + "' was malformed");
					}
					String result = bit.substring(end + "__".length());
					
					char previous = getFirstNonWhitespace(output);
					// at ends of lines, the start of the php, or end of function/if blocks
					if (previous == ';' || previous == 0 || previous == '}') {
						// output instrumentation code here
						output += " require_once(\"oaw_coverage.php\"); php_instrument_oaw(\"" + escapePhpString(args[0]) + "\", \"" + escapePhpString(args[1]) + "\", \"" + escapePhpString(args[2]) + "\"); ";
					}
					
					// do nothing with the code
					output += result;
				} else {
					// do nothing
					output += bit;
				}
			}
		}
		
		return output;
	}

	/**
	 * Instrument the HTML code in input; this script is guaranteed
	 * to not be within <?php... ?> blocks, but this file is definitely
	 * a .php file.
	 * @param input
	 * @return
	 * @throws InstrumentationException 
	 */
	protected String instrumentPhpHtml(String input) throws InstrumentationException {
		String[] bits = input.split("__output_instrument");
		String output = "";
		
		for (String bit : bits) {
			if (!bit.isEmpty()) {
				if (bit.charAt(0) == '(') {
					// do instrumentation
					int end = bit.indexOf("__");	// end of the instrumentation code
					// removes (brackets), and splits on ; - there should be three
					String code = bit.substring(0, end - 1).substring(1);
					String[] args = code.split(";");
					if (args.length != 3) {
						throw new InstrumentationException("Output instrumentation code '" + code + "' was malformed");
					}
					String result = bit.substring(end + "__".length());
					
					// ALL instrumentation code can be directly saved
					// as php.
					output += "<?php require_once(\"oaw_coverage.php\"); php_instrument_oaw(\"" + escapePhpString(args[0]) + "\", \"" + escapePhpString(args[1]) + "\", \"" + escapePhpString(args[2]) + "\"); ?>";
					
					output += result;
				} else {
					// do nothing
					output += bit;
				}
			}
		}
		
		return output;
	}

	/**
	 * Default instrumentation code; just get rid of all 
	 * instrumentation keys.
	 * 
	 * @param input
	 * @return
	 * @throws InstrumentationException 
	 */
	protected String instrumentPhp(String input) throws InstrumentationException {
		// split up all <?php blocks
		String[] bits = input.split("<\\?php");
		String output = "";
		
		// bits[0] is definitely html
		output += instrumentPhpHtml(bits[0]);
		
		if (bits.length > 1) {
			// bits[1...n-1] is definitely <?php ...?>html
			for (int i = 1; i < bits.length - 1; i++) {
				output += "<?php";
				String[] sub = bits[i].split("\\?>");
				if (sub.length != 2) {
					throw new InstrumentationException("Unexpected multiple occurences of ?> in bit: " + bits[i]);
				}
				output += instrumentPhpScript(sub[0]);
				output += "?>";
				output += instrumentPhpHtml(sub[1]);
			}
			
			// bits[n] is definitely <?php ...
			output += "<?php";
			output += instrumentPhpScript(bits[bits.length - 1]);
		}
		
		return output;
	}
	
	protected String escapePhpString(String s) {
		return s.replace("\"", "\\\"");
	}
	
	/**
	 * Start from the end of 'buffer', and iterate backwards
	 * until we find a non-whitespace character.
	 * 
	 * @param buffer
	 * @return the first non-whitespace character, or 0 if
	 * none could be found
	 */
	protected char getFirstNonWhitespace(String buffer) {
		if (buffer.length() == 0)
			return 0;
		
		for (int i = buffer.length() - 1; i >= 0; i--) {
			if (!Character.isWhitespace(buffer.charAt(i))) {
				return buffer.charAt(i);
			}
		}
		return 0;
	}

	/**
	 * Default instrumentation code; just get rid of all 
	 * instrumentation keys.
	 * 
	 * @param input
	 * @return
	 * @throws InstrumentationException 
	 */
	protected String instrumentDefault(String input) throws InstrumentationException {
		String[] bits = input.split("__output_instrument");
		String output = "";
		
		for (String bit : bits) {
			if (!bit.isEmpty()) {
				if (bit.charAt(0) == '(') {
					// do instrumentation
					int end = bit.indexOf("__");	// end of the instrumentation code
					// removes (brackets), and splits on ; - there should be three
					String code = bit.substring(0, end - 1).substring(1);
					String[] args = code.split(";");
					if (args.length != 3) {
						throw new InstrumentationException("Output instrumentation code '" + code + "' was malformed");
					}
					String result = bit.substring(end + "__".length());
					
					// do nothing with the code
					output += result;
				} else {
					// do nothing
					output += bit;
				}
			}
		}
		
		return output;
	}

	/* (non-Javadoc)
	 * @see org.openarchitectureware.xpand2.output.PostProcessor#beforeWriteAndClose(org.openarchitectureware.xpand2.output.FileHandle)
	 */
	@Override
	public void beforeWriteAndClose(FileHandle file) {
		// ignore	
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
}
