package com.geonwoo.solokill.domain.member.exception;

import com.geonwoo.solokill.global.exception.ErrorCode;
import com.geonwoo.solokill.global.exception.ServiceException;

public class AuthenticationException extends ServiceException {

	private static final ErrorCode ERROR_CODE = ErrorCode.UNAUTHORIZED_ERROR;
	private static final String MESSAGE_KEY = "권한이 없습니다.";

	public AuthenticationException() {
		super(ERROR_CODE, MESSAGE_KEY, new Object[] {});
	}

}
