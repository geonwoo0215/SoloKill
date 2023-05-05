package com.geonwoo.solokill.domain.summoner.converter;

import org.springframework.stereotype.Component;

import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;
import com.geonwoo.solokill.domain.summoner.model.Summoner;

@Component
public class SummonerConverter {

	public static Summoner toSummoner(SummonerInfoResponse summonerInfoResponse) {
		return Summoner.builder()
			.id(summonerInfoResponse.id())
			.puuid(summonerInfoResponse.puuid())
			.name(summonerInfoResponse.name())
			.profileIconId(summonerInfoResponse.profileIconId())
			.summonerLevel(summonerInfoResponse.summonerLevel())
			.build();
	}

	public static SummonerInfoResponse toSummonerInfoResponse(Summoner summoner) {
		return SummonerInfoResponse.builder()
			.id(summoner.getId())
			.puuid(summoner.getPuuid())
			.name(summoner.getName())
			.profileIconId(summoner.getProfileIconId())
			.summonerLevel(summoner.getSummonerLevel())
			.build();
	}
}
