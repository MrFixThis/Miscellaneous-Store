package com.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Bryan Baron
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MagazineLotNotFoundException extends RuntimeException {

	/**
	 * Constructs a new MagazineLotNotFoundException with the specified detail message.
	 *
	 * @param message the detail message. The detail message is saved for
	 * later retrieval by the {@link #getMessage()} method.
	 */
	public MagazineLotNotFoundException(String message) {
		super(message);
	}
}
