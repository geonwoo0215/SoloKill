package com.geonwoo.solokill.domain.matchInfo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Persistable;

import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;

import jakarta.persistence.Entity;
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
public class MatchInfo implements Persistable<String> {

	@OneToMany(mappedBy = "matchInfo", orphanRemoval = true)
	private final List<MatchRecord> matchRecords = new ArrayList<>();

	@Id
	private String id;

	public MatchInfo(String id) {
		this.id = id;
	}

	public void addMatchRecord(MatchRecord matchRecords) {
		this.matchRecords.add(matchRecords);
	}

	@Transient
	private boolean isNew = true;

	@Override
	public String getId() {
		return id;
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
