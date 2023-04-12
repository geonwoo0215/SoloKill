package com.geonwoo.solokill.domain.match.converter;

import org.springframework.stereotype.Component;

import com.geonwoo.solokill.domain.match.dto.ParticipantResponse;
import com.geonwoo.solokill.domain.match.model.Match;

@Component
public class MatchConverter {

	public static Match toMatch(ParticipantResponse participantResponse) {
		return Match.builder()
			.puuid(participantResponse.puuid())
			.teamId(participantResponse.teamId())
			.teamPosition(participantResponse.teamPosition())
			.championName(participantResponse.championName())
			.soloKills(participantResponse.challenges().soloKills())
			.visionScore(participantResponse.visionScore())
			.visionWardsBoughtInGame(participantResponse.visionWardsBoughtInGame())
			.totalMinionsKilled(participantResponse.totalMinionsKilled())
			.totalDamageDealtToChampions(participantResponse.totalDamageDealtToChampions())
			.goldEarned(participantResponse.goldEarned())
			.championId(participantResponse.championId())
			.kills(participantResponse.kills())
			.deaths(participantResponse.deaths())
			.assists(participantResponse.assists())
			.win(participantResponse.win())
			.build();
	}
}
