package com.geonwoo.solokill.domain.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geonwoo.solokill.domain.member.dto.request.MemberSignUpRequest;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.video.service.AwsS3UploadService;
import com.geonwoo.solokill.global.firebase.FCMInitializer;
import com.geonwoo.solokill.global.firebase.service.FCMService;

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

	@MockBean
	private FCMInitializer fcmInitializer;

	@MockBean
	private FCMService fcmService;

	@MockBean
	private AmazonS3 amazonS3;

	@MockBean
	private AwsS3UploadService awsS3UploadService;
	
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
}