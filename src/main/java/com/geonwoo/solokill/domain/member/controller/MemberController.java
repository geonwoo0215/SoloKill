package com.geonwoo.solokill.domain.member.controller;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geonwoo.solokill.domain.member.dto.request.MemberSignUpRequest;
import com.geonwoo.solokill.domain.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> signUp(
		@Valid @RequestBody MemberSignUpRequest memberSignUpRequest,
		HttpServletRequest request
	) {

		Long saveMemberId = memberService.singUp(memberSignUpRequest);

		return ResponseEntity.created(URI.create(request.getRequestURI() + "/" + saveMemberId)).build();
	}
}
