package com.geonwoo.solokill.domain.summoner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Summoner {

	@Id
	private String id;

	private String accountId;

	private String puuid;

	private String name;

	private Integer profileIconId;

	private Integer revisionDate;

	private Integer summonerLevel;

	@Builder
	protected Summoner(String id, String accountId, String puuid, String name, Integer profileIconId,
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
