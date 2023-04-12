package com.geonwoo.solokill.domain.summoner.dto;

import lombok.Builder;

public record SummonerInfoResponse(
	String id,
	String accountId,
	String puuid,
	String name,
	Integer profileIconId,
	Integer revisionDate,
	Integer summonerLevel
) {
	@Builder
	public SummonerInfoResponse(String id, String accountId, String puuid, String name, Integer profileIconId,
		Integer revisionDate, Integer summonerLevel) {
		this.id = id;
		this.accountId = accountId;
		this.puuid = puuid;
		this.name = name;
		this.profileIconId = profileIconId;
		this.revisionDate = revisionDate;
		this.summonerLevel = summonerLevel;
	}
}
