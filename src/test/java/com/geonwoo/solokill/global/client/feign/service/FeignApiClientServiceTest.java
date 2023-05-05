package com.geonwoo.solokill.global.client.feign.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geonwoo.solokill.domain.matchrecord.dto.ChallengesResponse;
import com.geonwoo.solokill.domain.matchrecord.dto.MatchInfo;
import com.geonwoo.solokill.domain.matchrecord.dto.MatchResponse;
import com.geonwoo.solokill.domain.matchrecord.dto.ParticipantResponse;
import com.geonwoo.solokill.domain.matchInfo.repository.MatchInfoRepository;
import com.geonwoo.solokill.domain.matchrecord.repository.MatchRecordRepository;
import com.geonwoo.solokill.domain.summoner.converter.SummonerConverter;
import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;
import com.geonwoo.solokill.domain.summoner.model.Summoner;
import com.geonwoo.solokill.domain.summoner.repository.SummonerRepository;
import com.geonwoo.solokill.global.client.feign.feignclient.RiotMatchOpenFeign;
import com.geonwoo.solokill.global.client.feign.feignclient.RiotSummonerOpenFeign;

@ExtendWith(MockitoExtension.class)
class FeignApiClientServiceTest {

	@InjectMocks
	private FeignApiClientService feignApiClientService;

	@Mock
	private RiotSummonerOpenFeign riotSummonerOpenFeign;

	@Mock
	private RiotMatchOpenFeign riotMatchOpenFeign;

	@Mock
	private SummonerRepository summonerRepository;

	@Mock
	private MatchInfoRepository matchInfoRepository;

	@Mock
	private MatchRecordRepository matchRecordRepository;

	@Test
	@DisplayName("소환사 이름으로 소환사 정보를 호출한다.")
	public void getSummonerInfoByName() {

		//given
		String name = "리거누";
		SummonerInfoResponse summonerInfoResponse = SummonerInfoResponse.builder()
			.id("id")
			.puuid("puuid")
			.name("리거누")
			.profileIconId(1234)
			.summonerLevel(344)
			.build();

		Summoner summoner = Summoner.builder()
			.id("id")
			.puuid("puuid")
			.name("리거누")
			.profileIconId(1234)
			.summonerLevel(344)
			.build();

		MockedStatic<SummonerConverter> summonerConverterMockedStatic = mockStatic(SummonerConverter.class);
		when(riotSummonerOpenFeign.getSummonerInfoByName(name)).thenReturn(summonerInfoResponse);
		when(SummonerConverter.toSummoner(summonerInfoResponse)).thenReturn(summoner);
		when(summonerRepository.save(summoner)).thenReturn(summoner);

		//when
		SummonerInfoResponse summonerInfoByName = feignApiClientService.getSummonerInfoByName(name);

		//then
		assertThat(summonerInfoByName)
			.hasFieldOrPropertyWithValue("id", summonerInfoResponse.id())
			.hasFieldOrPropertyWithValue("puuid", summonerInfoResponse.puuid())
			.hasFieldOrPropertyWithValue("profileIconId", summonerInfoResponse.profileIconId())
			.hasFieldOrPropertyWithValue("summonerLevel", summonerInfoResponse.summonerLevel());

		verify(riotSummonerOpenFeign).getSummonerInfoByName(name);
		verify(summonerRepository).save(summoner);
		summonerConverterMockedStatic.verify(() -> SummonerConverter.toSummoner(summonerInfoResponse), times(1));
	}

	@Test
	@DisplayName("소환사의 puuid로 경기id를 조회하고, 경기id로 경기를 조회하여 참여자들의 경기기록을 저장한다.")
	void getMatchInfoByPuuid() {

		String puuid = "puuid";
		String puuid1 = "puuid1";
		String gameType = "ranked";
		Integer gameCount = 3;
		String matchId = "matchId";
		List<String> matchIds = new ArrayList<>();
		matchIds.add(matchId);

		ChallengesResponse challengesResponse = new ChallengesResponse(1);
		ParticipantResponse participantResponse1 = new ParticipantResponse(1, "TOP", "Jayce", challengesResponse,
			1, 1, 1, 1, 1, 1, 1, 1, 1, true, "summoner1", puuid, "리거누", 1234, 324);
		ParticipantResponse participantResponse2 = new ParticipantResponse(2, "TOP", "Gnar",
			challengesResponse,
			1, 1, 1, 1, 1, 1, 1, 1, 1, false, "summoner2", puuid1, "Hide On Bush", 1234, 1234);
		List<ParticipantResponse> participants = new ArrayList<>();
		participants.add(participantResponse1);
		participants.add(participantResponse2);
		MatchInfo matchInfo = new MatchInfo(participants);
		MatchResponse matchResponse = new MatchResponse(matchInfo);

		when(riotMatchOpenFeign.getMatchId(puuid, gameType, gameCount)).thenReturn(matchIds);
		when(riotMatchOpenFeign.getMatchByMatchId(matchId)).thenReturn(matchResponse);
		when(matchInfoRepository.existsById(matchId)).thenReturn(false);

		feignApiClientService.getMatchInfoByPuuid(puuid);

		verify(riotMatchOpenFeign).getMatchId(puuid, gameType, gameCount);
		verify(riotMatchOpenFeign).getMatchByMatchId(matchId);
		verify(matchInfoRepository).existsById(matchId);
	}
}