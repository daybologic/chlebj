package org.chlebsearch.util;

public class BooleanParserUserException extends BooleanParserException {

	public BooleanParserUserException() {
		super();
	}

	public BooleanParserUserException(String errorMsg) {
		super(errorMsg);
	}

	public BooleanParserUserException(String errorMsg, Exception e) {
		super(errorMsg, e);
	}
}
