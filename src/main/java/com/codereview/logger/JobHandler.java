package com.codereview.logger;

public interface JobHandler {
	void execute(MessageRequest input);

	void setNextJobLoggerHandler(JobHandler requestHandler);

}
