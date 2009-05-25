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
	 * A helper class to keep track of output summaries.
	 * 
	 * @author jmwright
	 *
	 */
	protected class OutputSummary {
		private File template;
		private File destination;
		private long blocks = 0;
		private long coveredOaw = 0;
		private long coveredPhp = 0;
		
		/**
		 * 
		 * @param template Input template file
		 * @param destination Destination output file
		 */
		public OutputSummary(File template, File destination) {
			this.template = template;
			this.destination = destination;
		}

		/**
		 * Add another potential execution block.
		 */
		public void addBlock() {
			blocks++;
		}

		/**
		 * Call when a block has been covered with OAW execution.
		 */
		public void coveredInOaw() {
			coveredOaw++;
		}

		/**
		 * Call when a block has been covered with PHP execution.
		 */
		public void coveredInPhp() {
			coveredPhp++;
		}

		public File getTemplate() {
			return template;
		}

		public File getDestination() {
			return destination;
		}

		public long getBlocks() {
			return blocks;
		}

		public long getCoveredOaw() {
			return coveredOaw;
		}

		public long getCoveredPhp() {
			return coveredPhp;
		}

	}

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
	 * @throws InstrumentationException 
	 */
	protected String instrumentFile(File destinationDir, String filename, String input) throws InstrumentationException {
		// we want to jump between each instance of «
		
		// we need to escape the strings a lot
		filename = filename.replace("\\", "\\\\\\\\");
		String destDir = destinationDir.getAbsolutePath().replace("\\", "\\\\\\\\");
		
		input = input.replaceAll("«((DEFINE|FILE|IF|ELSE|ELSEIF|FOREACH)[^'»]*?)»", "«$1»«EXPAND _instrument_template FOR _instrument('" + destDir + "', '" + filename + "', '__LINE_NUMBER__:$1')»");

		// add the instrumentation code at the bottom
		input += "\n\n«DEFINE _instrument_template FOR Object»«ENDDEFINE»";
		
		// replace all FILE templates with special tags to enable
		// output code coverage
		input = input.replaceAll("«(FILE[^'»]*?)»", "«$1»" + ENABLE_OUTPUT_INSTRUMENTATION);
		
		// output code coverage
		input = input.replaceAll("«((DEFINE|FILE|IF|ELSE|ELSEIF|FOREACH)[^'»]*?)»", "«$1»__output_instrument(" + destDir + "|" + filename + "|__LINE_NUMBER__:$1)__");
		
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
	 * @param before
	 * @param c
	 * @return
	 */
	private int countOccurrences(String before, char c) {
		int count = 0;
		int current = 0;
		while (true) {
			int pos = before.indexOf(c, current);
			if (pos == -1)
				return count;
			current = pos + 1;
			count++;
		}
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
		
		writeFile(f, buf.toString());
	}
	
	/**
	 * The aim of this method is to take our template files and
	 * the results of the instrumentation, and generate a pretty format
	 * of the code coverage for HTML.
	 * 
	 * @param oawDump
	 * @param phpDump
	 * @param templatesDir
	 * @param outputDir
	 * @throws InstrumentationException 
	 * @throws IOException 
	 */
	public void outputCoveredCode(File oawDump, File phpDump, File templatesDir, File outputDir) throws InstrumentationException, IOException {
		// checks
		if (!templatesDir.exists() || !templatesDir.isDirectory()) {
			throw new InstrumentationException("Template directory '" + templatesDir + "' does not exist or is not a directory.");
		}
		if (!outputDir.exists()) {
			boolean created = outputDir.mkdirs();
			if (!created) {
				throw new InstrumentationException("Could not create directory '" + outputDir + "'");
			}
		}
		if (!oawDump.exists()) {
			throw new InstrumentationException("File '" + oawDump + "' does not exist");
		}
		if (!phpDump.exists()) {
			throw new InstrumentationException("File '" + phpDump + "' does not exist");
		}
		
		// load the dumps
		Map<String, Map<String, Integer>> oaw = loadInstrumentation(oawDump);
		Map<String, Map<String, Integer>> php = loadInstrumentation(phpDump);
		
		// get templates
		List<File> templates = getRecursiveDirectoryContents(templatesDir);
		List<OutputSummary> summaries = new ArrayList<OutputSummary>();
		for (File template : templates) {
			if (template.getAbsolutePath().endsWith(".xpt")) {
				String templateOut = outputDir.getAbsolutePath() + File.separator + getOutputName(template);
				OutputSummary summary = outputCoveredTemplate(oaw, php, template, new File(templateOut));
				summaries.add(summary);
			}
		}
		
		// write a summary
		String templateOut = outputDir.getAbsolutePath() + File.separator + "index.html";
		outputSummary(summaries, templateOut); 
	}

	/**
	 * Output a summary of all templates to the given file.
	 * 
	 * @param summaries
	 * @param templateOut File to write to
	 * @throws IOException 
	 */
	private void outputSummary(List<OutputSummary> summaries, String templateOut) throws IOException {
		String html = "";
		double barWidth = 200;

		for (OutputSummary summary : summaries) {
			html += "<tr><th><a href=\"" + escapeHtmlQuotes(summary.getDestination().getName()) + "\">" + escapeHtml(formatTemplateName(summary.getTemplate())) + "</a></th>\n";
			{
				double ratio = summary.getCoveredOaw() / (1.0 * summary.getBlocks());
				double ratio2 = summary.getCoveredPhp() / (1.0 * summary.getBlocks());
				html += "<td>" + formatNumber(summary.getCoveredOaw()) + " / " + formatNumber(summary.getBlocks()) + " (" + formatNumber(ratio * 100) + "%) </td>\n";
				html += "<td>" + formatNumber(summary.getCoveredPhp()) + " / " + formatNumber(summary.getBlocks()) + " (" + formatNumber(ratio2 * 100) + "%) </td>\n";
				html += "<td style=\"border:1px solid #ccc;margin:0;padding:0;background:#f33;\">";
				html += "<div style=\"width:" + Math.floor((barWidth * ratio2)) + "px;background:#3f3;height:1.3em;display:inline-block;\"></div>";
				html += "<div style=\"width:" + Math.floor((barWidth * (ratio - ratio2))) + "px;background:#ff3;height:1.3em;display:inline-block;\"></div>";
				html += "<div style=\"width:" + Math.floor((barWidth * (1.0-ratio))) + "px;background:#f33;height:1.3em;display:inline-block;\"></div>";
				html += "</td>\n";
			}
			html += "</tr>\n";
		}
		
		html = "<html><style>body,html,th,td{font-family:Arial;}</style><body><h1>Summary</h1>\n\n<table><tr><th>Template</th><th>OAW</th><th>PHP</th><th width=\"" + barWidth + "\">%</th></tr>\n" + html + "</table></html>";
		
		System.out.println("Writing summary '" + templateOut + "'...");
		writeFile(new File(templateOut), html);
	}

	/**
	 * @param d
	 * @return
	 */
	private String formatNumber(double d) {
		return formatNumber(new Double(d).longValue());
	}

	/**
	 * @param n
	 * @return
	 */
	private String formatNumber(long n) {
		return new Long(n).toString();
	}
	
	/**
	 * Get an easy-to-read filename of the given file. 
	 * Removes the root directory from the file.
	 * 
	 * @param f
	 * @return
	 */
	private String formatTemplateName(File f) {
		File root = new File("");
		String out = f.getAbsolutePath().replace(root.getAbsolutePath() + File.separator, "");
		return out;
	}

	/**
	 * @param oaw oaw instrumentation results
	 * @param php php instrumentation results
	 * @param template the xpt template file itself
	 * @param file file to output results to
	 * @param templates list of templates that we are loading (used to generate hyperlinks)
	 * @return an output summary of the instrumented template
	 * @throws IOException 
	 */
	protected OutputSummary outputCoveredTemplate(Map<String, Map<String, Integer>> oaw,
			Map<String, Map<String, Integer>> php, File template,
			File file) throws IOException {
		
		String html = readFile(template);
		OutputSummary os = new OutputSummary(template, file);
		
		// escape html
		html = escapeHtml(html);
		
		// elements to worry about: DEFINE|FILE|IF|ELSE|ELSEIF|FOREACH
		html = replaceBinaryTag("DEFINE", "ENDDEFINE", html, template.getAbsolutePath(), oaw, php, os);
		html = replaceBinaryTag("FILE", "ENDFILE", html, template.getAbsolutePath(), oaw, php, os);
		html = replaceBinaryTag("FOREACH", "ENDFOREACH", html, template.getAbsolutePath(), oaw, php, os);
		
		// hopefully this clever little hack works
		html = replaceBinaryTag("IF", "ENDIF", html, template.getAbsolutePath(), oaw, php, os);
		html = replaceBinaryTagReverse("ELSE", "ELSE", html, template.getAbsolutePath(), oaw, php, os);
		html = replaceBinaryTagReverse("ELSEIF", "ELSEIF", html, template.getAbsolutePath(), oaw, php, os);
		
		// create <a> links to template files
		html = replaceLinks(template, html, template.getAbsolutePath());
		
		html = "<html><style>body,html,th,td{font-family:Arial;}.none{background:#fcc;}.oaw{background:#ffc;}.php{color:green;}pre{background:#eee;border:1px solid #666;}</style><body><h1>" + template + "</h1>\n\n<a href=\"index.html\">Summary</a><ul><li class=\"none\">no execution</li><li class=\"oaw\">OAW execution</li><li class=\"php\">PHP execution</li></ul>\n\n<p><pre>" + html + "</pre></p></html>";
		
		System.out.println("Writing file '" + file + "'...");
		writeFile(file, html);
		
		return os;
		
	}

	/**
	 * Replace all EXPAND foo::bar statements with a hyperlink
	 * to the actual template.
	 * 
	 * @param html
	 * @param absolutePath
	 * @param templates
	 * @return
	 */
	protected String replaceLinks(File thisTemplate, String html, String absolutePath) {

		// create <a> links from <expand> to <define>s
		String[] bits = html.split("«EXPAND");
		html = bits[0];
		
		for (int i = 1; i < bits.length; i++) {
			String key = bits[i].substring(0, bits[i].indexOf('»'));
			String remainder = bits[i].substring(bits[i].indexOf('»') + 1);
			
			String[] keyBits = key.split("FOR", 2);
			String href = "foo";
			// remove brackets
			if (keyBits[0].contains("(")) {
				keyBits[0] = keyBits[0].substring(0, keyBits[0].indexOf("("));
			}
			if (keyBits.length == 2 && keyBits[0].contains("::")) {
				// its in a different file
				String lastKey = keyBits[0].substring(keyBits[0].lastIndexOf("::") + 2);
				href = getOutputName(findTemplateFile(thisTemplate, keyBits[0])) + '#' + generateExpandHref(lastKey);
			} else {
				// its in this file
				href = '#' + generateExpandHref(key);
			}
			
			// append
			html += "<a href=\"" + escapeHtmlQuotes(href) + "\">«EXPAND" + key + "»</a>" + remainder;
		}
		
		// now, take all <define>s and add <a name>s so they can be linked
		html = createDefineLinks(html);
		
		return html;
		
	}

	
	/**
	 * @param html
	 * @return
	 */
	private String createDefineLinks(String html) {

		// create <a> links from <expand> to <define>s
		String[] bits = html.split("«DEFINE");
		html = bits[0];
		
		for (int i = 1; i < bits.length; i++) {
			String key = bits[i].substring(0, bits[i].indexOf('»'));
			String remainder = bits[i].substring(bits[i].indexOf('»') + 1);
			
			String href = generateExpandHref(key);
			
			// append
			html += "<a name=\"" + escapeHtmlQuotes(href) + "\">«DEFINE" + key + "»</a>" + remainder;
		}
		
		return html;
	}

	/**
	 * Find the template file for the given string.
	 * 
	 * @param string ' js::Operations::expandEventTriggers'
	 * @return
	 */
	private File findTemplateFile(File thisTemplate, String string) {		
		String[] bits = string.trim().split("::");
		File start = thisTemplate;
		
		// go back to find the common root
		for (int i = 0; i < bits.length - 1; i++) {
			start = start.getParentFile();
		}
		
		// jevon hack: if the file does not contain /template, add it
		if (!bits[0].equals("template") && !start.getAbsolutePath().contains(File.separator + "template")) {
			start = new File(start.getAbsolutePath() + File.separator + "template");
		}
		
		// now add additional bits (folders and filename)
		for (int i = 0; i < bits.length - 1; i++) {
			start = new File(start.getAbsolutePath() + File.separator + bits[i]);
		}
		
		// add file extension
		start = new File(start.getAbsolutePath() + ".xpt");
		
		// this should be the result file
		return start;
	}

	/**
	 * Generate a key into a href target.
	 * @param key
	 * @return
	 */
	protected String generateExpandHref(String key) {
		// remove brackets; we can't do type inference
		if (key.contains("(")) {
			key = key.substring(0, key.indexOf("("));
		}
		// remove FOR: we can't do type inference
		if (key.contains("FOR")) {
			key = key.substring(0, key.indexOf("FOR"));
		}
		
		return getOutputName(new File(key.trim()));
	}

	/**
	 * Escape the given string to HTML
	 * 
	 * @param html
	 * @return
	 */
	protected String escapeHtml(String html) {
		return html.replace("&", "&amp;").replace(">", "&gt;").replace("<", "&lt;");
	}

	/**
	 * Escape the given string to HTML, but also quotes.
	 * 
	 * @param html
	 * @return
	 */
	protected String escapeHtmlQuotes(String html) {
		return escapeHtml(html).replace("\"", "&quot;");
	}
	
	/**
	 * @param html
	 * @param oaw
	 * @param php
	 * @param os output summary to append to
	 * @return
	 */
	private String replaceBinaryTag(String tag,
			String endtag,
			String html,
			String templateFile,
			Map<String, Map<String, Integer>> oaw,
			Map<String, Map<String, Integer>> php, OutputSummary os) {

		// find all DEFINEs
		int lineCount = 0;
		String[] bits = html.split("«" + tag);
		html = bits[0];
		lineCount = countOccurrences(bits[0], '\n') + 1;		// generated instrumentation code is out by 1
		
		if (templateFile.toLowerCase().contains("operations.xpt") && tag.equals("DEFINE")) {
			System.out.println("breakpoint");
		}
		
		for (int i = 1; i < bits.length; i++) {
			String key = tag + bits[i].substring(0, bits[i].indexOf('»'));
			key = lineCount + ":" + key;
			
			// add to output summary
			os.addBlock();
			
			// add a <span> with a "title"
			String classes = "";
			String title = "";
			if (oaw.containsKey(templateFile) && oaw.get(templateFile).containsKey(key)) {
				// it's been executed in OAW
				classes += "oaw ";
				title += oaw.get(templateFile).get(key) + " steps in OAW ";
				os.coveredInOaw();
			}
			if (php.containsKey(templateFile) && php.get(templateFile).containsKey(key)) {
				// it's been executed in OAW
				classes += "php ";
				title += php.get(templateFile).get(key) + " steps in PHP ";
				os.coveredInPhp();
			}
			if (classes.isEmpty()) {
				classes = "none";
			}
			title += "(" + templateFile + ": " + key + ")";
			html += "<span class=\"" + classes + "\" title=\"" + title + "\">";
			
			// append
			html += "«" + tag + bits[i];
			lineCount += countOccurrences(bits[i], '\n');
		}
		
		// append all ENDDEFINE with </span>
		html = html.replaceAll("«(" + endtag + "[^'»]*?)»", "«$1»</span>");
		
		return html;
		
	}
	

	/**
	 * @param html
	 * @param oaw
	 * @param php
	 * @param os output summary to append to
	 * @return
	 */
	private String replaceBinaryTagReverse(String tag,
			String endtag,
			String html,
			String templateFile,
			Map<String, Map<String, Integer>> oaw,
			Map<String, Map<String, Integer>> php, OutputSummary os) {

		// append all ENDDEFINE with </span>
		// do this replacement first
		// the </span> is also in reverse order
		html = html.replaceAll("«(" + endtag + "[^'»]*?)»", "</span>«$1»");

		// find all DEFINEs
		int lineCount = 0;
		String[] bits = html.split("«" + tag);
		html = bits[0];
		lineCount = countOccurrences(bits[0], '\n') + 1;		// generated instrumentation code is out by 1
		
		if (templateFile.toLowerCase().contains("operations.xpt") && tag.equals("DEFINE")) {
			System.out.println("breakpoint");
		}
		
		for (int i = 1; i < bits.length; i++) {
			String key = tag + bits[i].substring(0, bits[i].indexOf('»'));
			key = lineCount + ":" + key;
			
			// add to output summary
			os.addBlock();
			
			// add a <span> with a "title"
			String classes = "";
			String title = "";
			if (oaw.containsKey(templateFile) && oaw.get(templateFile).containsKey(key)) {
				// it's been executed in OAW
				classes += "oaw ";
				title += oaw.get(templateFile).get(key) + " steps in OAW ";
				os.coveredInOaw();
			}
			if (php.containsKey(templateFile) && php.get(templateFile).containsKey(key)) {
				// it's been executed in OAW
				classes += "php ";
				title += php.get(templateFile).get(key) + " steps in PHP ";
				os.coveredInPhp();
			}
			if (classes.isEmpty()) {
				classes = "none";
			}
			title += "(" + templateFile + ": " + key + ")";
			html += "<span class=\"" + classes + "\" title=\"" + title + "\">";
			
			// append
			html += "«" + tag + bits[i];
			lineCount += countOccurrences(bits[i], '\n');
		}
		
		return html;
		
	}
	
	/**
	 * @param oawDump
	 * @return
	 * @throws InstrumentationException 
	 * @throws IOException 
	 */
	protected Map<String, Map<String, Integer>> loadInstrumentation(File file) throws InstrumentationException, IOException {
		String input = readFile(file);
		String[] lines = input.split("\n");
		
		Map<String, Map<String, Integer>> result = new 
			HashMap<String, Map<String, Integer>>();
		Map<String, Integer> current = new HashMap<String, Integer>();
		String currentFilename = "";
		
		for (String line : lines) {
			if (!line.isEmpty()) {
				if (line.charAt(0) == '\t') {
					// add to current
					int pos = line.lastIndexOf(":");
					String part1 = line.substring(0, pos).trim();
					String part2 = line.substring(pos + 1).trim();
					current.put(part1, Integer.valueOf(part2.trim()));
				} else {
					// a filename; insert the current buffer into the result
					result.put(currentFilename, current);
					
					// remove the ':' at the end of the filename
					currentFilename = line.substring(0, line.length() - 1);
					current = new HashMap<String, Integer>();
				}
			}
		}
		
		// put in the last result
		result.put(currentFilename, current);
		
		return result;
	}

	/**
	 * Get the HTML filename result for the given xpt template file.
	 * 
	 * @param template
	 * @return
	 */
	protected String getOutputName(File template) {
		// get root
		File root = new File("");
		String out = template.getAbsolutePath().replace(root.getAbsolutePath() + File.separator, "");
		return out.replaceAll("[^A-Za-z0-9_\\-]", "-").replaceAll("--+", "-") + ".html";		
	}
	
}
