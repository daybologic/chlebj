package org.chlebsearch.util;

public class BooleanParserSystemException extends BooleanParserException {

	public BooleanParserSystemException() {
		super();
	}

	public BooleanParserSystemException(final String key, final String errorMsg) {
		super(key, errorMsg);
	}

	public BooleanParserSystemException(final String key, final String errorMsg, final Exception e) {
		super(key, errorMsg, e);
	}
}
