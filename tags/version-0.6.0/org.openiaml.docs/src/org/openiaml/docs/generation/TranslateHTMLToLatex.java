/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.openiaml.docs.generation.codegen.LatexCodegenFunctions;

/**
 * @author jmwright
 *
 */
public class TranslateHTMLToLatex {

	/**
	 * Read in a file into a string.
	 * 
	 * @throws IOException if an IO exception occurs
	 */
	public static String readFile(File sourceFile) throws IOException {
		return readFile(new FileReader(sourceFile));
	}
	
	/**
	 * Read in an InputStream into a string.
	 * 
	 * @throws IOException if an IO exception occurs
	 */
	public static String readFile(InputStream stream) throws IOException {
		return readFile(new InputStreamReader(stream));
	}
	
	/**
	 * Read in a Reader into a string.
	 * 
	 * @throws IOException if an IO exception occurs
	 */
	public static String readFile(Reader sourceReader) throws IOException {
		int bufSize = 128;
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = new BufferedReader(sourceReader, bufSize);
		
		char[] chars = new char[bufSize];
		int numRead = 0;
		while ((numRead = reader.read(chars)) != -1) {
			sb.append(chars, 0, numRead);
		}

		reader.close();
		return sb.toString();
	}

	/**
	 * Convert the given file into a LaTeX format, and return the
	 * result as a String.
	 * 
	 * @param inputFile
	 * @return
	 * @throws IOException 
	 * @throws LatexTranslationException if there is an exception during translation
	 */
	public String convertToTex(File inputFile) throws IOException, LatexTranslationException {
		String f = readFile(inputFile);
		
		// remove \r's
		f = f.replace("\r", "");
		
		// double newlines are ignored
		f = f.replaceAll("\n+", "\n");
		
		// remove any [noLaTeX] sections
		f = f.replaceAll("(?s) *<\\!--\\s*noLaTeX\\s*-->(.+)<\\!--\\s*/noLaTeX\\s*--> *", "% ignored section\n\n");

		// specifically enable LaTex sections
		f = f.replaceAll("(?s) *<\\!--\\s*LaTeX(.+)--> *", "$1");

		// keep indexes
		f = f.replaceAll("<!--index ([^>]+)-->", "\\\\index{$1}");
		
		// remove any other HTML comments
		f = f.replaceAll("(?s) *<\\!--([^>]+)--> *", "");

		// replace <p> with two newlines
		f = f.replace("<p>", "\n\n");
		f = f.replace("</p>", "");
		
		// {@model} tags are humanised and replaced
		// NOTE: this logic repeats the JavaDoc-style parsing used in 
		// LoadEMFDescription, and in the future these two approaches
		// should probably be merged
		String[] modelBits = f.split("\\{@model ");
		StringBuffer buf = new StringBuffer();
		buf.append(modelBits[0]);
		for (int i = 1 ; i < modelBits.length; i++) {
			String bit = modelBits[i];
			int beforeBrace = bit.indexOf('}');
			if (beforeBrace == -1)
				throw new LatexTranslationException("Could not close @model tag: '" + bit + "'");
			
			String modelElementName = bit.substring(0, beforeBrace);
			// there cannot be any spaces in the modelElementName, e.g. for '{@model Element description}'
			if (modelElementName.contains(" ")) {
				throw new LatexTranslationException("A @model tag cannot contain a textual description if it needs to be translated to LaTeX: " + modelElementName);
			}
			
			// is it a property name, e.g. Foo#bar?
			String[] properties = modelElementName.split("#", 2);
			
			// is there a 's' after the brace?
			boolean sAfterBrace = beforeBrace < bit.length() && 
				bit.charAt(beforeBrace + 1) == 's';

			if (properties.length == 2)
				buf.append("\\modelProperty{");
			else
				buf.append("\\modelLink{");
			buf.append(LatexCodegenFunctions.humanise(properties[0]));
			if (properties.length == 2) {
				buf.append("}{");
				buf.append(properties[1]);
			}
			if (sAfterBrace)
				buf.append("s");
			buf.append("}");
			if (sAfterBrace)
				buf.append(bit.substring(beforeBrace + 2));
			else
				buf.append(bit.substring(beforeBrace + 1));
		}
		f = buf.toString();
		
		// other inline tag references are much more straightforward
		f = f.replaceAll("\\{@issue ([^}]+)\\}", "\\\\issue{$1}");
		f = f.replaceAll("\\{@uml ([^}]+)\\}", "\\\\uml{$1}");
		f = f.replaceAll("\\{@type ([^}]+)\\}", "\\\\type{$1}");
		f = f.replaceAll("\\{@event ([^}]+)\\}", "\\\\event{$1}");
		f = f.replaceAll("\\{@emf ([^}]+)\\}", "\\\\emf{$1}");
		
		// magic translations for tables
		if (f.contains("<table>"))
			throw new LatexTranslationException("<table>s need to have an attribute 'latexAlign', in order to translate the table into LaTeX");
		if (f.contains("<td></td>"))
			throw new LatexTranslationException("A table cannot contain an empty table cell, i.e. <td></td>");
		if (f.contains("<th></th>"))
			throw new LatexTranslationException("A table cannot contain an empty table cell, i.e. <th></th>");
		f = f.replaceAll("<table latexAlign=\"([^\"]+)\">", "\n\\\\begin{modeldocTable}\n\\\\begin{tabularx}{\\\\modeldocTableWidth}{$1}");
		f = f.replace("</table>", "\\hline\n\\end{tabularx}\n\\end{modeldocTable}");
		f = f.replace("<thead>", "");
		f = f.replace("</thead>", "\\hline");
		f = f.replace("<tbody>", "");
		f = f.replace("</tbody>", "");
		f = f.replace("<tr>", "\\hline");
		f = f.replace("</tr>", "\\\\\n");
		f = f.replace("<td>", "");
		f = f.replace("</td>", "&");
		f = f.replace("<th>", "\\textbf{");
		f = f.replace("</th>", "}&");
		
		// headings
		f = f.replace("<h1>", "\n\n\\section{");
		f = f.replace("</h1>", "}");
		f = f.replace("<h2>", "\n\n\\subsection{");
		f = f.replace("</h2>", "}");
		f = f.replace("<h3>", "\n\n\\subsubsection{");
		f = f.replace("</h3>", "}");
		f = f.replace("<h4>", "\n\n\\subsubsubsection{");
		f = f.replace("</h4>", "}");
		
		// lists
		f = f.replace("<ol>", "\n\\begin{enumerate}");
		f = f.replace("</ol>", "\\end{enumerate}\n");
		f = f.replace("<ul>", "\n\\begin{itemize}");
		f = f.replace("</ul>", "\\end{itemize}\n");
		f = f.replace("<dl>", "\n\\begin{description}");
		f = f.replace("</dl>", "\\end{description}\n");
		f = f.replace("<dt>", "\\item[");
		f = f.replace("</dt>", "]");
		f = f.replace("<dd>", "");
		f = f.replace("</dd>", "");
		f = f.replace("<li>", "\\item ");
		f = f.replace("</li>", "");
				
		// get rid of right-most cells
		// assumes that there are no empty <td>s in the table
		f = f.replaceAll("&[\\s*]\\\\", "\\\\");
		
		// additional functions provided by convertHTMLIntoLatex
		f = LatexCodegenFunctions.convertHTMLIntoLatex(f);

		// remove any excess newlines and trim
		f = f.replaceAll("\n\n+", "\n\n");
		f = f.trim();
		
		return f;
	}
	
	/**
	 * Something unusual happened during translation.
	 * 
	 * @author jmwright
	 *
	 */
	public class LatexTranslationException extends Exception {

		private static final long serialVersionUID = 1L;

		public LatexTranslationException(String string) {
			super(string);
		}
		
	}

}
