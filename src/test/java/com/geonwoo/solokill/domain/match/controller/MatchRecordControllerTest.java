package com.geonwoo.solokill.domain.match.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.geonwoo.solokill.global.firebase.FCMInitializer;
import com.geonwoo.solokill.global.firebase.service.FCMService;
import com.geonwoo.solokill.global.properties.NCPProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MatchRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FCMInitializer fcmInitializer;

    @MockBean
    private FCMService fcmService;

    @MockBean
    private NCPProperties ncpProperties;

    @MockBean
    private AmazonS3 amazonS3;


}