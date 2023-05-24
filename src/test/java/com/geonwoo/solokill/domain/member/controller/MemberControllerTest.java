package com.geonwoo.solokill.domain.member.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geonwoo.solokill.domain.member.dto.request.MemberSignUpRequest;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("[성공] 회원가입에 성공한다.")
	void signUpSuccess() throws Exception {

		MemberSignUpRequest memberSignUpRequest = new MemberSignUpRequest("loginEmail", "password", "nickname");

		String json = objectMapper.writeValueAsString(memberSignUpRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andDo(MockMvcResultHandlers.print());

	}
}