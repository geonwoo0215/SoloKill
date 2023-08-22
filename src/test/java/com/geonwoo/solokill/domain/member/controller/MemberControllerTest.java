package com.geonwoo.solokill.domain.member.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geonwoo.solokill.domain.member.model.dto.request.MemberLoginRequest;
import com.geonwoo.solokill.domain.member.model.dto.request.MemberSignUpRequest;
import com.geonwoo.solokill.domain.member.service.MemberService;
import com.geonwoo.solokill.global.firebase.FCMInitializer;
import com.geonwoo.solokill.global.firebase.service.FCMService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberService memberService;

    @MockBean
    private FCMInitializer fcmInitializer;

    @MockBean
    private FCMService fcmService;

    @MockBean
    private AmazonS3 amazonS3;

    @Test
    @DisplayName("[성공] 회원가입에 성공한다.")
    void signUpSuccess() throws Exception {

        String email = "email";
        String password = "password";
        String nickname = "nickname";

        MemberSignUpRequest memberSignUpRequest = new MemberSignUpRequest(email, password, nickname);

        String json = objectMapper.writeValueAsString(memberSignUpRequest);

        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    @DisplayName(("[성공] 로그인에 성공한다."))
    void login() throws Exception {

        String email = "email";
        String password = "password";
        String nickname = "nickname";
        MemberSignUpRequest memberSignUpRequest = new MemberSignUpRequest(email, password, nickname);
        MemberLoginRequest memberLoginRequest = new MemberLoginRequest(email, password);

        memberService.singUp(memberSignUpRequest);

        String json = objectMapper.writeValueAsString(memberLoginRequest);

        mockMvc.perform(post("/members/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

    }
}