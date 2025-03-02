package org.chlebsearch.util;

public class BooleanParserSystemException extends BooleanParserException {

	public BooleanParserSystemException() {
		super();
	}

	public BooleanParserSystemException(String errorMsg) {
		super(errorMsg);
	}

	public BooleanParserSystemException(String errorMsg, Exception e) {
		super(errorMsg, e);
	}
}
