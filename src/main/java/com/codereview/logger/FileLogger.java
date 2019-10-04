package com.codereview.logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLogger implements JobHandler {

	private static Logger logger = Logger.getLogger("Logger");

	private JobHandler process;

	@Override
	public void setNextJobLoggerHandler(JobHandler requestHandler) {
		this.process = requestHandler;
	}

	public void execute(MessageRequest messsage) {
		writeMessagetoFile(messsage);
		if (Optional.ofNullable(process).isPresent()) {
			this.process.execute(messsage);
		}
	}

	private void writeMessagetoFile(MessageRequest request) {
		File logFile = new File("src" + "/file.log");
		try {
			if (logFile.exists()) {
				logFile.delete();
			} else {
				logFile.createNewFile();
			}
			logger.addHandler(new FileHandler("src" + "/file.log"));
			List<Level> messagesToLog = Arrays.asList(request.getLevels());

			messagesToLog.forEach(severity -> logger.log(severity, request.getMessage()));

		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		request.setCount(request.getCount() + 1);
	}

}