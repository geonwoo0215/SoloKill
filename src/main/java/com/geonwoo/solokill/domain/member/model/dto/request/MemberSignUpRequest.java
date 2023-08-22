package com.geonwoo.solokill.domain.member.model.dto.request;

public record MemberSignUpRequest(
        String email,
        String password,
        String nickname
) {
}
