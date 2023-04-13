package com.geonwoo.solokill.domain.playermatchrecord.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.geonwoo.solokill.domain.playermatchrecord.dto.PlayerChampionResponse;
import com.geonwoo.solokill.domain.playermatchrecord.repository.PlayerMatchRecordRepository;
import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;
import com.geonwoo.solokill.global.client.service.ApiClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerMatchRecordService {

	private final PlayerMatchRecordRepository matchRepository;
	private final ApiClientService apiClientService;

	public List<PlayerChampionResponse> getPlayerChampionByName(String name) {
		SummonerInfoResponse summonerInfoByName = apiClientService.getSummonerInfoByName(name);
		apiClientService.getMatchInfoByPuuid(summonerInfoByName.puuid());
		Set<String> championNameByPuuid = matchRepository.findChampionNameByPuuid(summonerInfoByName.puuid());
		return championNameByPuuid.stream().map(PlayerChampionResponse::new).toList();
	}
}
