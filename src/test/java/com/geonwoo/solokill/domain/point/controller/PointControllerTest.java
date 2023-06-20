package com.geonwoo.solokill.domain.point.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class PointControllerTest {

	// @Autowired
	// private MockMvc mockMvc;
	//
	// @Autowired
	// private ObjectMapper objectMapper;
	//
	// @Autowired
	// private MemberRepository memberRepository;
	//
	// @MockBean
	// private FCMInitializer fcmInitializer;
	//
	// @MockBean
	// private FCMService fcmService;
	//
	// @MockBean
	// private LoginInterceptor loginInterceptor;
	//
	// @MockBean
	// private LoginMemberArgumentResolver loginMemberArgumentResolver;
	//
	// @MockBean
	// private AuthenticationInterceptor authenticationInterceptor;
	//
	// @Test
	// @DisplayName("[성공] 포인트 충전에 성공한다.")
	// void chargeSuccess() throws Exception {
	//
	// 	// MockHttpSession session = new MockHttpSession();
	// 	ChargeRequest chargeRequest = new ChargeRequest(1000L, "token");
	// 	String json = objectMapper.writeValueAsString(chargeRequest);
	//
	// 	Member member = new Member("gw980215@naver.com", "password", "nickname");
	// 	memberRepository.save(member);
	//
	// 	AuthenticationDTO authenticationDTO = new AuthenticationDTO(member.getId(), member.getMemberAuthority());
	// 	// session.setAttribute(SessionAttribute.LOGIN_MEMBER, authenticationDTO);
	//
	// 	mockMvc.perform(post("/point")
	// 			// .session(session)
	// 			.contentType(MediaType.APPLICATION_JSON)
	// 			.content(json))
	// 		.andExpect(status().isCreated())
	// 		.andDo(print());
	// }
}