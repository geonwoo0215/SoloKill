package com.geonwoo.solokill.domain.member.model.dto.response;

import com.geonwoo.solokill.domain.member.model.Member;

public record MemberResponse(
        Long id,
        String email,
        String nickname
) {

    public static MemberResponse toMemberResponse(Member member) {
        return new MemberResponse(member.getId(), member.getEmail(), member.getNickname());
    }
}
