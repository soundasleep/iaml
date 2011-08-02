/**
 * 
 */
package org.openiaml.docs.generation.codegen;


/**
 * @author jmwright
 *
 */
public class LatexCodegenFunctions {
	
	/**
	 * Turn a string like "InputTextField" into "Input Text Field".
	 * 
	 * @param s InputTextField
	 * @return Input Text Field
	 */
	public static String humanise(String s) {		
		StringBuffer buf = new StringBuffer();
		char prev = 0;
		int pos = 0;
		char[] data = s.trim().toCharArray();
		for (char c : data) {
			if (Character.isUpperCase(c) && buf.length() != 0) {
				if (prev == 0 || !Character.isUpperCase(prev)) {
					buf.append(' ');
				}
			
				// is the next character uppercase too?
				else if (pos > 1 && data.length > pos && !Character.isUpperCase(data[pos + 1])) {
					buf.append(' ');
				}
			}
			buf.append(c);
			prev = c;
			pos++;
		}
		
		return buf.toString();
	}
	
	/**
	 * Turn a string like "InputTextField" into "input-text-field".
	 * 
	 * @param s InputTextField
	 * @return input-text-field
	 */
	public static String latexise(String s) {
		return humanise(s).toLowerCase().replace(' ', '-');
	}
	
	/**
	 * Turn a string like "input.text.field" into "inputTextField".
	 * 
	 * @param s input.text.field
	 * @return inputTextField
	 */
	public static String camelcase(String s) {
		// split up based on "."s
		String[] bits = s.split("\\.");
		StringBuffer buf = new StringBuffer(bits[0]);
		for (int i = 1; i < bits.length; i++) {
			if (bits[i].length() > 0) {
				buf.append(Character.toUpperCase(bits[i].charAt(0)));
				if (bits[i].length() > 1) {
					buf.append(bits[i].substring(1));
				}
			}
		}

		return buf.toString();
	}
	/**
	 * Turns a string - which may contain HTML - into Latex
	 * code. In particular, it changes:
	 * 
	 * <ol>
	 * 	<li> <code>&lt;code&gt;AAA&lt;/code&gt;</code> into <code>\code{AAA}</code>
	 * 	<li> <code>&amp;lt;</code> into <code>&lt;</code>
	 * 	<li> <code>&amp;gt;</code> into <code>&gt;</code>
	 * 	<li> <code>&amp;amp;</code> into <code>\&amp;</code>
	 * 	<li> <code>_</code> into <code>\_</code>
	 * </ol>
	 * 
	 * @param s
	 * @return
	 */
	public static String convertHTMLIntoLatex(String s) {
		return s.replaceAll("(?i)<code>", "\\\\code{")
			.replaceAll("(?i)</code>", "}")
			.replaceAll("(?i)<em>", "\\\\textit{")
			.replaceAll("(?i)</em>", "}")
			.replaceAll("(?i)<i>", "\\\\textit{")
			.replaceAll("(?i)</i>", "}")
			.replaceAll("(?i)<b>", "\\\\textbf{")
			.replaceAll("(?i)</b>", "}")
			.replace("&lt;", "<")
			.replace("&gt;", ">")
			.replace("&amp;", "\\&")
			.replace("_", "\\_");
	}
	
}
