package com.codereview.logger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.logging.Level;

import org.junit.jupiter.api.Test;

import com.logger.db.DBConnection;

public class BDLoggerTest {
	
	private FileLogger fileLogger = mock(FileLogger.class);
	private DBConnection bdConnection = mock(DBConnection.class);
	private BDLogger bdLogger;
	private MessageRequest request;
	
	
	@Test
	public void testBDLogger() throws Exception {
		bdLogger = new BDLogger(bdConnection);
		request = new MessageRequest("My Message...", Level.WARNING);
		
		bdLogger.setNextJobLoggerHandler(fileLogger);
		bdLogger.execute(request);


		verify(fileLogger, times(1)).execute(request);
	}

}
