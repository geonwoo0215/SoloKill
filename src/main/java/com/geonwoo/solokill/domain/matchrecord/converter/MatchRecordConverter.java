package com.geonwoo.solokill.domain.matchrecord.converter;

import org.springframework.stereotype.Component;

import com.geonwoo.solokill.domain.matchrecord.dto.ParticipantResponse;
import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;
import com.geonwoo.solokill.domain.summoner.model.Summoner;

@Component
public class MatchRecordConverter {

	public static MatchRecord toMatchRecord(ParticipantResponse participantResponse, String matchInfoId) {
		MatchRecord matchRecord = MatchRecord.builder()
			.summonerId(participantResponse.summonerId())
			.matchInfoId(matchInfoId)
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

		return matchRecord;
	}
}
