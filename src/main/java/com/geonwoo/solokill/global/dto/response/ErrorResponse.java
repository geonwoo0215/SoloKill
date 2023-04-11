package com.geonwoo.solokill.global.dto.response;

import com.geonwoo.solokill.global.exception.ErrorCode;

public record ErrorResponse(
	String code,
	String message
) {

	public static ErrorResponse of(ErrorCode code) {
		return new ErrorResponse(code.getCode(), code.getMessage());
	}
}
