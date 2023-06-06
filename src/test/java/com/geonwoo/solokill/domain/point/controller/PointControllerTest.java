package com.geonwoo.solokill.domain.point.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geonwoo.solokill.domain.member.dto.request.AuthenticationDTO;
import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.point.dto.ChargeRequest;
import com.geonwoo.solokill.global.auth.SessionAttribute;

@SpringBootTest
@AutoConfigureMockMvc
class PointControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	@DisplayName("[성공] 포인트 충전에 성공한다.")
	void chargeSuccess() throws Exception {

		MockHttpSession session = new MockHttpSession();

		ChargeRequest chargeRequest = new ChargeRequest(1000L, "token");
		String json = objectMapper.writeValueAsString(chargeRequest);

		Member member = new Member("gw980215@naver.com", "password", "nickname");
		memberRepository.save(member);

		AuthenticationDTO authenticationDTO = new AuthenticationDTO(member.getId(), member.getMemberAuthority());
		session.setAttribute(SessionAttribute.LOGIN_MEMBER, authenticationDTO);

		mockMvc.perform(post("/point")
				.session(session)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andExpect(status().isCreated())
			.andDo(print());
	}
}