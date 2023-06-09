package com.geonwoo.solokill.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.solokill.domain.member.converter.MemberConverter;
import com.geonwoo.solokill.domain.member.dto.request.AuthenticationDTO;
import com.geonwoo.solokill.domain.member.dto.request.MemberLoginRequest;
import com.geonwoo.solokill.domain.member.dto.request.MemberSignUpRequest;
import com.geonwoo.solokill.domain.member.dto.response.MemberDTO;
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
		validateDuplicateEmail(memberSignUpRequest.email());
		validateDuplicateNickname(memberSignUpRequest.nickname());

		String encryptPassword = passwordEncoder.encrypt(memberSignUpRequest.password());
		Member member = memberRepository.save(MemberConverter.toMember(memberSignUpRequest, encryptPassword));
		return member.getId();
	}

	public AuthenticationDTO login(MemberLoginRequest memberLoginRequest) {
		return memberRepository.findByEmail(memberLoginRequest.email())
			.filter(member -> passwordEncoder.isMatch(memberLoginRequest.password(), member.getPassword()))
			.map(member -> new AuthenticationDTO(member.getId(), member.getMemberAuthority()))
			.orElseThrow(IllegalArgumentException::new);
	}

	public MemberDTO getById(Long id) {
		Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		return MemberConverter.toMemberResponse(member);
	}

	private void validateDuplicateEmail(String email) {
		if (memberRepository.existsByEmail(email)) {
			throw new IllegalArgumentException("중복된 아이디입니다.");
		}
	}

	private void validateDuplicateNickname(String nickname) {
		if (memberRepository.existsByNickname(nickname)) {
			throw new IllegalArgumentException("중복된 닉네입니다.");
		}
	}
}
