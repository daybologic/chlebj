package org.chlebsearch.util;

import java.util.Arrays;
import java.util.List;

public class BooleanParser {

	private static final List<String> TRUE_VALUES = Arrays.asList("1", "true", "on", "yes");
	private static final List<String> FALSE_VALUES = Arrays.asList("0", "false", "off", "no");

	private static boolean isTrue(String v) {
		v = v.toLowerCase();

		for (final String trueValue : TRUE_VALUES) {
			if (v.equals(trueValue)) return true;
		}

		if (v.startsWith("enable")) return true;

		return false;
	}

	private static boolean isFalse(String v) {
		v = v.toLowerCase();

		for (final String falseValue : FALSE_VALUES) {
			if (v.equals(falseValue)) return true;
		}

		if (v.startsWith("disable")) return true;

		return false;
	}

	public static boolean parse(String value, String defaultValue) throws BooleanParserException {
		boolean defaultValueReturned = false;

		if (defaultValue != null) {
			if (isTrue(defaultValue)) {
				defaultValueReturned = true;
			} else if (!isFalse(defaultValue)) {
				throw new BooleanParserException("Illegal default value: '" + defaultValue + "'");
			}
		}

		if (value != null) {
			value = value.toLowerCase();

			if (isTrue(value)) return true;
			if (isFalse(value)) return false;
		}

		return defaultValueReturned;
	}

	public static boolean parse(final String value) throws BooleanParserException {
		return parse(value, null);
	}
}
