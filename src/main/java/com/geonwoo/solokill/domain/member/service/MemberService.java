package com.geonwoo.solokill.domain.member.service;

import com.geonwoo.solokill.domain.member.exception.MemberNotFoundException;
import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.model.dto.request.MemberLoginRequest;
import com.geonwoo.solokill.domain.member.model.dto.request.MemberSignUpRequest;
import com.geonwoo.solokill.domain.member.model.dto.response.MemberResponse;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long singUp(MemberSignUpRequest memberSignUpRequest) {
        validateDuplicateEmail(memberSignUpRequest.email());
        validateDuplicateNickname(memberSignUpRequest.nickname());

        String encodePassword = bCryptPasswordEncoder.encode(memberSignUpRequest.password());
        Member member = new Member(memberSignUpRequest.email(), encodePassword, memberSignUpRequest.nickname());

        memberRepository.save(member);
        return member.getId();
    }

    public MemberResponse login(MemberLoginRequest memberLoginRequest) {

        return memberRepository.findByEmail(memberLoginRequest.email())
                .filter(member -> bCryptPasswordEncoder.matches(memberLoginRequest.password(), member.getPassword()))
                .map(MemberResponse::toMemberResponse)
                .orElseThrow(MemberNotFoundException::new);
    }

    public MemberResponse getById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return MemberResponse.toMemberResponse(member);
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
