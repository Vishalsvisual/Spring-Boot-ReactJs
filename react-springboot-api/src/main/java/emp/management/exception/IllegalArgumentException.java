package emp.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class IllegalArgumentException extends Exception {
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	public IllegalArgumentException(String message) {
		super(message);
	}
}
