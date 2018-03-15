package com.gzycdjk.commons.exception;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CustomException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1909256662252720380L;
	
	
	private String message;
	
	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
		this.message = message;
	}
	

}
