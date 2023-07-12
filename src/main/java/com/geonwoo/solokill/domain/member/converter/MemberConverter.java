package com.geonwoo.solokill.domain.member.converter;

import org.springframework.stereotype.Component;

import com.geonwoo.solokill.domain.member.dto.request.MemberSignUpRequest;
import com.geonwoo.solokill.domain.member.dto.response.MemberDTO;
import com.geonwoo.solokill.domain.member.model.Member;

@Component
public class MemberConverter {

	public static Member toMember(MemberSignUpRequest memberSignUpRequest, String encryptPassword) {

		return new Member(memberSignUpRequest.email(), encryptPassword, memberSignUpRequest.nickname());
	}

	public static MemberDTO toMemberResponse(Member member) {

		return new MemberDTO(member.getId(), member.getEmail(), member.getNickname(), member.getMemberAuthority());
	}
}
