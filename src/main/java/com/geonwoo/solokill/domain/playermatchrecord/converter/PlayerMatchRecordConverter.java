package com.geonwoo.solokill.domain.playermatchrecord.converter;

import org.springframework.stereotype.Component;

import com.geonwoo.solokill.domain.playermatchrecord.dto.ParticipantResponse;
import com.geonwoo.solokill.domain.playermatchrecord.model.PlayerMatchRecord;

@Component
public class PlayerMatchRecordConverter {

	public static PlayerMatchRecord toPlayerMatchRecord(ParticipantResponse participantResponse) {
		return PlayerMatchRecord.builder()
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
