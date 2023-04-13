package com.geonwoo.solokill.domain.matchrecord.model;

import java.util.Objects;

import com.geonwoo.solokill.domain.summoner.model.Summoner;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer teamId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "puuid", name = "puuid")
	private Summoner summoner;

	private String teamPosition;

	private Integer championId;

	private String championName;

	private Integer soloKills;

	private Integer visionScore;

	private Integer visionWardsBoughtInGame;

	private Integer totalMinionsKilled;

	private Integer totalDamageDealtToChampions;

	private Integer goldEarned;

	private Integer kills;

	private Integer deaths;

	private Integer assists;

	private Boolean win;

	@ManyToOne
	@JoinColumn(name = "match_info_id")
	private MatchInfo matchInfo;

	@Builder
	public MatchRecord(Long id, Integer teamId, String teamPosition, Integer championId,
		String championName,
		Integer soloKills, Integer visionScore, Integer visionWardsBoughtInGame, Integer totalMinionsKilled,
		Integer totalDamageDealtToChampions, Integer goldEarned, Integer kills, Integer deaths, Integer assists,
		Boolean win) {
		this.id = id;
		this.teamId = teamId;
		this.teamPosition = teamPosition;
		this.championId = championId;
		this.championName = championName;
		this.soloKills = soloKills;
		this.visionScore = visionScore;
		this.visionWardsBoughtInGame = visionWardsBoughtInGame;
		this.totalMinionsKilled = totalMinionsKilled;
		this.totalDamageDealtToChampions = totalDamageDealtToChampions;
		this.goldEarned = goldEarned;
		this.kills = kills;
		this.deaths = deaths;
		this.assists = assists;
		this.win = win;
	}

	public void addSummoner(Summoner summoner) {
		if (this.summoner != null) {
			return;
		}
		this.summoner = summoner;
		summoner.addMatch(this);
	}

	public void addMatch(MatchInfo matchInfo) {
		if (this.matchInfo != null) {
			return;
		}
		this.matchInfo = matchInfo;
	}

	public boolean isSameTeamPosition(String teamPosition) {
		return Objects.equals(this.teamPosition, teamPosition);
	}
}
