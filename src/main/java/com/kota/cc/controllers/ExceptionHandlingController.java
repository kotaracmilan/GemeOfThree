package com.kota.cc.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kota.cc.exceptions.BadMoveException;

@ControllerAdvice
public class ExceptionHandlingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);
	
	@ExceptionHandler(BadMoveException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleBadMoveException(BadMoveException bme) {
		
	}
}
