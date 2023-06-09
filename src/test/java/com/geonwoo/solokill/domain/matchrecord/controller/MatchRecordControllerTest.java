package com.geonwoo.solokill.domain.matchrecord.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.s3.AmazonS3;
import com.geonwoo.solokill.global.firebase.FCMInitializer;
import com.geonwoo.solokill.global.firebase.service.FCMService;
import com.geonwoo.solokill.global.properties.NCPProperties;

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

	@Test
	@Transactional
	@DisplayName("[성공] 사용자 이름으로 사용자가 최근 사용한 챔피언 이름을 반환한다.")
	void getSummonerInfo() throws Exception {

		String summonerName = "리거누";

		mockMvc.perform(MockMvcRequestBuilders.get("/matchRecord/{summonerName}", summonerName)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print());

	}

	@Test
	@Transactional
	@Sql(scripts = {"/sql/dummy.sql"})
	@DisplayName("[성공] 사용자 이름과 챔피언 이름으로 경기 기록을 반환한다.")
	void getMatchUp() throws Exception {

		String summonerName = "리거누";
		String championName = "Jayce";
		mockMvc.perform(
				MockMvcRequestBuilders.get("/matchRecord/{summonerName}/{championName}", summonerName, championName)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print());

	}
}