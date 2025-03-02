package org.chlebsearch.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

/**
 * Helper utilities for testing Lambda functions.
 */
public class BooleanParserTest {

	private static final List<String> TRUE_VALUES = Arrays.asList(
		"true",
		"on",
		"yes",
		"1",
		"enable",
		"enabled",
		"enables"
	);

	private static final List<String> FALSE_VALUES = Arrays.asList(
		"false",
		"off",
		"no",
		"0",
		"disable",
		"disabled",
		"disables"
	);

	private static final String KEY = "marketing";

	@Test
	public void trueLower() throws BooleanParserException {
		for (final String v : TRUE_VALUES) {
			assertTrue("value: " + v, BooleanParser.parse(KEY, v));
		}
	}

	@Test
	public void trueLowerWhitespaceLeft() throws BooleanParserException {
		for (String v : TRUE_VALUES) {
			v = "  " + v;
			assertTrue("value: " + v, BooleanParser.parse(KEY, v));
		}
	}

	@Test
	public void trueLowerWhitespaceRight() throws BooleanParserException {
		for (String v : TRUE_VALUES) {
			v = v + "  ";
			assertTrue("value: " + v, BooleanParser.parse(KEY, v));
		}
	}

	@Test
	public void trueUpper() throws BooleanParserException {
		for (String v : TRUE_VALUES) {
			v = v.toUpperCase();
			assertTrue("value: " + v, BooleanParser.parse(KEY, v));
		}
	}

	@Test
	public void trueUpperWhitespaceLeft() throws BooleanParserException {
		for (String v : TRUE_VALUES) {
			v = "  " + v.toUpperCase();
			assertTrue("value: " + v, BooleanParser.parse(KEY, v));
		}
	}

	@Test
	public void trueUpperWhitespaceRight() throws BooleanParserException {
		for (String v : TRUE_VALUES) {
			v = v.toUpperCase() + "  ";
			assertTrue("value: " + v, BooleanParser.parse(KEY, v));
		}
	}

	@Test
	public void falseLower() throws BooleanParserException {
		for (final String v : FALSE_VALUES) {
			assertFalse("value: '" + v + "'", BooleanParser.parse(KEY, v));
		}
	}

	@Test
	public void falseLowerWhitespaceLeft() throws BooleanParserException {
		for (String v : FALSE_VALUES) {
			v = "  " + v;
			assertFalse("value: '" + v + "'", BooleanParser.parse(KEY, v));
		}
	}

	@Test
	public void falseLowerWhitespaceRight() throws BooleanParserException {
		for (String v : FALSE_VALUES) {
			v = v + "  ";
			assertFalse("value: '" + v + "'", BooleanParser.parse(KEY, v));
		}
	}

	@Test
	public void falseUpper() throws BooleanParserException {
		for (String v : FALSE_VALUES) {
			v = v.toUpperCase();
			assertFalse("value: " + v, BooleanParser.parse(KEY, v));
		}
	}

	@Test
	public void falseUpperWhitespaceLeft() throws BooleanParserException {
		for (String v : FALSE_VALUES) {
			v = "  " + v.toUpperCase();
			assertFalse("value: " + v, BooleanParser.parse(KEY, v));
		}
	}

	@Test
	public void falseUpperWhitespaceRight() throws BooleanParserException {
		for (String v : FALSE_VALUES) {
			v = v.toUpperCase() + "  ";
			assertFalse("value: " + v, BooleanParser.parse(KEY, v));
		}
	}

	@Rule
	public ExpectedException exceptionRuleIllegalUserSupplied = ExpectedException.none();

	private void configUserSupplied() {
		exceptionRuleIllegalUserSupplied.expect(BooleanParserUserException.class);
		exceptionRuleIllegalUserSupplied.expectMessage("Illegal user-supplied value: 'unknown' for key '" + KEY + "'");
	}

	@Test
	public void illegalUserSuppliedTrue() throws BooleanParserException {
		configUserSupplied();
		assertTrue("unknown with default 1", BooleanParser.parse(KEY, "unknown", "1"));
	}

	@Test
	public void illegalUserSuppliedFalse() throws BooleanParserException {
		configUserSupplied();
		assertFalse("unknown with default 0", BooleanParser.parse(KEY, "unknown", "0"));
	}

	@Test
	public void defaultLegalUnused() throws BooleanParserException {
		assertTrue("1 with default 0", BooleanParser.parse(KEY, "1", "0"));
		assertFalse("0 with default 1", BooleanParser.parse(KEY, "0", "1"));
	}

	@Test
	public void defaultLegalUsed() throws BooleanParserException {
		assertTrue("null with default TRUE", BooleanParser.parse(KEY, null, "TRUE"));
		assertFalse("null with default FALSE", BooleanParser.parse(KEY, null, "FALSE"));

		assertTrue("null with default 1", BooleanParser.parse(KEY, null, "1"));
		assertFalse("null with default 0", BooleanParser.parse(KEY, null, "0"));

		assertTrue("empty with default 1", BooleanParser.parse(KEY, "", "1"));
		assertFalse("empty with default 0", BooleanParser.parse(KEY, "", "0"));
	}

	@Test
	public void defaultIllegal_stranger() throws BooleanParserException {
		defaultIllegal("stranger");
	}

	@Test
	public void defaultIllegal_1() throws BooleanParserException {
		defaultIllegal("1");
	}

	@Test
	public void defaultIllegal_0() throws BooleanParserException {
		defaultIllegal("0");
	}

	@Test
	public void defaultIllegal_1wsLeft() throws BooleanParserException {
		defaultIllegal("0", " 1"); // whitespace not allowed
	}

	@Test
	public void defaultIllegal_1wsRight() throws BooleanParserException {
		defaultIllegal("0", "1 "); // whitespace not allowed
	}

	@Test
	public void defaultIllegal_0wsLeft() throws BooleanParserException {
		defaultIllegal("0", " 0"); // whitespace not allowed
	}

	@Test
	public void defaultIllegal_0wsRight() throws BooleanParserException {
		defaultIllegal("0", "0 "); // whitespace not allowed
	}

	@Rule
	public ExpectedException exceptionRuleIllegalDefault = ExpectedException.none();

	private void configDefaultIllegal(final String defaultValue) {
		exceptionRuleIllegalDefault.expect(BooleanParserSystemException.class);
		exceptionRuleIllegalDefault.expectMessage("Illegal default value: '" + defaultValue + "' for key '" + KEY + "'");
	}

	private void defaultIllegal(final String value) throws BooleanParserException{
		defaultIllegal(value, "stranger");
	}

	private void defaultIllegal(final String value, final String defaultValue) throws BooleanParserException{
		configDefaultIllegal(defaultValue);
		BooleanParser.parse(KEY, value, defaultValue);
	}

	@Rule
	public ExpectedException exceptionRuleNoDefault = ExpectedException.none();

	private void configDefaultNone() {
		exceptionRuleIllegalDefault.expect(BooleanParserUserException.class);
		exceptionRuleIllegalDefault.expectMessage("Mandatory value for key '" + KEY + "' not supplied");
	}

	@Test
	public void defaultNone_null() throws BooleanParserException {
		configDefaultNone();
		BooleanParser.parse(KEY, null);
	}

	@Test
	public void defaultNone_empty() throws BooleanParserException {
		configDefaultNone();
		BooleanParser.parse(KEY, "");
	}

	@Test
	public void defaultNone_onlyWhitespace() throws BooleanParserException {
		configDefaultNone();
		BooleanParser.parse(KEY, " ");
	}
}
