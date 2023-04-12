package com.geonwoo.solokill.domain.summoner.dto;

import lombok.Builder;

public record SummonerInfoResponse(
	String id,
	String accountId,
	String puuid,
	String name,
	Integer profileIconId,
	Integer revisionData,
	Integer summonerLevel
) {
	@Builder
	public SummonerInfoResponse(String id, String accountId, String puuid, String name, Integer profileIconId,
		Integer revisionData, Integer summonerLevel) {
		this.id = id;
		this.accountId = accountId;
		this.puuid = puuid;
		this.name = name;
		this.profileIconId = profileIconId;
		this.revisionData = revisionData;
		this.summonerLevel = summonerLevel;
	}
}
