package com.geonwoo.solokill.domain.match.dto;

public record ParticipantResponse(
	String puuid,
	Integer teamId,
	String teamPosition,
	String championName,
	ChallengesResponse challenges,
	Integer visionScore,
	Integer visionWardsBoughtInGame,
	Integer totalMinionsKilled,
	Integer totalDamageDealtToChampions,
	Integer goldEarned,
	Integer championId,
	Integer kills,
	Integer deaths,
	Integer assists,
	Boolean win
) {
}
