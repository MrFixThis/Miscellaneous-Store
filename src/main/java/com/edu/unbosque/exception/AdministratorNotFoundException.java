package com.edu.unbosque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Bryan Baron
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdministratorNotFoundException extends RuntimeException {

	public AdministratorNotFoundException(String message) {
		super(message);
	}
}
