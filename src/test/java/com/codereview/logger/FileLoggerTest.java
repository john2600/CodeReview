package com.codereview.logger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class FileLoggerTest {
	private ConsoleLogger consoleLogger = mock(ConsoleLogger.class);
	private FileLogger fileLogger;
	private MessageRequest request;

	@Test
	public void testFileLogger() throws Exception {
		// given
		givenAFileLoggerJobAndMessageRequesIsCreated();

		//when
		fileLogger.setNextJobLoggerHandler(consoleLogger);
		fileLogger.execute(request);

		//then
		verifyThatPreviousJobWasCalledAndFileWasCreated();

	}

	private void verifyThatPreviousJobWasCalledAndFileWasCreated() {
		verify(consoleLogger, times(1)).execute(request);
		File logFile = new File("src", "file.log");
		assertTrue(logFile.exists());
	}

	public void givenAFileLoggerJobAndMessageRequesIsCreated() throws Exception {
		fileLogger = new FileLogger();
		request = new MessageRequest("My Message...", Level.WARNING);
	}

}
