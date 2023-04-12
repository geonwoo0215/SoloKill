package com.geonwoo.solokill.domain.match.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer teamId;

	private String puuid;

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

	@OneToOne
	private Match opponentMatch;

	@Builder
	public Match(Long id, Integer teamId, String puuid, String teamPosition, Integer championId, String championName,
		Integer soloKills, Integer visionScore, Integer visionWardsBoughtInGame, Integer totalMinionsKilled,
		Integer totalDamageDealtToChampions, Integer goldEarned, Integer kills, Integer deaths, Integer assists,
		Boolean win) {
		this.id = id;
		this.teamId = teamId;
		this.puuid = puuid;
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

	public void addMatch(Match opponentMatch) {
		this.opponentMatch = opponentMatch;
	}

	public boolean isSameTeamPosition(String teamPosition) {
		return Objects.equals(this.teamPosition, teamPosition);
	}
}
