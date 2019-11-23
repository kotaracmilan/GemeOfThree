package com.kota.cc.exceptions;

public class BadMoveException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadMoveException(String m) {
		super(m);
	}
}
