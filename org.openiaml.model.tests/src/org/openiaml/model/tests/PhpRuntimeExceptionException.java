/**
 * 
 */
package org.openiaml.model.tests;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * When a PHP exception occurs (<code>exception.php?fail=[trace]</code>),
 * we may be able to directly extract the failure trace.
 * 
 * This exception also prints out the location in the PHP source 
 * of the exception, as per {@link #convertToStackTrace(String)}.
 * 
 * @author jmwright
 */
public class PhpRuntimeExceptionException extends FailingHttpStatusCodeException {

	private static final long serialVersionUID = 1L;
	private String message;
	
	/**
	 * @param f the original exception
	 * @param message the PHP message of the exception
	 */
	public PhpRuntimeExceptionException(FailingHttpStatusCodeException f,
			String message) {
		super(f.getResponse());
		
		this.message = message;
		initCause(new PhpRuntimeException(message, f));
	}

	/**
	 * @param f the original exception
	 */
	public PhpRuntimeExceptionException(FailingHttpStatusCodeException f) {
		this(f, extractTrace(f));
	}

	@Override
	public String getMessage() {
		return "PHP server-side exception: " + message;
	}
	
	/**
	 * Can we extract a PHP stack trace from the given exception?
	 * 
	 * @param f
	 * @return
	 */
	public static boolean canHandle(FailingHttpStatusCodeException f) {
		return extractTrace(f) != null;
	}
	
	/**
	 * Extract out the PHP stack trace from the given exception, or 
	 * return <code>null</code> if none can be extracted.
	 * 
	 * @param f
	 * @return
	 */
	public static String extractTrace(FailingHttpStatusCodeException f) {
		if (f.getResponse() != null && f.getResponse().getRequestUrl() != null) {
			String url = f.getResponse().getRequestUrl().toString();
			if (url.contains("exception.php?fail=")) {
				String trace = url.substring(url.indexOf("exception.php?fail=") + "exception.php?fail=".length());
				// remove any appending '&'s
				if (trace.contains("&")) {
					trace = trace.substring(0, trace.indexOf("&")); 
				}
				// convert from urlencoding into a string
				String converted;
				try {
					converted = URLDecoder.decode(trace, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException("UTF-8 was an unsupported encoding: " + e.getMessage(), e);
				}
				return converted;
			}
		}
		return null;
	}
	
	/**
	 * Extract out the cause (the trace) from the PHP exception,
	 * as part of the URL: <code>...&trace=[trace]</code>
	 * The trace is stored URLencoded.
	 *  
	 * @param f
	 * @return the extracted trace, or null
	 */
	public static StackTraceElement[] extractCause(FailingHttpStatusCodeException f) {
		if (f.getResponse() != null && f.getResponse().getRequestUrl() != null) {
			String url = f.getResponse().getRequestUrl().toString();
			if (url.contains("&trace=")) {
				String trace = url.substring(url.indexOf("&trace=") + "&trace=".length());
				// remove any appending '&'s
				if (trace.contains("&")) {
					trace = trace.substring(0, trace.indexOf("&")); 
				}
				// convert from urlencoding into a string
				String converted;
				try {
					converted = URLDecoder.decode(trace, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException("UTF-8 was an unsupported encoding: " + e.getMessage(), e);
				}
				return convertToStackTrace(converted);
			}
		}
		return new StackTraceElement[] {}; // empty
	}
	
	/**
	 * <p>
	 * Extract the PHP trace (decoded from URL) into a series of 
	 * stack trace elements.
	 * In our case, the cause is stored in the following format:
	 * </p>
	 * 
	 * <p>
	 * <code>#1 C:\...\test.NewInstanceWithoutId.0908061503090\output\visual_122ed49bb14_7.php(223): get_new_domain_object_model_122ed49ba78_70()</code>
	 * </p>
	 *
	 * <p>A sensible implementation would use or implement a real parser, this
	 * is just a quick implementation.</p>
	 *
	 * @param converted
	 * @return
	 */
	private static StackTraceElement[] convertToStackTrace(String converted) {
		String[] lines = converted.split("\n");
		
		List<StackTraceElement> result = new ArrayList<StackTraceElement>();
		for (int i = 0; i < lines.length; i++) {
			String[] bits = lines[i].split("\\): ", 2);
			if (bits.length != 2)
				continue;
			String file = bits[0].substring(bits[0].indexOf(" ") + 1);
			String function = bits[1];
			
			// remove line number
			String lineNumber = file.substring(file.lastIndexOf('(') + 1);
			int line = Integer.valueOf(lineNumber);
			file = file.substring(0, file.lastIndexOf('('));
			
			// get only the filename
			/*
			if (file.contains(File.separator)) {
				file = file.substring(file.lastIndexOf(File.separator) + 1);
			}
			*/
			
			// remove arguments
			function = function.substring(0, function.indexOf('('));
			
			result.add(new StackTraceElement("<root>", function, file, line));			
		}
		
		return result.toArray(new StackTraceElement[]{});
	}
	
	/**
	 * A wrapper class for representing PHP exceptions.
	 * 
	 * @author jmwright
	 */
	private static class PhpRuntimeException extends RuntimeException {
		private String message;
		
		public PhpRuntimeException(String message, FailingHttpStatusCodeException cause) {
			initCause(cause);
			setStackTrace(extractCause(cause));
			this.message = message;
		}

		private static final long serialVersionUID = 1L;
		
		@Override
		public String getMessage() {
			return message;
		}
	}
	
	
}
