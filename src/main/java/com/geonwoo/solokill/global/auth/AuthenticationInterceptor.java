package com.geonwoo.solokill.global.auth;

import static com.geonwoo.solokill.global.auth.SessionAttribute.*;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.geonwoo.solokill.domain.member.dto.request.AuthenticationDTO;
import com.geonwoo.solokill.domain.member.exception.AuthenticationException;
import com.geonwoo.solokill.global.auth.annotation.Auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		if (Objects.isNull(auth)) {
			return true;
		}

		HttpSession session = request.getSession(false);
		AuthenticationDTO authenticationDTO = (AuthenticationDTO)session.getAttribute(LOGIN_MEMBER);

		if (Objects.isNull(authenticationDTO)) {
			throw new AuthenticationException();
		}

		boolean hasAuthority = Arrays.stream(auth.allowedRoles()).anyMatch(authority -> authority.equals(
			authenticationDTO.memberAuthority()
		));
		if (hasAuthority) {
			return true;
		}

		throw new AuthenticationException();

	}
}
