/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.server;

import java.util.Calendar;
import java.util.Date;

import org.openiaml.model.datatypes.BuiltinDataTypes;

/**
 * Checks casting functions.
 * 
 * @author jmwright
 *
 */
public class CastFunctions extends PhpCodegenTestCase {
	
	public static final String TYPE_STRING = BuiltinDataTypes.TYPE_STRING;
	public static final String TYPE_EMAIL = BuiltinDataTypes.TYPE_EMAIL;
	public static final String TYPE_DATETIME = BuiltinDataTypes.TYPE_DATETIME;
	public static final String TYPE_INTEGER = BuiltinDataTypes.TYPE_INTEGER;
	
	private String canCastToString(String value) {
		return "can_cast('" + value + "', '" + TYPE_STRING + "')";
	}
	private String canCastToString(long value) {
		return "can_cast(" + value + ", '" + TYPE_STRING + "')";
	}
	private String canCastToString(Date date) {
		return "can_cast(new DateTime('" + rfc2822(date) + "'), '" + TYPE_STRING + "')";
	}
	
	private String canCastToEmail(String value) {
		return "can_cast('" + value + "', '" + TYPE_EMAIL + "')";
	}
	private String canCastToEmail(long value) {
		return "can_cast(" + value + ", '" + TYPE_EMAIL + "')";
	}
	private String canCastToEmail(Date date) {
		return "can_cast(new DateTime('" + rfc2822(date) + "'), '" + TYPE_EMAIL + "')";
	}
	
	private String canCastToDate(String value) {
		return "can_cast('" + value + "', '" + TYPE_DATETIME + "')";
	}
	private String canCastToDate(long value) {
		return "can_cast(" + value + ", '" + TYPE_DATETIME + "')";
	}
	private String canCastToDate(Date date) {
		return "can_cast(new DateTime('" + rfc2822(date) + "'), '" + TYPE_DATETIME + "')";
	}
	
	private String canCastToInteger(String value) {
		return "can_cast('" + value + "', '" + TYPE_INTEGER + "')";
	}
	private String canCastToInteger(long value) {
		return "can_cast(" + value + ", '" + TYPE_INTEGER + "')";
	}
	private String canCastToInteger(Date date) {
		return "can_cast(new DateTime('" + rfc2822(date) + "'), '" + TYPE_INTEGER + "')";
	}
	
	private String doCastToString(String value) {
		return "do_cast('" + value + "', '" + TYPE_STRING + "')";
	}
	private String doCastToString(long value) {
		return "do_cast(" + value + ", '" + TYPE_STRING + "')";
	}
	private String doCastToString(Date date) {
		return "do_cast(new DateTime('" + rfc2822(date) + "'), '" + TYPE_STRING + "')";
	}
	
	private String doCastToEmail(String value) {
		return "do_cast('" + value + "', '" + TYPE_EMAIL + "')";
	}
	private String doCastToEmail(long value) {
		return "do_cast(" + value + ", '" + TYPE_EMAIL + "')";
	}
	private String doCastToEmail(Date date) {
		return "do_cast(new DateTime('" + rfc2822(date) + "'), '" + TYPE_EMAIL + "')";
	}
	
	private String doCastToDate(String value) {
		return "do_cast(do_cast('" + value + "', '" + TYPE_DATETIME + "'), '" + TYPE_STRING + "')";
	}
	private String doCastToDate(long value) {
		return "do_cast(do_cast(" + value + ", '" + TYPE_DATETIME + "'), '" + TYPE_STRING + "')";
	}
	private String doCastToDate(Date date) {
		return "do_cast(do_cast(new DateTime('" + rfc2822(date) + "'), '" + TYPE_DATETIME + "'), '" + TYPE_STRING + "')";
	}
	
	private String doCastToInteger(String value) {
		return "do_cast('" + value + "', '" + TYPE_INTEGER + "')";
	}
	private String doCastToInteger(long value) {
		return "do_cast(" + value + ", '" + TYPE_INTEGER + "')";
	}
	private String doCastToInteger(Date date) {
		return "do_cast(new DateTime('" + rfc2822(date) + "'), '" + TYPE_INTEGER + "')";
	}
	
