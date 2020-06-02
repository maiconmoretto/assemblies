package com.br;

public class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final Integer code;

	public AppException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
}
