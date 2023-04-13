package com.geonwoo.solokill.domain.matchrecord.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchInfo {

	@OneToMany(mappedBy = "matchInfo")
	private final List<MatchRecord> matchRecords = new ArrayList<>();

	@Id
	private String matchId;

	public MatchInfo(String matchId) {
		this.matchId = matchId;
	}

	public void addMatchRecord(MatchRecord matchRecords) {
		this.matchRecords.add(matchRecords);
		matchRecords.addMatch(this);
	}
}
