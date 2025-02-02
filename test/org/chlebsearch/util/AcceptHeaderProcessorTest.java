package org.chlebsearch.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

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
	public void complex() {
		String acceptHeader = "text/plain; q=0.5, text/html,\n"
		    + "text/x-dvi; q=0.8, text/x-c";

		Optional<String> bestMatch = sut.process(acceptHeader);
		is(bestMatch, "text/html");

		acceptHeader = "text/html,application/xhtml+xml,application/xml";
		bestMatch = sut.process(acceptHeader);
		is(bestMatch, "text/html");

		acceptHeader = "text/html,application/xhtml+xml,*/*;q=0.8,application/xml;q=0.9";
		bestMatch = sut.process(acceptHeader);
		is(bestMatch, "text/html");

		acceptHeader = "text/plain; q=0.5, text/html,\n";
		bestMatch = sut.process(acceptHeader);
		is(bestMatch, "text/html");

		acceptHeader = "text/plain,application/xhtml+xml,*/*;q=0.8,application/xml;q=0.9;application/json;q=100";
		bestMatch = sut.process(acceptHeader);
		//is(bestMatch, "application/json");
	}

	@Test
	public void empty() {
		Optional<String> bestMatch = sut.process(null);
		checkNotPresent(bestMatch);

		bestMatch = sut.process("");
		checkNotPresent(bestMatch);
	}

	@Test
	public void simple() {
		Optional<String> bestMatch = sut.process("*/*");
	//	is(bestMatch, "text/html");

		bestMatch = sut.process("application/json");
		is(bestMatch, "application/json");

		bestMatch = sut.process("text/html");
		is(bestMatch, "text/html");

		bestMatch = sut.process("text/plain");
		checkNotPresent(bestMatch);

		bestMatch = sut.process("text/*");
		is(bestMatch, "text/html");

		bestMatch = sut.process("application/*");
		//is(bestMatch, "application/json");

		bestMatch = sut.process("application/json");
		is(bestMatch, "application/json");
	}

	@Test
	public void simpleFailure() {
		Optional<String> bestMatch = sut.process("application/jsom"); // sic
		checkNotPresent(bestMatch);

		bestMatch = sut.process("audio/basic");
		checkNotPresent(bestMatch);
	}
	
	private void is(final Optional<String> bestMatch, final String expected) {
		assertNotNull(bestMatch);
		assertTrue(bestMatch.isPresent());
		assertEquals(expected, bestMatch.get());
	}

	private void checkNotPresent(final Optional<String> bestMatch) {
		assertNotNull(bestMatch);
		assertFalse(bestMatch.isPresent());
	}
}
