package com.geonwoo.solokill.domain.member.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberLoginRequest(
        @NotBlank String email,
        @NotBlank String password
) {
}
