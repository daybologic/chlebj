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

		// Let's run this block first so we trap invalid defaults even when they aren't used
		if (defaultValue != null) {
			if (isTrue(defaultValue)) {
				defaultValueReturned = true;
			} else if (!isFalse(defaultValue)) {
				// nb. this is a developer issue, not an end-user issue
				// TODO: do we need a separate exception for internal errors?
				throw new BooleanParserException("Illegal default value: '" + defaultValue + "'");
			}
		}

		if (value == null || value.length() == 0) { // We must fall-back to a default
			if (defaultValue != null) return defaultValueReturned; // Apply default, if supplied/available
			throw new BooleanParserException("Mandatory value not supplied"); // TODO missing key name
		} else {
			value = value.toLowerCase();

			if (isTrue(value)) return true;
			if (isFalse(value)) return false;
		}

		// TODO: We don't know the key name
		// TODO: Should the key be recorded against the exception object?
		throw new BooleanParserException("Illegal user-supplied value: '" + value + "'");
	}

	public static boolean parse(final String value) throws BooleanParserException {
		return parse(value, null);
	}
}
