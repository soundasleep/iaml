/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.client;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Checks casting functions.
 * 
 * @author jmwright
 *
 */
public class CastFunctions extends JavascriptCodegenTestCase {
	
	public static final String TYPE_STRING = "http://openiaml.org/model/datatypes#iamlString";
	public static final String TYPE_EMAIL = "http://openiaml.org/model/datatypes#iamlEmail";
	public static final String TYPE_DATETIME = "http://openiaml.org/model/datatypes#iamlDateTime";
	public static final String TYPE_INTEGER = "http://openiaml.org/model/datatypes#iamlInteger";
	
	/**
	 * Example: <code>Fri, 13 Dec 1901 20:45:52 +0000</code>
	 */
	private static final String RFC_2822 = "EEE', 'd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z";
	
	private String canCastToString(String value) {
		return "can_cast('" + value + "', '" + TYPE_STRING + "')";
	}
	private String canCastToString(long value) {
		return "can_cast(" + value + ", '" + TYPE_STRING + "')";
	}
	private String canCastToString(Date date) {
		return "can_cast(new Date(" + (date.getTime()) + "), '" + TYPE_STRING + "')";
	}
	
	private String canCastToEmail(String value) {
		return "can_cast('" + value + "', '" + TYPE_EMAIL + "')";
	}
	private String canCastToEmail(long value) {
		return "can_cast(" + value + ", '" + TYPE_EMAIL + "')";
	}
	private String canCastToEmail(Date date) {
		return "can_cast(new Date(" + (date.getTime()) + "), '" + TYPE_EMAIL + "')";
	}
	
	private String canCastToDate(String value) {
		return "can_cast('" + value + "', '" + TYPE_DATETIME + "')";
	}
	private String canCastToDate(long value) {
		return "can_cast(" + value + ", '" + TYPE_DATETIME + "')";
	}
	private String canCastToDate(Date date) {
		return "can_cast(new Date(" + (date.getTime()) + "), '" + TYPE_DATETIME + "')";
	}
	
	private String canCastToInteger(String value) {
		return "can_cast('" + value + "', '" + TYPE_INTEGER + "')";
	}
	private String canCastToInteger(long value) {
		return "can_cast(" + value + ", '" + TYPE_INTEGER + "')";
	}
	private String canCastToInteger(Date date) {
		return "can_cast(new Date(" + (date.getTime()) + "), '" + TYPE_INTEGER + "')";
	}
	
	private String doCastToString(String value) {
		return "do_cast('" + value + "', '" + TYPE_STRING + "')";
	}
	private String doCastToString(long value) {
		return "do_cast(" + value + ", '" + TYPE_STRING + "')";
	}
	private String doCastToString(Date date) {
		return "do_cast(new Date(" + (date.getTime()) + "), '" + TYPE_STRING + "')";
	}
	
	private String doCastToEmail(String value) {
		return "do_cast('" + value + "', '" + TYPE_EMAIL + "')";
	}
	private String doCastToEmail(long value) {
		return "do_cast(" + value + ", '" + TYPE_EMAIL + "')";
	}
	private String doCastToEmail(Date date) {
		return "do_cast(new Date(" + (date.getTime()) + "), '" + TYPE_EMAIL + "')";
	}
	
	private String doCastToDate(String value) {
		return "do_cast(do_cast('" + value + "', '" + TYPE_DATETIME + "'), '" + TYPE_STRING + "')";
	}
	private String doCastToDate(long value) {
		return "do_cast(do_cast(" + value + ", '" + TYPE_DATETIME + "'), '" + TYPE_STRING + "')";
	}
	private String doCastToDate(Date date) {
		return "do_cast(do_cast(new Date(" + (date.getTime()) + "), '" + TYPE_DATETIME + "'), '" + TYPE_STRING + "')";
	}
	
	private String doCastToInteger(String value) {
		return "do_cast('" + value + "', '" + TYPE_INTEGER + "')";
	}
	private String doCastToInteger(long value) {
		return "do_cast(" + value + ", '" + TYPE_INTEGER + "')";
	}
	private String doCastToInteger(Date date) {
		return "do_cast(new Date(" + (date.getTime()) + "), '" + TYPE_INTEGER + "')";
	}
	
	private Date TODAY = new Date();
	private Date EPOCH = new Date(0);
	
