package com.geonwoo.solokill.domain.matchrecord.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geonwoo.solokill.domain.matchrecord.repository.MatchRecordRepository;
import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;
import com.geonwoo.solokill.domain.summoner.service.SummonerService;
import com.geonwoo.solokill.global.client.service.ApiClientService;

@ExtendWith(MockitoExtension.class)
class MatchRecordServiceTest {

	@Mock
	private SummonerService summonerService;

	@Mock
	private ApiClientService apiClientService;

	@Mock
	private MatchRecordRepository matchRecordRepository;

	@InjectMocks
	private MatchRecordService matchRecordService;

	@Test
	void getPlayerChampionByName() {
		String name = "리거누";
		Set<String> championName = new HashSet<>();
		championName.add("Jayce");
		SummonerInfoResponse summonerInfoResponse = SummonerInfoResponse.builder()
			.id("id")
			.summonerLevel(234)
			.name("리거누")
			.puuid("puuid")
			.profileIconId(123)
			.build();

		when(summonerService.getSummonerInfoByName(name)).thenReturn(summonerInfoResponse);
		doNothing().when(apiClientService).getMatchInfoByPuuid(summonerInfoResponse.puuid());
		when(matchRecordRepository.findChampionNameBySummonerId(summonerInfoResponse.puuid())).thenReturn(championName);

		List<String> responses = matchRecordService.getPlayerChampionByName(name);

		assertTrue(responses.contains("Jayce"));

		verify(summonerService).getSummonerInfoByName(name);
		verify(apiClientService).getMatchInfoByPuuid("puuid");
		verify(matchRecordRepository).findChampionNameBySummonerId("puuid");
	}
}