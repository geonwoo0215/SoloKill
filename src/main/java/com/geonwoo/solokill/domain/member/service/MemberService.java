package com.geonwoo.solokill.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.solokill.domain.member.converter.MemberConverter;
import com.geonwoo.solokill.domain.member.dto.request.MemberSignUpRequest;
import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Long singUp(MemberSignUpRequest memberSignUpRequest) {
		validateDuplicateEmail(memberSignUpRequest.loginEmail());
		validateDuplicateNickname(memberSignUpRequest.nickname());

		String encryptPassword = passwordEncoder.encrypt(memberSignUpRequest.password());
		Member member = memberRepository.save(MemberConverter.toMember(memberSignUpRequest, encryptPassword));
		return member.getId();
	}

	private void validateDuplicateEmail(String loginEmail) {
		if (memberRepository.existsByLoginEmail(loginEmail)) {
			throw new IllegalArgumentException("중복된 아이디입니다.");
		}
	}

	private void validateDuplicateNickname(String nickname) {
		if (memberRepository.existsByNickname(nickname)) {
			throw new IllegalArgumentException("중복된 닉네입니다.");
		}
	}
}
