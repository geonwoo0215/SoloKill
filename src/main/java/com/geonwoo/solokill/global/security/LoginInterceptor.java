package com.geonwoo.solokill.global.security;

import static com.geonwoo.solokill.global.security.SessionAttribute.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {

		String requestURL = request.getRequestURL().toString();

		HttpSession httpSession = request.getSession(false);
		if (Objects.isNull(httpSession) || Objects.isNull(httpSession.getAttribute(LOGIN_MEMBER))) {
			String redirectURL = "login?redirectURL=" + URLEncoder.encode(requestURL, StandardCharsets.UTF_8);
			request.getRequestDispatcher(redirectURL).forward(request, response);
			return false;
		}
		return true;
	}

}
