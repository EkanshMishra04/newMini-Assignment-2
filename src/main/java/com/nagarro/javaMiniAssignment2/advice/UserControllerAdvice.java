package com.nagarro.javaMiniAssignment2.advice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.nagarro.javaMiniAssignment2.constants.ServiceConstants;
import com.nagarro.javaMiniAssignment2.dto.ApiResponse;
import com.nagarro.javaMiniAssignment2.exceptions.CustomException;
import com.nagarro.javaMiniAssignment2.exceptions.ExecutionException;
import com.nagarro.javaMiniAssignment2.exceptions.ReadTimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResponse> handleCustomException(CustomException customException) {
		String message = customException.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, String.valueOf(customException.getCode()),LocalDateTime.now().format(DateTimeFormatter.ofPattern("d 'th' MMMM yyyy HH:mm:ss")));
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(ReadTimeoutException.class)
	public ResponseEntity<ApiResponse> handleReadException(ReadTimeoutException readTimeoutException) {
		String message = readTimeoutException.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, ServiceConstants.STATUS_408,LocalDateTime.now().format(DateTimeFormatter.ofPattern("d 'th' MMMM yyyy HH:mm:ss")));
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(InterruptedException.class)
	public ResponseEntity<ApiResponse> handleInterruptedException(InterruptedException interruptedException) {
		String message = interruptedException.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, ServiceConstants.STATUS_408,LocalDateTime.now().format(DateTimeFormatter.ofPattern("d 'th' MMMM yyyy HH:mm:ss")));
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ExecutionException.class)
	public ResponseEntity<ApiResponse> handleReadException(ExecutionException executionException) {
		String message = executionException.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, ServiceConstants.STATUS_408,LocalDateTime.now().format(DateTimeFormatter.ofPattern("d 'th' MMMM yyyy HH:mm:ss")));
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

}
