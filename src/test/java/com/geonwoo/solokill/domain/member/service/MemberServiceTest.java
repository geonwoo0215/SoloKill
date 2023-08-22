package com.geonwoo.solokill.domain.member.service;

import com.geonwoo.solokill.domain.member.exception.MemberNotFoundException;
import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.model.dto.request.MemberLoginRequest;
import com.geonwoo.solokill.domain.member.model.dto.request.MemberSignUpRequest;
import com.geonwoo.solokill.domain.member.model.dto.response.MemberResponse;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("[성공] 회원가입에 성공한다.")
    void singUp_success() {

        String email = "email";
        String password = "password";
        String nickname = "nickname";
        String encodePassword = "encodePassword";
        MemberSignUpRequest memberSignUpRequest = new MemberSignUpRequest(email, password, nickname);
        Member member = new Member(email, password, nickname);

        when(bCryptPasswordEncoder.encode(memberSignUpRequest.password())).thenReturn(encodePassword);
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        memberService.singUp(memberSignUpRequest);

        verify(bCryptPasswordEncoder).encode(memberSignUpRequest.password());
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    @DisplayName("[성공] 로그인에 성공한다.")
    void login_success() {

        String email = "email";
        String password = "password";
        String nickname = "nickname";
        String encodePassword = "encodePassword";
        Member member = new Member(email, encodePassword, nickname);

        MemberLoginRequest memberLoginRequest = new MemberLoginRequest(email, password);

        when(memberRepository.findByEmail(email)).thenReturn(Optional.of(member));
        when(bCryptPasswordEncoder.matches(password, encodePassword)).thenReturn(true);

        MemberResponse memberResponse = memberService.login(memberLoginRequest);

        assertThat(memberResponse)
                .hasFieldOrPropertyWithValue("email", email)
                .hasFieldOrPropertyWithValue("nickname", nickname);

        verify(memberRepository).findByEmail(email);
        verify(bCryptPasswordEncoder).matches(password, encodePassword);
    }

    @Test
    @DisplayName("[실패] 로그인에 실패한다.")
    void login_fail() {

        String email = "email";
        String password = "password";
        String nickname = "nickname";

        MemberLoginRequest memberLoginRequest = new MemberLoginRequest(email, password);

        when(memberRepository.findByEmail(email)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> memberService.login(memberLoginRequest))
                .isInstanceOf(MemberNotFoundException.class);

    }
}