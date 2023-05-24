package com.geonwoo.solokill.domain.member.dto.request;

public record MemberSignUpRequest(
	String loginEmail,
	String password,
	String nickname
) {
}
