package com.geonwoo.solokill.domain.member.controller;

import com.geonwoo.solokill.domain.member.model.dto.request.MemberLoginRequest;
import com.geonwoo.solokill.domain.member.model.dto.request.MemberSignUpRequest;
import com.geonwoo.solokill.domain.member.model.dto.response.MemberResponse;
import com.geonwoo.solokill.domain.member.service.MemberService;
import com.geonwoo.solokill.global.auth.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/members", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUp(
            @Valid @RequestBody MemberSignUpRequest memberSignUpRequest,
            HttpServletRequest request
    ) {

        Long saveMemberId = memberService.singUp(memberSignUpRequest);

        return ResponseEntity.created(URI.create(request.getRequestURI() + "/" + saveMemberId)).build();
    }

    @PostMapping(value = "/members/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> login(
            @Valid @RequestBody MemberLoginRequest memberLoginRequest,
            HttpServletRequest request
    ) {
        MemberResponse memberResponse = memberService.login(memberLoginRequest);

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionAttribute.LOGIN_MEMBER, memberResponse);

        return ResponseEntity.ok().build();
    }
}
