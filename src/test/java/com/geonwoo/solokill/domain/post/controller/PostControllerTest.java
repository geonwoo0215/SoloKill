package com.geonwoo.solokill.domain.post.controller;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geonwoo.solokill.domain.member.dto.request.AuthenticationDTO;
import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.model.vo.MemberAuthority;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.post.dto.PostSaveRequest;
import com.geonwoo.solokill.domain.video.service.AwsS3UploadService;
import com.geonwoo.solokill.global.auth.SessionAttribute;
import com.geonwoo.solokill.global.firebase.FCMInitializer;
import com.geonwoo.solokill.global.firebase.service.FCMService;
import com.geonwoo.solokill.global.properties.NCPProperties;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private FCMInitializer fcmInitializer;

	@MockBean
	private FCMService fcmService;

	@MockBean
	private NCPProperties ncpProperties;

	@MockBean
	private AmazonS3 amazonS3;

	@MockBean
	private AwsS3UploadService awsS3UploadService;

	@Autowired
	private MemberRepository memberRepository;

	private Long memberId;

	@BeforeEach
	void setUp(WebApplicationContext webApplicationContext) {
		Member member = new Member("email", "password", "nickname");
		memberRepository.save(member);
		memberId = member.getId();

	}

	@Test
	@Transactional
	void save() throws Exception {

		String key = SessionAttribute.LOGIN_MEMBER;
		AuthenticationDTO authenticationDTO = new AuthenticationDTO(memberId, MemberAuthority.USER);

		PostSaveRequest postSaveRequest = new PostSaveRequest("title", "content");
		MockMultipartFile multipartFile = new MockMultipartFile("video", "video", "text/plain", "test file".getBytes(
			StandardCharsets.UTF_8));
		String json = objectMapper.writeValueAsString(postSaveRequest);

		MockMultipartFile post = new MockMultipartFile("post", "post", "application/json",
			json.getBytes(StandardCharsets.UTF_8));

		MockHttpSession mockHttpSession = new MockHttpSession();
		mockHttpSession.setAttribute(key, authenticationDTO);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/post")
				.file(multipartFile)
				.file(post)
				.session(mockHttpSession))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andDo(MockMvcResultHandlers.print());
	}

}