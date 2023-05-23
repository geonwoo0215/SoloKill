package com.geonwoo.solokill.domain.matchrecord.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.solokill.domain.matchrecord.dto.PlayerMatchUpResponse;
import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;
import com.geonwoo.solokill.domain.matchrecord.repository.MatchRecordRepository;
import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;
import com.geonwoo.solokill.domain.summoner.service.SummonerService;
import com.geonwoo.solokill.global.client.service.ApiClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchRecordService {

	private final MatchRecordRepository matchRecordRepository;
	private final SummonerService summonerService;
	private final ApiClientService apiClientService;

	@Transactional
	public List<String> getPlayerChampionByName(String name) {
		SummonerInfoResponse summonerInfoByName = summonerService.getSummonerInfoByName(name);
		apiClientService.getMatchInfoByPuuid(summonerInfoByName.puuid());
		Set<String> championNameByPuuid = matchRecordRepository.findChampionNameBySummonerId(summonerInfoByName.id());
		return championNameByPuuid.stream().toList();
	}

	public List<PlayerMatchUpResponse> getPlayerMatchUpByChampionName(String name, String championName) {

		SummonerInfoResponse summonerInfoByName = summonerService.getSummonerInfoByName(name);

		return matchRecordRepository.findAllByIdAndChampionName(
			summonerInfoByName.id(), championName);

	}
}