	private Date TODAY = new Date();
	private Date EPOCH = new Date(0);
	
	public void testCanCastToString() throws Exception {
		assertPhpResult(true, canCastToString("Hello, world!"));
		assertPhpResult(true, canCastToString(""));
		assertPhpResult(true, canCastToString(42));
		assertPhpResult(true, canCastToString(0));
		assertPhpResult(true, canCastToString(TODAY));
	}

	public void testCanCastToEmail() throws Exception {
		assertPhpResult(false, canCastToEmail("Hello, world!"));
		assertPhpResult(true, canCastToEmail(""));
		assertPhpResult(false, canCastToEmail(42));
		assertPhpResult(false, canCastToEmail(0));
		assertPhpResult(true, canCastToEmail("hello@world.com"));
		assertPhpResult(false, canCastToEmail(TODAY));
	}

	public void testCanCastToDateTime() throws Exception {
		assertPhpResult(true, canCastToDate("dec 25, 1995"));
		assertPhpResult(false, canCastToDate(""));
		assertPhpResult(true, canCastToDate(42));
		assertPhpResult(true, canCastToDate(0));
		assertPhpResult(false, canCastToDate((long) 2147483647 + 1024));
		assertPhpResult(true, canCastToDate(TODAY));
	}

	public void testCanCastToInteger() throws Exception {
		assertPhpResult(false, canCastToInteger("Hello, world!"));
		assertPhpResult(true, canCastToInteger(""));
		assertPhpResult(true, canCastToInteger(42));
		assertPhpResult(true, canCastToInteger("42"));
		assertPhpResult(false, canCastToInteger("42.1"));
		assertPhpResult(true, canCastToInteger(0));
		assertPhpResult(true, canCastToInteger("0"));
		assertPhpResult(false, canCastToInteger((long) 2147483647 + 1024));
		assertPhpResult(true, canCastToInteger(TODAY));
	}
	
	public void testDoCastToString() throws Exception {
		assertPhpResult("Hello, world!", doCastToString("Hello, world!"));
		assertPhpResult("", doCastToString(""));
		assertPhpResult("42", doCastToString(42));
		assertPhpResult("0", doCastToString(0));
		assertPhpResult(rfc2822(TODAY), doCastToString(TODAY));
	}

	public void testDoCastToEmail() throws Exception {
		assertPhpResult("", doCastToEmail("Hello, world!"));
		assertPhpResult("", doCastToEmail(""));
		assertPhpResult("", doCastToEmail(42));
		assertPhpResult("", doCastToEmail(0));
		assertPhpResult("hello@world.com", doCastToEmail("hello@world.com"));
		assertPhpResult("", doCastToEmail(TODAY));
	}

	public void testDoCastToDateTime() throws Exception {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(1995, 11, 25);
		Date specific = c.getTime();

		assertPhpResult(rfc2822(specific), doCastToDate("dec 25, 1995"));
		assertPhpResult(rfc2822(EPOCH), doCastToDate(""));
		assertPhpResult(rfc2822(new Date(42 * 1000)), doCastToDate(42));
		assertPhpResult(rfc2822(EPOCH), doCastToDate(0));
		assertPhpResult(rfc2822(EPOCH), doCastToDate((long) 2147483647 + 1024));
		assertPhpResult(rfc2822(TODAY), doCastToDate(TODAY));
	}

	public void testDoCastToInteger() throws Exception {
		assertPhpResult(0, doCastToInteger("Hello, world!"));
		assertPhpResult(0, doCastToInteger(""));
		assertPhpResult(42, doCastToInteger(42));
		assertPhpResult(42, doCastToInteger("42"));
		assertPhpResult(42, doCastToInteger("42.1"));
		assertPhpResult(0, doCastToInteger(0));
		assertPhpResult(0, doCastToInteger("0"));
		assertPhpResult(0, doCastToInteger((long) 2147483647 + 1024));
		assertPhpResult(TODAY.getTime() / 1000, doCastToInteger(TODAY));
	}
	

}
