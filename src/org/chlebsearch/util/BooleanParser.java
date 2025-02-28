package org.chlebsearch.util;

public class BooleanParser {

	private static boolean isTrue(String v) {
		v = v.toLowerCase();

		if (v.equals("1")) return true;
		if (v.equals("true")) return true;
		if (v.equals("on")) return true;
		if (v.equals("yes")) return true;

		if (v.startsWith("enable")) return true;

		return false;
	}

	private static boolean isFalse(String v) {
		v = v.toLowerCase();

		// TODO: Use an ArrayList (Arrays) to loop over these values
		if (v.equals("0")) return true;
		if (v.equals("false")) return true;
		if (v.equals("off")) return true;
		if (v.equals("no")) return true;

		if (v.startsWith("disable")) return true;

		return false;
	}

	public boolean parse(String value, String defaultValue) {
		boolean defaultValueReturned = false;

		if (defaultValue != null) {
			if (isTrue(defaultValue)) {
				defaultValueReturned = true;
			} else if (!isFalse(defaultValue)) {
				throw new RuntimeException("Illegal default value: '" + defaultValue + "'");
			}
		}

		if (value != null) {
			value = value.toLowerCase();

			if (isTrue(value)) return true;
			if (isFalse(value)) return false;
		}

		return defaultValueReturned;
	}
}
