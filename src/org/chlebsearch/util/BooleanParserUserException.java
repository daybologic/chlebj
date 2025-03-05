package org.chlebsearch.util;

public class BooleanParserUserException extends BooleanParserException {

	private static final long serialVersionUID = 3314923041457616312L;

	public BooleanParserUserException() {
		super();
	}

	public BooleanParserUserException(final String key, final String errorMsg) {
		super(key, errorMsg);
	}

	public BooleanParserUserException(final String key, final String errorMsg, final Exception e) {
		super(key, errorMsg, e);
	}
}
