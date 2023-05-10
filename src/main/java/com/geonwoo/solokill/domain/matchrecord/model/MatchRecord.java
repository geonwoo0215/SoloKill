package com.geonwoo.solokill.domain.matchrecord.model;

import java.util.Objects;

import org.springframework.data.domain.Persistable;

import com.geonwoo.solokill.domain.matchInfo.model.MatchInfo;
import com.geonwoo.solokill.domain.summoner.model.Summoner;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchRecord implements Persistable<MatchRecordPk> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private MatchRecordPk matchRecordPk;

	@MapsId("summonerId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "summoner_id")
	private Summoner summoner;

	@MapsId("matchInfoId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "match_id")
	private MatchInfo matchInfo;

	private Integer teamId;

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

	@Builder
	public MatchRecord(String summonerId, String matchInfoId, Integer teamId, String teamPosition, Integer championId,
		String championName,
		Integer soloKills, Integer visionScore, Integer visionWardsBoughtInGame, Integer totalMinionsKilled,
		Integer totalDamageDealtToChampions, Integer goldEarned, Integer kills, Integer deaths, Integer assists,
		Boolean win) {
		this.matchRecordPk = new MatchRecordPk(summonerId, matchInfoId);
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
		summoner.addMatchRecord(this);
	}

	public void addMatch(MatchInfo matchInfo) {
		if (this.matchInfo != null) {
			return;
		}
		this.matchInfo = matchInfo;
		matchInfo.addMatchRecord(this);
	}

	public boolean isSameTeamPosition(String teamPosition) {
		return Objects.equals(this.teamPosition, teamPosition);
	}

	public boolean isSameTeamId(Integer teamId) {
		return Objects.equals(this.teamId, teamId);
	}

	@Transient
	private boolean isNew = true;

	@Override
	public MatchRecordPk getId() {
		return matchRecordPk;
	}

	@Override
	public boolean isNew() {
		return isNew;
	}

	@PrePersist
	@PostLoad
	void markNotNew() {
		this.isNew = false;
	}
}
