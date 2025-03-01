package org.chlebsearch.util;

import java.util.Arrays;
import java.util.List;

public class BooleanParser {

	private static final List<String> TRUE_VALUES = Arrays.asList("1", "true", "on", "yes");
	private static final List<String> FALSE_VALUES = Arrays.asList("0", "false", "off", "no");

	private static boolean isTrue(String v) {
		for (final String trueValue : TRUE_VALUES) {
			if (v.equals(trueValue)) return true;
		}

		if (v.startsWith("enable")) return true;

		return false;
	}

	private static boolean isFalse(String v) {
		for (final String falseValue : FALSE_VALUES) {
			if (v.equals(falseValue)) return true;
		}

		if (v.startsWith("disable")) return true;

		return false;
	}

	public static boolean parse(String value, String defaultValue) throws BooleanParserException {
		boolean defaultValueReturned = false;

		// Let's run this block first so we trap invalid defaults even when they aren't used
		if (defaultValue != null) {
			defaultValue = defaultValue.toLowerCase(); // TODO: not properly tested
			if (isTrue(defaultValue)) {
				defaultValueReturned = true;
			} else if (!isFalse(defaultValue)) {
				// nb. this is a developer issue, not an end-user issue
				// TODO: do we need a separate exception for internal errors?
				throw new BooleanParserException("Illegal default value: '" + defaultValue + "'");
			}
		}

		if (value != null) {
			value = value.trim();
			if (value.length() > 0) {
				value = value.toLowerCase();

				if (isTrue(value)) return true;
				if (isFalse(value)) return false;

				throw new BooleanParserException("Illegal user-supplied value: '" + value + "'");
			}
		}

		// TODO: We don't know the key name
		// TODO: Should the key be recorded against the exception object?
		if (defaultValue != null) return defaultValueReturned; // Apply default, if supplied/available
		throw new BooleanParserException("Mandatory value not supplied"); // TODO missing key name
	}

	public static boolean parse(final String value) throws BooleanParserException {
		return parse(value, null);
	}
}
