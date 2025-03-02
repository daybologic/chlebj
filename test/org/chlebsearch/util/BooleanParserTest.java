package org.chlebsearch.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.rules.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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

		assertFalse("value: <null>", BooleanParser.parse(null));
		assertFalse("value: 'unknown'", BooleanParser.parse("unknown"));
	}

	@Test
	public void falseUpper() throws BooleanParserException {
		for (String v : FALSE_VALUES) {
			v = v.toUpperCase();
			assertFalse("value: " + v, BooleanParser.parse(v));
		}
	}

	@Test
	public void defaultLegal() throws BooleanParserException {
		assertTrue("unknown with default 1 is 1", BooleanParser.parse("unknown", "1"));
		assertFalse("unknown with default 0 is 0", BooleanParser.parse("unknown", "0"));
		assertTrue("1 with default 0 is 1", BooleanParser.parse("1", "0"));
		assertFalse("0 with default 1 is 0", BooleanParser.parse("0", "1"));
	}

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

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

	private void defaultIllegal(final String value) throws BooleanParserException{
		exceptionRule.expect(BooleanParserException.class);
		exceptionRule.expectMessage("Illegal default value: 'stranger'");

		BooleanParser.parse(value, "stranger");
	}
}
