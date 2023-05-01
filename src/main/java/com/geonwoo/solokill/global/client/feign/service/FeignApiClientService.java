package com.geonwoo.solokill.global.client.feign.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.geonwoo.solokill.domain.matchrecord.converter.MatchRecordConverter;
import com.geonwoo.solokill.domain.matchrecord.dto.MatchResponse;
import com.geonwoo.solokill.domain.matchInfo.model.MatchInfo;
import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;
import com.geonwoo.solokill.domain.matchrecord.repository.MatchInfoRepository;
import com.geonwoo.solokill.domain.matchrecord.repository.MatchRecordRepository;
import com.geonwoo.solokill.domain.summoner.converter.SummonerConverter;
import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;
import com.geonwoo.solokill.domain.summoner.model.Summoner;
import com.geonwoo.solokill.domain.summoner.repository.SummonerRepository;
import com.geonwoo.solokill.global.client.feign.feignclient.RiotMatchOpenFeign;
import com.geonwoo.solokill.global.client.feign.feignclient.RiotSummonerOpenFeign;
import com.geonwoo.solokill.global.client.service.ApiClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeignApiClientService implements ApiClientService {

	private static String GAME_TYPE = "ranked";
	private static Integer GAME_COUNT = 3;
	private final RiotSummonerOpenFeign riotSummonerOpenFeign;
	private final RiotMatchOpenFeign riotMatchOpenFeign;
	private final MatchInfoRepository matchRepository;
	private final SummonerRepository summonerRepository;
	private final MatchRecordRepository matchRecordRepository;

	@Override
	public SummonerInfoResponse getSummonerInfoByName(String name) {
		SummonerInfoResponse summonerInfoResponse = riotSummonerOpenFeign.getSummonerInfoByName(name);
		Summoner summoner = SummonerConverter.toSummoner(summonerInfoResponse);
		summonerRepository.save(summoner);
		return summonerInfoResponse;
	}

	@Override
	public void getMatchInfoByPuuid(String puuid) {

		List<String> matchIds = riotMatchOpenFeign.getMatchId(puuid, GAME_TYPE, GAME_COUNT);
		for (String matchId : matchIds) {
			if (matchRepository.findById(matchId).isPresent()) {
				continue;
			}
			MatchInfo matchInfo = new MatchInfo(matchId);
			MatchResponse matchResponse = riotMatchOpenFeign.getMatchByMatchId(matchId);
			log.info("{}", matchResponse);
			List<MatchRecord> playerMatchRecords = matchResponse.info().participants().stream().map(a -> {
				SummonerInfoResponse summonerInfoByPuuid = riotSummonerOpenFeign.getSummonerInfoByPuuid(a.puuid());
				Summoner summoner = SummonerConverter.toSummoner(summonerInfoByPuuid);
				summonerRepository.save(summoner);
				MatchRecord matchRecord = MatchRecordConverter.toMatchRecord(a);
				matchRecord.addSummoner(summoner);
				matchInfo.addMatchRecord(matchRecord);
				return matchRecord;
			}).toList();
			matchRepository.save(matchInfo);
			matchRecordRepository.saveAll(playerMatchRecords);
		}
	}
}
