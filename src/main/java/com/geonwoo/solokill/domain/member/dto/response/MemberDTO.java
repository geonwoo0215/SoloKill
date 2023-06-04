package com.geonwoo.solokill.domain.member.dto.response;

import com.geonwoo.solokill.domain.member.model.vo.MemberAuthority;

public record MemberDTO(
	String email,
	String nickname,
	MemberAuthority memberAuthority
) {
}
