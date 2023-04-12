package com.geonwoo.solokill.global.client.feign.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.geonwoo.solokill.domain.match.converter.MatchConverter;
import com.geonwoo.solokill.domain.match.dto.MatchResponse;
import com.geonwoo.solokill.domain.match.dto.ParticipantResponse;
import com.geonwoo.solokill.domain.match.model.Match;
import com.geonwoo.solokill.domain.match.repository.MatchRepository;
import com.geonwoo.solokill.domain.summoner.converter.SummonerConverter;
import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;
import com.geonwoo.solokill.domain.summoner.model.Summoner;
import com.geonwoo.solokill.domain.summoner.repository.SummonerRepository;
import com.geonwoo.solokill.global.client.feign.feignclient.RiotMatchOpenFeign;
import com.geonwoo.solokill.global.client.feign.feignclient.RiotSummonerOpenFeign;
import com.geonwoo.solokill.global.client.service.ApiClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeignApiClientService implements ApiClientService {

	private static String GAME_TYPE = "ranked";
	private static Integer GAME_COUNT = 50;
	private final RiotSummonerOpenFeign riotSummonerOpenFeign;
	private final RiotMatchOpenFeign riotMatchOpenFeign;
	private final MatchRepository matchRepository;
	private final SummonerRepository summonerRepository;

	@Override
	public SummonerInfoResponse getSummonerInfoByName(String name) {
		SummonerInfoResponse summonerInfoResponse = riotSummonerOpenFeign.getSummonerInfoByName(name);
		Summoner summoner = SummonerConverter.toSummoner(summonerInfoResponse);
		summonerRepository.save(summoner);
		return summonerInfoResponse;
	}

	@Override
	public void getMatchInfoByPuuid(String puuid) {
		List<String> matchIdResponses = riotMatchOpenFeign.getMatchId(puuid, GAME_TYPE, GAME_COUNT);

		for (String matchId : matchIdResponses) {
			MatchResponse matchResponse = riotMatchOpenFeign.getMatchByMatchId(matchId);
			List<ParticipantResponse> participantResponses = matchResponse.info().participants();

			String teamPosition = "";
			List<Match> matchInfos = new ArrayList<>();

			for (ParticipantResponse participantResponse : participantResponses) {
				if (puuid.equals(participantResponse.puuid())) {
					teamPosition = participantResponse.teamPosition();
					Match match = MatchConverter.toMatch(participantResponse);
					matchInfos.add(match);
				}
			}
			for (ParticipantResponse participantResponse : participantResponses) {
				if (teamPosition.equals(participantResponse.teamPosition()) && !puuid.equals(
					participantResponse.puuid())) {
					Match match = MatchConverter.toMatch(participantResponse);

					matchInfos.add(match);
				}
			}
			Match match1;
			Match match2;
			if (matchInfos.size() >= 2) {
				match1 = matchInfos.get(0);
				match2 = matchInfos.get(1);
				match1.addMatch(match2);
				match2.addMatch(match1);
				matchRepository.save(match1);
				matchRepository.save(match2);
			}
		}
	}
}
