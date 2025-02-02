package org.chlebsearch.util;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Helper utilities for testing Lambda functions.
 */
public class AcceptHeaderProcessorTest {

	private AcceptHeaderProcessor sut;

	@Before
	public void setUp() {
		sut = new AcceptHeaderProcessor();
	}

	@Test
	public void successfulResponse() {
		final String input = "text/plain; q=0.5, text/html,\n"
		    + "text/x-dvi; q=0.8, text/x-c";

		assertTrue(true); // FIXME: dummy test
		sut.process(input);
	}

	@Test
	public void simpleSuccess() {
		sut.process("*/*");
		sut.process("application/json");
		sut.process("text/html");
		sut.process("text/plain");
		sut.process("text/*");
		sut.process("application/*");
		sut.process("application/json");
	}

	@Test
	public void simpleFailure() {
		sut.process("application/jsom");
		sut.process("audio/basic");
	}
}
