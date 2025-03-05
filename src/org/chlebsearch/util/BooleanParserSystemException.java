package org.chlebsearch.util;

public class BooleanParserSystemException extends BooleanParserException {

	private static final long serialVersionUID = 8690463251843485437L;

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
