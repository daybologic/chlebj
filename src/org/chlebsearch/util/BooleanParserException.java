package org.chlebsearch.util;

abstract public class BooleanParserException extends Throwable {

	public BooleanParserException() {
		super();
	}

	public BooleanParserException(String errorMsg) {
		super(errorMsg);
	}

	public BooleanParserException(String errorMsg, Exception e) {
		super(errorMsg, e);
	}
}
