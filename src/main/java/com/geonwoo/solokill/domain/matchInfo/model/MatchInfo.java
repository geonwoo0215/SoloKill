package com.geonwoo.solokill.domain.matchInfo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Persistable;

import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String matchId;

	@OneToMany(mappedBy = "matchInfo", orphanRemoval = true)
	private final List<MatchRecord> matchRecords = new ArrayList<>();

	public MatchInfo(String matchId) {
		this.matchId = matchId;
	}

	public void addMatchRecord(MatchRecord matchRecords) {
		this.matchRecords.add(matchRecords);
	}

}
