package com.Thiago_landi.sistemas.vendas.exceptions;

public class InvalidOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidOperationException(String message) {
		super(message);
	}
}
