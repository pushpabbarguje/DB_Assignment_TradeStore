package com.db.assignment.tradestore.controller;

public class InvalidTradeException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 525723180688308091L;
	public final String message;
	public InvalidTradeException(String message){
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	
	
}
