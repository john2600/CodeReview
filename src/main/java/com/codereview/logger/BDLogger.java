package com.codereview.logger;

import java.util.Optional;

import com.logger.db.DBConnection;

public class BDLogger implements JobHandler {

	//private static final String INSERT_QUERY = "insert into Log_Values (%s, %d) ";
	private JobHandler process;
	private DBConnection bdConnection;

	public BDLogger(DBConnection connection) {
		this.bdConnection = connection;
	}

	@Override
	public void setNextJobLoggerHandler(JobHandler process) {
		this.process = process;
	}

	@Override
	public void execute(MessageRequest request) {
		if (Optional.ofNullable(process).isPresent()) {
			process.execute(request);
		}
		request.setCount(request.getCount() + 1);
		//bdConnection.executeQuery(String.format(INSER_QUERY, request.getMessage(),
		//request.getCount()));
	}

}
