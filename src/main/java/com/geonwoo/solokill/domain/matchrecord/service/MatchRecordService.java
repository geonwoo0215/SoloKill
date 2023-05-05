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
		Set<String> championNameByPuuid = matchRecordRepository.findChampionNameByPuuid(summonerInfoByName.puuid());
		return championNameByPuuid.stream().toList();
	}

	public List<PlayerMatchUpResponse> getPlayerMatchUpByChampionName(String name, String championName) {

		SummonerInfoResponse summonerInfoByName = summonerService.getSummonerInfoByName(name);

		List<MatchRecord> matchRecords = matchRecordRepository.findAllByPuuidAndChampionName(summonerInfoByName.puuid(),
			championName);

		Set<String> opponentChampion = new HashSet<>();

		List<MatchRecord> opponentMatchRecord = new ArrayList<>();

		for (MatchRecord matchRecord : matchRecords) {
			for (MatchRecord matchRecord1 : matchRecord.getMatchInfo().getMatchRecords()) {
				if (matchRecord1.isSameTeamPosition(matchRecord.getTeamPosition()) && !matchRecord1.isSameTeamId(
					matchRecord.getTeamId())) {
					opponentChampion.add(matchRecord1.getChampionName());
					opponentMatchRecord.add(matchRecord1);
				}
			}
		}

		List<PlayerMatchUpResponse> matchUpResponses = new ArrayList<>();
		for (String opponentChampionName : opponentChampion) {
			List<MatchRecord> matchRecordList = new ArrayList<>();
			List<MatchRecord> opponentRecordList = new ArrayList<>();
			for (MatchRecord matchRecord : matchRecords) {
				for (MatchRecord matchRecord1 : matchRecord.getMatchInfo().getMatchRecords()) {
					if (Objects.equals(opponentChampionName, matchRecord1.getChampionName())) {
						matchRecordList.add(matchRecord);
						opponentRecordList.add(matchRecord1);
					}
				}
			}
			Double soloKillAverage = matchRecordList.stream().mapToInt(MatchRecord::getSoloKills).average().orElse(0.0);
			Double visionScoreAverage = matchRecordList.stream()
				.mapToInt(MatchRecord::getVisionScore)
				.average()
				.orElse(0.0);
			Double visionWardsBoughtInGameAverage = matchRecordList.stream()
				.mapToInt(MatchRecord::getVisionWardsBoughtInGame)
				.average()
				.orElse(0.0);
			Double totalMinionsKilledAverage = matchRecordList.stream()
				.mapToInt(MatchRecord::getTotalMinionsKilled)
				.average()
				.orElse(0.0);
			Double totalDamageDealtToChampionsAverage = matchRecordList.stream()
				.mapToInt(MatchRecord::getTotalDamageDealtToChampions)
				.average()
				.orElse(0.0);
			Double goldEarnedAverage = matchRecordList.stream()
				.mapToInt(MatchRecord::getGoldEarned)
				.average()
				.orElse(0.0);
			Double killAverage = matchRecordList.stream().mapToInt(MatchRecord::getKills).average().orElse(0.0);
			Double deathAverage = matchRecordList.stream().mapToInt(MatchRecord::getDeaths).average().orElse(0.0);
			Double assistAverage = matchRecordList.stream().mapToInt(MatchRecord::getAssists).average().orElse(0.0);
			Double opponentSoloKillAverage = opponentRecordList.stream()
				.mapToInt(MatchRecord::getSoloKills)
				.average()
				.orElse(0.0);
			Double opponentVisionScoreAverage = opponentRecordList.stream()
				.mapToInt(MatchRecord::getVisionScore)
				.average()
				.orElse(0.0);
			Double opponentVisionWardsBoughtInGameAverage = opponentRecordList.stream().mapToInt(
				MatchRecord::getVisionWardsBoughtInGame).average().orElse(0.0);
			Double opponentTotalMinionsKilledAverage = opponentRecordList.stream().mapToInt(
				MatchRecord::getTotalMinionsKilled).average().orElse(0.0);
			Double opponentTotalDamageDealtToChampionsAverage = opponentRecordList.stream().mapToInt(
				MatchRecord::getTotalDamageDealtToChampions).average().orElse(0.0);
			Double opponentKillAverage = opponentRecordList.stream()
				.mapToInt(MatchRecord::getKills)
				.average()
				.orElse(0.0);
			Double opponentDeathAverage = opponentRecordList.stream()
				.mapToInt(MatchRecord::getDeaths)
				.average()
				.orElse(0.0);
			Double opponentAssistAverage = opponentRecordList.stream()
				.mapToInt(MatchRecord::getAssists)
				.average()
				.orElse(0.0);
			Double opponentGoldEarnedAverage = opponentRecordList.stream()
				.mapToInt(MatchRecord::getGoldEarned)
				.average()
				.orElse(0.0);
			Integer win = 0;
			Integer lose = 0;
			for (MatchRecord matchRecord : matchRecordList) {
				if (matchRecord.getWin()) {
					win++;
				} else {
					lose++;
				}
			}

			PlayerMatchUpResponse playerMatchUpResponse = PlayerMatchUpResponse.builder()
				.championName(championName)
				.soloKills(soloKillAverage)
				.visionScore(visionScoreAverage)
				.visionWardsBoughtInGame(visionWardsBoughtInGameAverage)
				.totalMinionsKilled(totalMinionsKilledAverage)
				.totalDamageDealtToChampions(totalDamageDealtToChampionsAverage)
				.goldEarned(goldEarnedAverage)
				.kills(killAverage)
				.deaths(deathAverage)
				.assists(assistAverage)
				.opponentChampionName(opponentChampionName)
				.opponentSoloKills(opponentSoloKillAverage)
				.opponentVisionScore(opponentVisionScoreAverage)
				.opponentVisionWardsBoughtInGame(opponentVisionWardsBoughtInGameAverage)
				.opponentTotalMinionsKilled(opponentTotalMinionsKilledAverage)
				.opponentTotalDamageDealtToChampions(opponentTotalDamageDealtToChampionsAverage)
				.opponentKills(opponentKillAverage)
				.opponentDeaths(opponentDeathAverage)
				.opponentAssists(opponentAssistAverage)
				.opponentGoldEarned(opponentGoldEarnedAverage)
				.win(win)
				.lose(lose)
				.build();

			matchUpResponses.add(playerMatchUpResponse);

		}
		return matchUpResponses;

	}
}
