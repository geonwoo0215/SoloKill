package com.geonwoo.solokill.domain.post.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.video.service.AwsS3UploadService;
import com.geonwoo.solokill.global.firebase.FCMInitializer;
import com.geonwoo.solokill.global.firebase.service.FCMService;
import com.geonwoo.solokill.global.properties.NCPProperties;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

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


}