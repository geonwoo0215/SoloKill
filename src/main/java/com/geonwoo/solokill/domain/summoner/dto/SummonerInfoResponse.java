package com.geonwoo.solokill.domain.summoner.dto;

import lombok.Builder;

public record SummonerInfoResponse(
	String id,
	String puuid,
	String name,
	Integer profileIconId,
	Integer summonerLevel
) {
	@Builder
	public SummonerInfoResponse(String id,String puuid, String name, Integer profileIconId, Integer summonerLevel) {
		this.id = id;
		this.puuid = puuid;
		this.name = name;
		this.profileIconId = profileIconId;
		this.summonerLevel = summonerLevel;
	}
}