	public void testCanCastToString() throws Exception {
		assertJavascriptResult(true, canCastToString("Hello, world!"));
		assertJavascriptResult(true, canCastToString(""));
		assertJavascriptResult(true, canCastToString(42));
		assertJavascriptResult(true, canCastToString(0));
		assertJavascriptResult(true, canCastToString(TODAY));
	}

	public void testCanCastToEmail() throws Exception {
		assertJavascriptResult(false, canCastToEmail("Hello, world!"));
		assertJavascriptResult(true, canCastToEmail(""));
		assertJavascriptResult(false, canCastToEmail(42));
		assertJavascriptResult(false, canCastToEmail(0));
		assertJavascriptResult(true, canCastToEmail("hello@world.com"));
		assertJavascriptResult(false, canCastToEmail(TODAY));
	}

	public void testCanCastToDateTime() throws Exception {
		assertJavascriptResult(true, canCastToDate("dec 25, 1995"));
		assertJavascriptResult(false, canCastToDate(""));
		assertJavascriptResult(true, canCastToDate(42));
		assertJavascriptResult(true, canCastToDate(0));
		assertJavascriptResult(false, canCastToDate((long) 2147483647 + 1024));
		assertJavascriptResult(true, canCastToDate(TODAY));
	}

	public void testCanCastToInteger() throws Exception {
		assertJavascriptResult(false, canCastToInteger("Hello, world!"));
		assertJavascriptResult(true, canCastToInteger(""));
		assertJavascriptResult(true, canCastToInteger(42));
		assertJavascriptResult(true, canCastToInteger("42"));
		assertJavascriptResult(false, canCastToInteger("42.1"));
		assertJavascriptResult(true, canCastToInteger(0));
		assertJavascriptResult(true, canCastToInteger("0"));
		assertJavascriptResult(false, canCastToInteger((long) 2147483647 + 1024));
		assertJavascriptResult(true, canCastToInteger(TODAY));
	}
	
	/**
	 * Convert the given date to RFC 2822 format, represented in UTC.
	 */
	private String rfc2822(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat(RFC_2822, Locale.US);
		fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
		return fmt.format(date);
	}
	
	public void testDoCastToString() throws Exception {
		assertJavascriptResult("Hello, world!", doCastToString("Hello, world!"));
		assertJavascriptResult("", doCastToString(""));
		assertJavascriptResult("42", doCastToString(42));
		assertJavascriptResult("0", doCastToString(0));
		assertJavascriptResult(rfc2822(TODAY), doCastToString(TODAY));
	}

	public void testDoCastToEmail() throws Exception {
		assertJavascriptResult("", doCastToEmail("Hello, world!"));
		assertJavascriptResult("", doCastToEmail(""));
		assertJavascriptResult("", doCastToEmail(42));
		assertJavascriptResult("", doCastToEmail(0));
		assertJavascriptResult("hello@world.com", doCastToEmail("hello@world.com"));
		assertJavascriptResult("", doCastToEmail(TODAY));
	}

	public void testDoCastToDateTime() throws Exception {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(1995, 11, 25);
		Date specific = c.getTime();

		assertJavascriptResult(rfc2822(specific), doCastToDate("dec 25, 1995"));
		assertJavascriptResult(rfc2822(EPOCH), doCastToDate(""));
		assertJavascriptResult(rfc2822(new Date(42 * 1000)), doCastToDate(42));
		assertJavascriptResult(rfc2822(EPOCH), doCastToDate(0));
		assertJavascriptResult(rfc2822(EPOCH), doCastToDate((long) 2147483647 + 1024));
		assertJavascriptResult(rfc2822(TODAY), doCastToDate(TODAY));
	}

	public void testDoCastToInteger() throws Exception {
		assertJavascriptResult(0, doCastToInteger("Hello, world!"));
		assertJavascriptResult(0, doCastToInteger(""));
		assertJavascriptResult(42, doCastToInteger(42));
		assertJavascriptResult(42, doCastToInteger("42"));
		assertJavascriptResult(42, doCastToInteger("42.1"));
		assertJavascriptResult(0, doCastToInteger(0));
		assertJavascriptResult(0, doCastToInteger("0"));
		assertJavascriptResult((long) 2147483647 + 1024, doCastToInteger((long) 2147483647 + 1024));
		assertJavascriptResult(TODAY.getTime() / 1000, doCastToInteger(TODAY));
	}
	

}
