package org.chlebsearch.util;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Helper utilities for testing Lambda functions.
 */
public class AcceptHeaderProcessorTest {

	private AcceptHeaderProcessor sut;

	@BeforeEach
	public void setUp() {
		sut = new AcceptHeaderProcessor();
	}

	@Test
	public void successfulResponse() {
		/*GatewayResponse result = (GatewayResponse) app.handleRequest(null, null);
		assertEquals(result.getStatusCode(), 200);
		assertEquals(result.getHeaders().get("Content-Type"), "application/json");
		String content = result.getBody();
		assertNotNull(content);
		assertTrue(content.contains("\"message\""));
		assertTrue(content.contains("\"hello world\""));
		assertTrue(content.contains("\"location\""));*/
		String input = "text/plain; q=0.5, text/html,\n"
		    + "text/x-dvi; q=0.8, text/x-c";

		sut.process(input);
	}
}
