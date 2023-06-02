package com.geonwoo.solokill.domain.member.dto.request;

public record MemberSignUpRequest(
	String email,
	String password,
	String nickname
) {
}
