package com.geonwoo.solokill.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberLoginRequest(
	@NotBlank String loginEmail,
	@NotBlank String password
) {
}
