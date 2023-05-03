package com.geonwoo.solokill.domain.summoner.model;

import java.util.ArrayList;
import java.util.List;

import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "summoner", indexes = @Index(name = "idx_puuid", columnList = "puuid"))
public class Summoner {

	@Id
	private String id;

	private String puuid;

	private String name;

	private Integer profileIconId;

	private Integer summonerLevel;

	@OneToMany(mappedBy = "summoner")
	private List<MatchRecord> match = new ArrayList<>();

	@Builder
	protected Summoner(String id, String puuid, String name, Integer profileIconId, Integer summonerLevel) {
		this.id = id;
		this.puuid = puuid;
		this.name = name;
		this.profileIconId = profileIconId;
		this.summonerLevel = summonerLevel;
	}

	public void addMatch(MatchRecord match) {
		this.match.add(match);
	}
}
