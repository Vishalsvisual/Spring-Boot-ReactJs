package net.react.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class IllegalArgumentException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalArgumentException(String message) {
		super(message);
	}
}
