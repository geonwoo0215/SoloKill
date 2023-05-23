package com.geonwoo.solokill.domain.matchrecord.dto;

import lombok.Builder;

public record PlayerMatchUpResponse(
	String championName,
	Double soloKills,
	Double visionScore,
	Double visionWardsBoughtInGame,
	Double totalMinionsKilled,
	Double totalDamageDealtToChampions,
	Double goldEarned,
	Double kills,
	Double deaths,
	Double assists,
	String opponentChampionName,
	Double opponentSoloKills,
	Double opponentVisionScore,
	Double opponentVisionWardsBoughtInGame,
	Double opponentTotalMinionsKilled,
	Double opponentTotalDamageDealtToChampions,
	Double opponentGoldEarned,
	Double opponentKills,
	Double opponentDeaths,
	Double opponentAssists,
	Long win,
	Long lose
) {

	public PlayerMatchUpResponse(String championName, Double soloKills, Double visionScore,
		Double visionWardsBoughtInGame,
		Double totalMinionsKilled, Double totalDamageDealtToChampions, Double goldEarned, Double kills,
		Double deaths, Double assists, String opponentChampionName, Double opponentSoloKills,
		Double opponentVisionScore,
		Double opponentVisionWardsBoughtInGame, Double opponentTotalMinionsKilled,
		Double opponentTotalDamageDealtToChampions, Double opponentGoldEarned,
		Double opponentKills, Double opponentDeaths, Double opponentAssists, Long win, Long lose) {
		this.championName = championName;
		this.soloKills = soloKills;
		this.visionScore = visionScore;
		this.visionWardsBoughtInGame = visionWardsBoughtInGame;
		this.totalMinionsKilled = totalMinionsKilled;
		this.totalDamageDealtToChampions = totalDamageDealtToChampions;
		this.goldEarned = goldEarned;
		this.kills = kills;
		this.deaths = deaths;
		this.assists = assists;
		this.opponentChampionName = opponentChampionName;
		this.opponentSoloKills = opponentSoloKills;
		this.opponentVisionScore = opponentVisionScore;
		this.opponentVisionWardsBoughtInGame = opponentVisionWardsBoughtInGame;
		this.opponentTotalMinionsKilled = opponentTotalMinionsKilled;
		this.opponentTotalDamageDealtToChampions = opponentTotalDamageDealtToChampions;
		this.opponentGoldEarned = opponentGoldEarned;
		this.opponentKills = opponentKills;
		this.opponentDeaths = opponentDeaths;
		this.opponentAssists = opponentAssists;
		this.win = win;
		this.lose = lose;
	}
}
