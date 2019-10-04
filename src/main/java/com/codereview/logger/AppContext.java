package com.codereview.logger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.IntStream;

public class AppContext {

	public static JobHandler getHandler() {
		List<JobHandler> requestHandlers = new ArrayList<>();
		requestHandlers.add(new ConsoleLogger());
		requestHandlers.add(new FileLogger());
		//requestHandlers.add(new BDLogger(new DBConnection()));

		IntStream.range(0, requestHandlers.size() - 1)
				.forEach(index -> requestHandlers.get(index).setNextJobLoggerHandler(requestHandlers.get(index + 1)));
		return requestHandlers.get(0);
	}

	public static void main(String[] args) throws Exception {
		//MessageRequest requestData = new MessageRequest("My Message...", Level.INFO);
		//AppContext.getHandler().JobLogger(requestData);
		
		MessageRequest requestData2 = new MessageRequest("My Message...", Level.WARNING, Level.SEVERE);
		AppContext.getHandler().execute(requestData2);
		
	}
}
