package com.geonwoo.solokill.domain.matchrecord.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.geonwoo.solokill.domain.matchrecord.dto.PlayerChampionResponse;
import com.geonwoo.solokill.domain.matchrecord.repository.MatchRecordRepository;
import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;
import com.geonwoo.solokill.global.client.service.ApiClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchRecordService {

	private final MatchRecordRepository matchRepository;
	private final ApiClientService apiClientService;

	public List<PlayerChampionResponse> getPlayerChampionByName(String name) {
		SummonerInfoResponse summonerInfoByName = apiClientService.getSummonerInfoByName(name);
		apiClientService.getMatchInfoByPuuid(summonerInfoByName.puuid());
		Set<String> championNameByPuuid = matchRepository.findChampionNameByPuuid(summonerInfoByName.puuid());
		return championNameByPuuid.stream().map(PlayerChampionResponse::new).toList();
	}
}
