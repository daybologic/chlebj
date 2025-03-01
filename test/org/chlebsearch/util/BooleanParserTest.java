package org.chlebsearch.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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

	@Test
	public void trueLower() throws BooleanParserException {
		for (final String v : TRUE_VALUES) {
			assertTrue("value: " + v, BooleanParser.parse(v));
		}
	}

	@Test
	public void trueUpper() throws BooleanParserException {
		for (String v : TRUE_VALUES) {
			v = v.toUpperCase();
			assertTrue("value: " + v, BooleanParser.parse(v));
		}
	}

	@Test
	public void falseLower() throws BooleanParserException {
		for (final String v : FALSE_VALUES) {
			assertFalse("value: '" + v + "'", BooleanParser.parse(v));
		}
	}

	@Test
	public void falseUpper() throws BooleanParserException {
		for (String v : FALSE_VALUES) {
			v = v.toUpperCase();
			assertFalse("value: " + v, BooleanParser.parse(v));
		}
	}

	@Rule
	public ExpectedException exceptionRuleIllegalUserSupplied = ExpectedException.none();

	private void configUserSupplied() {
		exceptionRuleIllegalUserSupplied.expect(BooleanParserException.class);
		exceptionRuleIllegalUserSupplied.expectMessage("Illegal user-supplied value: 'unknown'");
	}

	@Test
	public void illegalUserSuppliedTrue() throws BooleanParserException {
		configUserSupplied();
		assertTrue("unknown with default 1", BooleanParser.parse("unknown", "1"));
	}

	@Test
	public void illegalUserSuppliedFalse() throws BooleanParserException {
		configUserSupplied();
		assertFalse("unknown with default 0", BooleanParser.parse("unknown", "0"));
	}

	@Test
	public void defaultLegalUnused() throws BooleanParserException {
		assertTrue("1 with default 0", BooleanParser.parse("1", "0"));
		assertFalse("0 with default 1", BooleanParser.parse("0", "1"));
	}

	@Test
	public void defaultLegalUsed() throws BooleanParserException {
		assertTrue("null with default 1", BooleanParser.parse(null, "1"));
		assertFalse("null with default 0", BooleanParser.parse(null, "0"));

		assertTrue("empty with default 1", BooleanParser.parse("", "1"));
		assertFalse("empty with default 0", BooleanParser.parse("", "0"));
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

	@Rule
	public ExpectedException exceptionRuleIllegalDefault = ExpectedException.none();

	private void configDefaultIllegal() {
		exceptionRuleIllegalDefault.expect(BooleanParserException.class);
		exceptionRuleIllegalDefault.expectMessage("Illegal default value: 'stranger'");
	}

	private void defaultIllegal(final String value) throws BooleanParserException{
		configDefaultIllegal();
		BooleanParser.parse(value, "stranger");
	}

	@Rule
	public ExpectedException exceptionRuleNoDefault = ExpectedException.none();

	private void configDefaultNone() {
		exceptionRuleIllegalDefault.expect(BooleanParserException.class);
		exceptionRuleIllegalDefault.expectMessage("Mandatory value not supplied");
	}

	@Test
	public void defaultNone_null() throws BooleanParserException {
		configDefaultNone();
		BooleanParser.parse(null);
	}

	@Test
	public void defaultNone_empty() throws BooleanParserException {
		configDefaultNone();
		BooleanParser.parse("");
	}
}
