/**
 * 
 */
package org.openiaml.model.codegen.oaw.coverage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.util.Random;

import org.omg.CORBA_2_3.portable.OutputStream;
import org.openarchitectureware.xpand2.output.FileHandle;

/**
 * Instruments the generated output in order to allow for
 * code coverage.
 * 
 * @author Jevon
 *
 */
public class OutputInstrumentation {

	public void instrumentFile(FileHandle file) throws IOException, InstrumentationException {
		String instrumented = instrument(file.getTargetFile());
		if (instrumented == null)
			return;		// do nothing
		
		// write output
		writeFile(file.getTargetFile(), instrumented);
		
		// also, write the helper file
		String resource = "src/org/openiaml/model/codegen/oaw/coverage/oaw_coverage.php";
		URL helper = getClass().getClassLoader().getResource(resource);
		if (helper == null) {
			throw new InstrumentationException("Could not load helper file resource '" + resource + "'");
		}
		
		String fn = file.getTargetFile().getParent() + File.separator + "oaw_coverage.php";
		writeFile(new File(fn), helper.openStream());

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
		
		// remove any runtime instrumentation code
		input = removeRuntimeInstrumentation(input);
		
		return input;
	}

	/**
	 * Remove any remaining '__runtime_instrument(..|..|..)__' calls.
	 * 
	 * @param input
	 * @return
	 */
	protected String removeRuntimeInstrumentation(String input) {
		return input.replaceAll("__runtime_instrument\\([^\\|]+\\|[^\\|]+\\|[^\\|]+\\)__", "");
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
					String[] args = code.split("\\|");
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
		
		// escape javascript
		input = instrumentHtmlJavascript(input);
		
		String[] bits = input.split("__output_instrument");
		String output = bits[0];
		
		for (int i = 1; i < bits.length; i++) {
			String bit = bits[i];
			if (!bit.isEmpty()) {
				if (bit.charAt(0) == '(') {
					// do instrumentation
					int end = bit.indexOf("__");	// end of the instrumentation code
					// removes (brackets), and splits on ; - there should be three
					String code = bit.substring(0, end - 1).substring(1);
					String[] args = code.split("\\|");
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
	 * Instrument <script> blocks within the HTML input. The <script>
	 * blocks may be broken up (e.g. split up by <?php ?> blocks).
	 * 
	 * This is called before instrumentation is removed via {@link #instrumentPhpHtml(String)}.
	 * 
	 * @param input
	 * @return
	 * @throws InstrumentationException 
	 */
	protected String instrumentHtmlJavascript(String input) throws InstrumentationException {

		String output = "";
		
		String[] scriptBlocks = input.split("<script");
		
		if (scriptBlocks.length == 0)
			return input;		// empty string
		
		// it wouldn't make sense to have <script></script></script>
		String[] end2 = scriptBlocks[0].split("</script>", 2);
		
		if (end2.length == 2) {
			// the first block was a script that needs to be ended
			output += instrumentJavascriptBlock(end2[0]);
			output += "</script>";
			output += end2[1];	// this is just HTML
		} else {
			// not script; just HTML
			output += end2[0];
		}
		
		// i+1.. blocks are all <script>s
		for (int i = 1; i < scriptBlocks.length; i++) {
			output += "<script";
			if (!scriptBlocks[i].contains("</script>")) {
				// no end of </script>
				output += instrumentJavascriptBlockTag(scriptBlocks[i]);
			} else {
				// it wouldn't make sense to have <script></script></script>
				String[] endBlocks = scriptBlocks[i].split("</script>", 2);
				output += instrumentJavascriptBlockTag(endBlocks[0]);
				output += "</script>";
				output += endBlocks[1];	// no additional instrumentation
			}
		}
		
		return output;
		
	}
		
	/**
	 * Instrument a block of Javascript. This is most likely to be code
	 * within <script>...</script> (but not guaranteed). But there won't be
	 * any <?php ... ?> code in here.
	 * 
	 * The string starts with the end of the script tag, i.e. ' language="...">function a() {...'
	 * 
	 * @see #instrumentJavascriptBlock(String)
	 * @param input
	 * @return
	 */
	protected String instrumentJavascriptBlockTag(String input) throws InstrumentationException {

		// jump to the start of the actual javascript
		if (!input.contains(">")) {
			throw new InstrumentationException("Script does not have an ending > character: '" + input + "'");
		}
		return input.substring(0, input.indexOf('>') + 1) + instrumentJavascriptBlock(input.substring(input.indexOf('>') + 1));		
		
	}

	private static int javascriptFunctionCounter = 0;
	
	/**
	 * Instrument a block of Javascript. This is most likely to be code
	 * within <script>...</script> (but not guaranteed). But there won't be
	 * any <?php ... ?> code in here.
	 * 
	 * This is designed to be the actual Javascript block.
	 * 
	 * @param input
	 * @return
	 */
	protected String instrumentJavascriptBlock(String input) throws InstrumentationException {

		// unique function names per block
		String ajaxFunctionName = "__javascript_instrument_" + javascriptFunctionCounter++;
		
		// we can't handle inline comments, so we replace them with block comments
		// don't replace comments that look like http:// (a hack)
		input = input.replaceAll("(\n|;|\\}|\\{|,)\\s*//([^\n]+)\n", "$1/*$2*/\n");

		// add AJAX code
		String output = "";
		output += "\tfunction " + ajaxFunctionName + "(dir, template, key) {\n";
		output += "\t	var url = 'oaw_coverage.php?javascript=1&dir=' + escape(dir) + '&template=' + escape(template) + '&key=' + escape(key);\n";	
		output += "\t	new Ajax.Request(url, { method : 'get' }); \n";
		output += "\t}\n";
		
		// replace all output templates
		String[] bits = input.split("__output_instrument");
		output += bits[0];
		
		for (int i = 1; i < bits.length; i++) {
			String bit = bits[i];
			if (!bit.isEmpty()) {
				if (bit.charAt(0) == '(') {
					// do instrumentation
					int end = bit.indexOf("__");	// end of the instrumentation code
					// removes (brackets), and splits on ; - there should be three
					String code = bit.substring(0, end - 1).substring(1);
					String[] args = code.split("\\|");
					if (args.length != 3) {
						throw new InstrumentationException("Output instrumentation code '" + code + "' was malformed");
					}
					
					char previous = getFirstNonWhitespace(output);
					// at ends of lines, the start of the php, or end of function/if blocks
					// or at the end of /* block comments */
					if (previous == ';' || previous == 0 || previous == '}') {
						// output instrumentation code here
						output += ajaxFunctionName + "(\"" + escapeJavascriptString(args[0]) + "\", \"" + escapeJavascriptString(args[1]) + "\", \"" + escapeJavascriptString(args[2]) + "\");";
					}
				} else {
					// do nothing
				}
				
				// we don't remove the original code (in case PHP wants to
				// log it as well); we just add it back as normal
				output += "__output_instrument" + bit;
			}
		}
		
		// TODO __runtime_instrument

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
	
	protected String escapeJavascriptString(String s) {
		return s.replace("\"", "\\\"");
	}
	
	/**
	 * Start from the end of 'buffer', and iterate backwards
	 * until we find a non-whitespace character.
	 * 
	 * ALSO escapes __foo()__ blocks.
	 * 
	 * ALSO escapes block comment blocks.
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
				// skip over __'s
				if (i > 3 && buffer.charAt(i) == '_' && buffer.charAt(i-1) == '_') {
					// iterate until we find another __
					for (i -= 2; i >= 1; i--) {
						if (buffer.charAt(i) == '_' && buffer.charAt(i-1) == '_') {
							i--;
							break;	// we found the end
						}
					}
					continue;
				}
				if (i > 3 && buffer.charAt(i) == '/' && buffer.charAt(i-1) == '*') {
					// iterate until we find the /*
					for (i -= 2; i >= 1; i--) {
						if (buffer.charAt(i) == '*' && buffer.charAt(i-1) == '/') {
							i--;
							break;	// we found the end
						}
					}
					continue;
				}
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
					String[] args = code.split("\\|");
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
	 * Write an InputStream to a file. If the file exists, it will be
     * overwritten. 
     * 
	 * @param file the file to write to
	 * @param stream the input stream to read from
	 * @throws IOException if an IO exception occurs
	 */
	public static void writeFile(File file, InputStream stream) throws IOException {
		FileOutputStream os = new FileOutputStream(file);
		
		// write from an input stream
		int bufSize = 128;
		byte[] chars = new byte[bufSize];
        int numRead = 0;
        while ((numRead = stream.read(chars)) > -1) {
        	os.write(chars, 0, numRead);
        }
        
        os.close();
		
	}
    
}
