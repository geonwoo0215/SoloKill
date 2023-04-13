package com.geonwoo.solokill.domain.playermatchrecord.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchInfo {

	@OneToMany(mappedBy = "matchInfo")
	private final List<PlayerMatchRecord> playerMatchRecords = new ArrayList<>();

	@Id
	private String matchId;

	public MatchInfo(String matchId) {
		this.matchId = matchId;
	}

	public void addPlayerMatchRecord(PlayerMatchRecord playerMatchRecords) {
		this.playerMatchRecords.add(playerMatchRecords);
		playerMatchRecords.addMatch(this);
	}
}
