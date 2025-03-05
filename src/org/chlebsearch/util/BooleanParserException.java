package org.chlebsearch.util;

abstract public class BooleanParserException extends Throwable {

	private static final long serialVersionUID = -6802781729274390394L;
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
