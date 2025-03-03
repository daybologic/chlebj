package org.chlebsearch.util;

abstract public class BooleanParserException extends Throwable {

	public final String key;

	public BooleanParserException() {
		super();
		key = null;
	}

	public BooleanParserException(final String key, final String errorMsg) {
		super(errorMsg);
		this.key = key;
	}

	public BooleanParserException(final String key, final String errorMsg, final Exception e) {
		super(errorMsg, e);
		this.key = key;
	}
}
