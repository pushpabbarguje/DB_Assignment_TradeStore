package com.db.assignment.tradestore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TradeStoreControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(InvalidTradeException.class)
	public ResponseEntity<String> invalidTrade(InvalidTradeException e){
		return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);		
		
	}
}
