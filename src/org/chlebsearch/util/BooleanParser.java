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

	public static boolean parse(final String key, String value, String defaultValue) throws BooleanParserException {
		boolean defaultValueReturned = false;

		// Let's run this block first so we trap invalid defaults even when they aren't used
		if (defaultValue != null) {
			defaultValue = defaultValue.toLowerCase();
			if (isTrue(defaultValue)) {
				defaultValueReturned = true;
			} else if (!isFalse(defaultValue)) {
				throw new BooleanParserSystemException(String.format(
					"Illegal default value: '%s' for key '%s'",
					defaultValue,
					key
				));
			}
		}

		if (value != null) {
			value = value.trim();
			if (value.length() > 0) {
				value = value.toLowerCase();

				if (isTrue(value)) return true;
				if (isFalse(value)) return false;

				throw new BooleanParserUserException(String.format(
					"Illegal user-supplied value: '%s' for key '%s'",
					value,
					key
				));
			}
		}

		// TODO: Should the key be recorded against the exception object?
		if (defaultValue != null) return defaultValueReturned; // Apply default, if supplied/available
		throw new BooleanParserUserException(String.format("Mandatory value for key '%s' not supplied", key));
	}

	public static boolean parse(final String key, final String value) throws BooleanParserException {
		return parse(key, value, null);
	}
}
