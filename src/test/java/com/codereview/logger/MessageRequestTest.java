package com.codereview.logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Level;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessageRequestTest {

	@Test
	public void shouldThrowExceptionDueNullValues() throws Exception {
		Assertions.assertThrows(Exception.class, () -> {
			new MessageRequest(null, Level.ALL);
		});
	}

	@Test
	public void shouldValidateMessageContent() throws Exception {
		MessageRequest actual = new MessageRequest("test", Level.ALL);
		assertEquals("October 4, 2019 test", actual.getMessage());
	}
}
