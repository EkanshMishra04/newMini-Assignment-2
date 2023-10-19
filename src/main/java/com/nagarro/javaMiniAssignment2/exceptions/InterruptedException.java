package com.nagarro.javaMiniAssignment2.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InterruptedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String message;
	private final int code;
	private final String timestamp;

	public InterruptedException(String message, int code) {
		super();
		this.message = message;
		this.code = code;
		this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("d 'th' MMMM yyyy HH:mm:ss"));
	}

	@Override
	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public String getTimestamp() {
		return timestamp;
	}

}
