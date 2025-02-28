package org.chlebsearch.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
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
	public void trueLower() {
		for (final String v : TRUE_VALUES) {
			assertTrue("value: " + v, BooleanParser.parse(v));
		}
	}

	@Test
	public void trueUpper() {
		for (String v : TRUE_VALUES) {
			v = v.toUpperCase();
			assertTrue("value: " + v, BooleanParser.parse(v));
		}
	}

	@Test
	public void falseLower() {
		for (final String v : FALSE_VALUES) {
			assertFalse("value: '" + v + "'", BooleanParser.parse(v));
		}

		assertFalse("value: <null>", BooleanParser.parse(null));
		assertFalse("value: 'unknown'", BooleanParser.parse("unknown"));
	}

	@Test
	public void falseUpper() {
		for (String v : FALSE_VALUES) {
			v = v.toUpperCase();
			assertFalse("value: " + v, BooleanParser.parse(v));
		}
	}



/*
	sub testDefaultLegal {
		my ($self) = @_;
		plan tests => 4;

		ok(Chleb::Utils::boolean('unknown', 1), 'unknown with default 1 is 1');
		ok(!Chleb::Utils::boolean('unknown', 0), 'unknown with default 0 is 0');
		ok(Chleb::Utils::boolean(1, 0), '1 with default 0 is 1');
		ok(!Chleb::Utils::boolean(0, 1), '0 with default 1 is 0');

		return EXIT_SUCCESS;
	}

	sub testDefaultIllegal {
		my ($self) = @_;
		plan tests => 3;

		Readonly my @BASE_VALUES => ('unknown', 1, 0);

		foreach my $v (@BASE_VALUES) {
			throws_ok {
				Chleb::Utils::boolean($v, 'stranger')
			} qr/Illegal default value: 'stranger'/, "$v with default stranger is illegal";
		}

		return EXIT_SUCCESS;
	}*/
}
