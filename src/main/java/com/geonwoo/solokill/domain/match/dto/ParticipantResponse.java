package com.geonwoo.solokill.domain.match.dto;

public record ParticipantResponse(
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
        Boolean win,
        String summonerId,
        String puuid,
        String summonerName,
        Integer profileIcon,
        Integer summonerLevel
) {
}
