package com.geonwoo.solokill.domain.member.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.geonwoo.solokill.domain.member.dto.request.MemberSignUpRequest;
import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private Member member;

	@InjectMocks
	private MemberService memberService;

	private MemberSignUpRequest memberSignUpRequest = new MemberSignUpRequest("loginEmail", "password", "nickname");

	@Test
	@DisplayName("[성공] 회원가입에 성공한다.")
	void singUp() {

		String encryptPassword = BCrypt.hashpw(memberSignUpRequest.password(), BCrypt.gensalt());
		Long memberId = 1L;

		Mockito.when(memberRepository.existsByEmail(memberSignUpRequest.email())).thenReturn(false);
		Mockito.when(memberRepository.existsByNickname(memberSignUpRequest.nickname())).thenReturn(false);
		Mockito.when(passwordEncoder.encrypt(memberSignUpRequest.password())).thenReturn(encryptPassword);
		Mockito.when(memberRepository.save(Mockito.any(Member.class))).thenReturn(member);
		Mockito.when(member.getId()).thenReturn(memberId);
		Long saveMemberId = memberService.singUp(memberSignUpRequest);

		Mockito.verify(memberRepository).existsByEmail(memberSignUpRequest.email());
		Mockito.verify(memberRepository).existsByNickname(memberSignUpRequest.nickname());
		Mockito.verify(passwordEncoder).encrypt(memberSignUpRequest.password());
		Mockito.verify(memberRepository).save(Mockito.any(Member.class));
		Mockito.verify(member).getId();

		Assertions.assertThat(saveMemberId).isEqualTo(memberId);
	}
}