package com.codereview.logger;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConsoleLoggerTest {
	Logger logger = Logger.getLogger("Logger");
	private OutputStream logOut;
	private StreamHandler testLogHandler;

	@BeforeEach
	public void setUp() throws Exception {
		setUpLogHandler(logger);
	}

	@Test
	public void testConsoleLogger() throws Exception {
		ConsoleLogger consoleLogger = new ConsoleLogger();
		
		logger.log(Level.WARNING, "October 4, 2019 test");
		testLogHandler.flush();
		
		String captured = logOut.toString().trim();
		consoleLogger.execute(new MessageRequest("test", Level.WARNING));
		
		String expected = "WARNING: " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " test";
		Assertions.assertTrue(captured.contains(expected));

	}

	protected void setUpLogHandler(Logger logger) {
		logOut = new ByteArrayOutputStream();
		Handler[] handlers = logger.getParent().getHandlers();
		testLogHandler = new StreamHandler(logOut, handlers[0].getFormatter());
		logger.addHandler(testLogHandler);
	}


}
