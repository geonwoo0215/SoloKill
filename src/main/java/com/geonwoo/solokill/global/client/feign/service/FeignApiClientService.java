package com.geonwoo.solokill.global.client.feign.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.solokill.domain.matchInfo.model.MatchInfo;
import com.geonwoo.solokill.domain.matchInfo.repository.MatchInfoRepository;
import com.geonwoo.solokill.domain.matchrecord.converter.MatchRecordConverter;
import com.geonwoo.solokill.domain.matchrecord.dto.MatchResponse;
import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;
import com.geonwoo.solokill.domain.matchrecord.model.MatchRecordPk;
import com.geonwoo.solokill.domain.matchrecord.repository.MatchRecordRepository;
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
@Transactional(readOnly = true)
public class FeignApiClientService implements ApiClientService {

	private static String GAME_TYPE = "ranked";
	private static Integer GAME_COUNT = 3;
	private final RiotSummonerOpenFeign riotSummonerOpenFeign;
	private final RiotMatchOpenFeign riotMatchOpenFeign;
	private final MatchInfoRepository matchInfoRepository;
	private final MatchRecordRepository matchRecordRepository;
	private final SummonerRepository summonerRepository;

	@Override
	@Transactional
	public SummonerInfoResponse getSummonerInfoByName(String name) {
		SummonerInfoResponse summonerInfoResponse = riotSummonerOpenFeign.getSummonerInfoByName(name);
		Summoner summoner = SummonerConverter.toSummoner(summonerInfoResponse);
		summonerRepository.save(summoner);
		return summonerInfoResponse;
	}

	@Override
	@Transactional
	public void getMatchInfoByPuuid(String puuid) {
		List<String> matchIds = riotMatchOpenFeign.getMatchId(puuid, GAME_TYPE, GAME_COUNT);
		List<MatchRecord> matchRecordList = new ArrayList<>();
		matchIds.forEach(matchId -> {
			if (!matchInfoRepository.existsById(matchId)) {
				MatchInfo matchInfo = new MatchInfo(matchId);
				MatchResponse matchResponse = riotMatchOpenFeign.getMatchByMatchId(matchId);

				matchResponse.info().participants().forEach(participantResponse ->
					{
						if (!matchRecordRepository.existsById(
							new MatchRecordPk(participantResponse.summonerId(), matchId))) {
							MatchRecord matchRecord = MatchRecordConverter.toMatchRecord(participantResponse, matchId);
							Summoner summoner = summonerRepository.findById(participantResponse.summonerId())
								.orElseGet(() ->
									SummonerConverter.toSummoner(new SummonerInfoResponse(
										participantResponse.summonerId(),
										participantResponse.puuid(),
										participantResponse.summonerName(),
										participantResponse.profileIcon(),
										participantResponse.summonerLevel()))
								);
							matchRecord.addSummoner(summoner);
							matchRecord.addMatch(matchInfo);
							matchRecordList.add(matchRecord);
							matchRecordRepository.saveAll(matchRecordList);
						}
					}
				);
			}
		});
	}
}
