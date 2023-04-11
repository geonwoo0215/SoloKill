package com.geonwoo.solokill.global.dto.response;

public record ApiResponse<T>(
	T data
) {
}
