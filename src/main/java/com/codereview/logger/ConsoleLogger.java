package com.codereview.logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogger implements JobHandler {
	private static Logger logger = Logger.getLogger("Logger");
	private JobHandler process;

	@Override
	public void setNextJobLoggerHandler(JobHandler requestHandler) {
		this.process = requestHandler;
	}

	@Override
	public void execute(MessageRequest request) {
		writeMessageToConsole(request);
		if (Optional.ofNullable(process).isPresent()) {
			process.execute(request);
		}
	}

	private void writeMessageToConsole(MessageRequest request) {
		List<Level> messagesToLog = Arrays.asList(request.getLevels());
		messagesToLog.forEach(severity -> {
			switch (severity.getName()) {
			case LogType.SEVERITY_INFO:
				logger.info(request.getMessage());
				break;
			case LogType.SEVERITY_WARNING:
				logger.warning(request.getMessage());
				break;
			case LogType.SEVERITY_SEVERE:
				logger.severe(request.getMessage());
				break;
			}

		});
		request.setCount(request.getCount() + 1);
	}

}
