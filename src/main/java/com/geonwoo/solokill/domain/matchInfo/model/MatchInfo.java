package com.geonwoo.solokill.domain.matchInfo.model;

import java.util.ArrayList;
import java.util.List;

import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;

import jakarta.persistence.CascadeType;
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

	@OneToMany(mappedBy = "matchInfo", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private final List<MatchRecord> matchRecords = new ArrayList<>();

	@Id
	private String id;

	public MatchInfo(String id) {
		this.id = id;
	}

	public void addMatchRecord(MatchRecord matchRecords) {
		this.matchRecords.add(matchRecords);
		matchRecords.addMatch(this);
	}
}
