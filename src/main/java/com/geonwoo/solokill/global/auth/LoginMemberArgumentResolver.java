package com.geonwoo.solokill.global.auth;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.geonwoo.solokill.domain.member.dto.request.AuthenticationDTO;
import com.geonwoo.solokill.domain.member.service.MemberService;
import com.geonwoo.solokill.global.auth.annotation.LoginMember;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

	private final HttpSession httpSession;
	private final MemberService memberService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(LoginMember.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		AuthenticationDTO authenticationDTO = (AuthenticationDTO)httpSession.getAttribute(
			SessionAttribute.LOGIN_MEMBER);
		return memberService.getById(authenticationDTO.memberId());
	}
}
