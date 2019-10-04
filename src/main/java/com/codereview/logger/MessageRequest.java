package com.codereview.logger;

import java.text.DateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;

public class MessageRequest {
	private String message;
	private Level[] levels;
	private int count = 0;

	public MessageRequest(String message, Level... levels) throws Exception {
		String msg = Optional.ofNullable(message).orElse("");
		if (!Optional.ofNullable(levels).isPresent() || msg.trim().isEmpty()) {
			throw new Exception("Error or Warning or Message must be specified");
		}

		this.message = message;
		this.levels = levels;
	}

	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append(DateFormat.getDateInstance(DateFormat.LONG).format(new Date()));
		sb.append(" ").append(this.message);
		return sb.toString();
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return this.count;
	}

	public Level[] getLevels() {
		return this.levels;
	}

}
