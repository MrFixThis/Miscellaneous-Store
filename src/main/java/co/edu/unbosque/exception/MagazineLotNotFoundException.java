package co.edu.unbosque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Bryan Baron
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MagazineLotNotFoundException extends RuntimeException {

	public MagazineLotNotFoundException(String message) {
		super(message);
	}
}
