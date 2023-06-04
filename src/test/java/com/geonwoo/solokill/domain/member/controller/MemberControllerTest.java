package com.geonwoo.solokill.domain.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geonwoo.solokill.domain.member.dto.request.MemberLoginRequest;
import com.geonwoo.solokill.domain.member.dto.request.MemberSignUpRequest;
import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	@DisplayName("[성공] 회원가입에 성공한다.")
	void signUpSuccess() throws Exception {

		MemberSignUpRequest memberSignUpRequest = new MemberSignUpRequest("loginEmail", "password", "nickname");

		String json = objectMapper.writeValueAsString(memberSignUpRequest);

		mockMvc.perform(post("/members")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andExpect(status().isCreated())
			.andDo(print());

	}

	@Test
	@DisplayName("[성공] 로그인에 성공한다.")
	void loginSuccess() throws Exception {

		MemberLoginRequest memberLoginRequest = new MemberLoginRequest("loginEmail", "password");
		String encodedPassword = BCrypt.hashpw(memberLoginRequest.password(), BCrypt.gensalt());
		Member member = new Member("loginEmail", encodedPassword, "nickname");
		memberRepository.save(member);
		String json = objectMapper.writeValueAsString(memberLoginRequest);

		mockMvc.perform(post("/members/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andExpect(status().isNoContent())
			.andDo(print());

	}
}