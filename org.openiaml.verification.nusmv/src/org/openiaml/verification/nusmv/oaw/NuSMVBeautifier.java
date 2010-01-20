/**
 * 
 */
package org.openiaml.verification.nusmv.oaw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.openarchitectureware.xpand2.output.FileHandle;
import org.openiaml.iacleaner.inline.InlineStringReader;
import org.openiaml.iacleaner.inline.InlineStringWriter;

/**
 * @author Jevon
 *
 */
public class NuSMVBeautifier implements org.openarchitectureware.xpand2.output.PostProcessor {
	
	private static IProgressMonitor monitor = null;

	public static IProgressMonitor getMonitor() {
		return monitor;
	}

	public static void setMonitor(IProgressMonitor monitor) {
		NuSMVBeautifier.monitor = monitor;
	}
	
	/**
	 * Should we make a backup of the initial file in '.old'?
	 * 
	 * @return
	 */
	public boolean makeBackup() {
		return false;
	}

	/**
	 * We use the IACleaner to format the file.
	 * 
	 * @see org.openarchitectureware.xpand2.output.PostProcessor#afterClose(org.openarchitectureware.xpand2.output.FileHandle)
	 * @throws RuntimeException if an IOException or CleanerException occurs while trying to format the file
	 */
	public void afterClose(FileHandle file) {
		try {
			// early bail?
			if (monitor != null && monitor.isCanceled())
				throw new OperationCanceledException();
			
			// make backup?
			if (makeBackup()) {
				File backup = new File( file.getTargetFile().getAbsolutePath() + ".old" );
				FileWriter fw = new FileWriter( backup );
				fw.write( readFile(file.getTargetFile()) );
				fw.close();
			}
			
			String out = cleanNuSMV( readFile(file.getTargetFile()) );
			
			// rewrite the file
			FileWriter fw2 = new FileWriter(file.getTargetFile());
			fw2.write(out);
			fw2.close();
			
		} catch (IOException e) {
			throw new RuntimeException("[" + file.getTargetFile() + "] IO Exception during prettifier: " + e.getMessage(), e);

		} finally {
			// reset the monitor
			monitor = null;
		}
		
	}
	
	/**
	 * Format the input SMV file into a cleaned output file.
	 * 
	 * @param readFile
	 * @return
	 */
	public String cleanNuSMV(String input) throws IOException {
		InlineStringReader reader = new InlineStringReader(new StringReader(input)) {

			@Override
			protected void throwWarning(String message, String buffer) {
				throw new RuntimeException("Warning message: " + message);
			}
			
		};
		InlineStringWriter writer = new InlineStringWriter() {

			@Override
			protected void throwWarning(String message, String buffer) {
				throw new RuntimeException("Warning message: " + message);
			}
			
		};
		
		int c;
		int prev = -1;
		boolean inLTL = false;
		while ((c = reader.read()) != -1) {
			// skip \rs
			if (c == '\r') continue;
			
			if (Character.isWhitespace(c) /* && Character.isWhitespace(prev) */) {
				// skip
			} else if (c == '-' && reader.readAhead() == '-') {
				if (prev != -1 && !Character.isWhitespace(writer.previous())) {
					// we need to add whitespace before this comment
					writer.write(' ');
				}
				
				// a comment
				writer.write('-');
				writer.enableWordwrap(false);	// disable wordwrap for comment
				while ((c = reader.read()) != -1) {
					if (c == '\r')
						continue;

					// copy comment directly
					writer.write(c);
					
					if (c == '\n')
						break;
				}
				writer.enableWordwrap(true);	// re-enable wordwrap for comment
			} else if (c == 'p' && !Character.isJavaIdentifierPart(prev) && reader.has(7) && reader.readAhead(6).equals("rocess") && !Character.isJavaIdentifierPart(reader.readAhead(7).charAt(6))) {
				// 'process' keyword
				writer.write(c);
				writer.write(reader.read(6));
				writer.write(' ');
				prev = ' ';
			} else if (c == 'M' && reader.has(6) && reader.readAhead(5).equals("ODULE")) {
				// multiple modules will reduce the indent, since MODULE
				// does not have an ending statement
				if (writer.getIndentSize() != 0)
					writer.indentDecrease();
				
				// MODULE
				writer.newLine();
				writer.write(c);
				writer.write(reader.read(5));
				writer.write(' ');
			} else if (c == 'V' && reader.has(3) && reader.readAhead(2).equals("AR")) {
				// VAR
				writer.newLine();
				writer.newLine();
				writer.write(c);
				writer.write(reader.read(2));
				writer.newLine();
				
				// indent
				writer.indentIncrease();
				c = ' ';
			} else if (c == 'A' && reader.has(6) && reader.readAhead(5).equals("SSIGN")) {
				// unindent
				writer.indentDecrease();
				
				// ASSIGN
				writer.newLine();
				writer.write(c);
				writer.write(reader.read(5));
				writer.newLine();
				
				// indent
				writer.indentIncrease();
				c = ' ';
			} else if (c == 'F' && reader.has(8) && reader.readAhead(7).equals("AIRNESS")) {
				// unindent
				writer.indentDecrease();
				
				// ASSIGN
				writer.newLine();
				writer.write(c);
				writer.write(reader.read(7));
				writer.newLine();
				
				// indent
				writer.indentIncrease();
				c = ' ';
			} else if (c == 'L' && reader.has(7) && reader.readAhead(6).equals("TLSPEC")) {
				// unindent
				writer.indentDecrease();

				// LTLSPEC
				writer.newLine();
				writer.write(c);
				writer.write(reader.read(6));
				writer.newLine();
				
				// indent
				writer.indentIncrease();
				c = ' ';
				
				inLTL = true;	// now in LTL space
			} else if (c == 'c' && reader.has(4) && reader.readAhead(3).equals("ase") && Character.isWhitespace(reader.readAhead(4).charAt(3))) { 
				// starting a case statement
				writer.write(c);
				writer.write(reader.read(3));
				writer.newLine();
				
				// indent
				writer.indentIncrease();
				c = ' ';
				
			} else if (c == 'e' &&  reader.has(4) && reader.readAhead(3).equals("sac") && 
					(Character.isWhitespace(reader.readAhead(4).charAt(3)) || reader.readAhead(4).charAt(3) == ';')) { 
				// no newline, but decrease indent before writing 'esac;'
				writer.indentDecrease();

				// ending a case statement
				writer.write(c);
				writer.write(reader.read(3));
				
				c = 'c';
				
			} else if (needsWhitespaceBefore(c, reader, writer, inLTL) && !Character.isWhitespace(writer.previous())) {
				// insert whitespace
				writer.write(' ');
			
				// copy normally
				writer.write(c);
				
			} else {
					
				// copy normally
				writer.write(c);
			}
			
			if (needsNewLine(c)) {
				// write possible newline
				writer.newLineMaybe();
			} else if (needsWhitespaceAfter(c, reader, writer, inLTL)) {
				// insert whitespace
				writer.write(' ');
			}
			
			prev = c;
		}
		
		return writer.getBuffer().toString();
	}

