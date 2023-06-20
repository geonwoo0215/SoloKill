package com.geonwoo.solokill.domain.member.dto.request;

import java.io.Serializable;

import com.geonwoo.solokill.domain.member.model.vo.MemberAuthority;

public record AuthenticationDTO(
	Long memberId,
	MemberAuthority memberAuthority
) implements Serializable {
}
