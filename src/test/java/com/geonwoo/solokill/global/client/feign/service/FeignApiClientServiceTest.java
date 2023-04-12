package com.geonwoo.solokill.global.client.feign.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;
import com.geonwoo.solokill.global.client.service.ApiClientService;

@ExtendWith(MockitoExtension.class)
class FeignApiClientServiceTest {

	@Mock
	private ApiClientService apiClientService;

	@Test
	@DisplayName("소환사 이름으로 소환사 정보를 호출한다.")
	public void getSummonerInfoByName() {

		String name = "리거누";
		SummonerInfoResponse summonerInfoResponse = SummonerInfoResponse.builder()
			.id("id")
			.accountId("accountId")
			.puuid("puuid")
			.name("리거누")
			.profileIconId(1234)
			.revisionDate(1234)
			.summonerLevel(344)
			.build();

		when(apiClientService.getSummonerInfoByName(name)).thenReturn(summonerInfoResponse);

		SummonerInfoResponse summonerInfoByName = apiClientService.getSummonerInfoByName(name);

		assertThat(summonerInfoByName)
			.hasFieldOrPropertyWithValue("id", summonerInfoResponse.id())
			.hasFieldOrPropertyWithValue("accountId", summonerInfoResponse.accountId())
			.hasFieldOrPropertyWithValue("puuid", summonerInfoResponse.puuid())
			.hasFieldOrPropertyWithValue("profileIconId", summonerInfoResponse.profileIconId())
			.hasFieldOrPropertyWithValue("revisionDate", summonerInfoResponse.revisionDate())
			.hasFieldOrPropertyWithValue("summonerLevel", summonerInfoResponse.summonerLevel());

		verify(apiClientService).getSummonerInfoByName(name);
	}

}