	/**
	 * @param c
	 * @return
	 */
	protected boolean needsNewLine(int c) {
		return (c == ';');
	}

	/**
	 * @param prev
	 * @return
	 * @throws IOException 
	 */
	protected boolean needsWhitespaceAfter(int prev, InlineStringReader reader, InlineStringWriter writer, boolean inLTL) throws IOException {
		return (prev == ':' && reader.readAhead() != '=')
			|| (prev == '+')
			|| (prev == '-' && reader.readAhead() != '>')
			|| (prev == '*')
			|| (prev == '/')
			|| (prev == ',') 
			|| (prev == '=')
			|| (prev == 'd' && !Character.isJavaIdentifierPart(reader.readAhead()) && writer.getLastWritten(3).equals("mod") && Character.isWhitespace(writer.getLastWritten(4).charAt(0)))
			|| (inLTL && isLTLOperator(prev))
			;
	}

	/**
	 * @param c
	 * @return
	 * @throws IOException 
	 */
	protected boolean needsWhitespaceBefore(int c, InlineStringReader reader, InlineStringWriter writer, boolean inLTL) throws IOException {
		return (c == '(' && writer.previous() != '(' && writer.previous() != '!') 
			|| (c == '!' && writer.previous() != '(') 
			|| (writer.previous() != '(' && 
					(c == '+' || c == '-' || c == '*' || c == '/' 
						|| (c == 'm' && reader.has(3) && reader.readAhead(2).equals("od") && !Character.isJavaIdentifierPart(reader.readAhead(3).charAt(2)))))
			|| (inLTL && isLTLOperator(c) && writer.previous() != '(' && writer.previous() != '!')
			|| (c == ':') 
			|| (c == '{') 
			|| (c == '=' && writer.previous() != '!' && writer.previous() != ':')
			|| (c == '-' && reader.readAhead() == '>');
	}

	protected boolean isLTLOperator(int prev) {
		return (prev == 'F' || prev == 'G' || prev == 'X' || prev == 'U' || prev == 'R' || prev == 'W');
	}
	
	/**
	 * Format the input SMV file into a cleaned output file.
	 * 
	 * @param readFile
	 * @return
	 * @throws IOException 
	 */
	public String cleanNuSMV(File readFile) throws IOException {
		return cleanNuSMV(readFile(readFile));
	}

	/* (non-Javadoc)
	 * @see org.openarchitectureware.xpand2.output.PostProcessor#beforeWriteAndClose(org.openarchitectureware.xpand2.output.FileHandle)
	 */
	public void beforeWriteAndClose(FileHandle file) {
		// ignore	
	}


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
	
}
