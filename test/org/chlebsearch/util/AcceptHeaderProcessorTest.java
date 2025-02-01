package org.chlebsearch.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.Map;
/*
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.tz.FixedDateTimeZone;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Record;
import com.amazonaws.services.dynamodbv2.model.StreamRecord;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;*/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Helper utilities for testing Lambda functions.
 */
public class AcceptHeaderProcessorTest {

	private AcceptHeaderProcessor sut

	@Before
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
