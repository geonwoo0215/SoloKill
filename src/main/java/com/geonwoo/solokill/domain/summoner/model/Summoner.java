package com.geonwoo.solokill.domain.summoner.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Persistable;

import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "summoner", indexes = {
	@Index(name = "idx_puuid", unique = true, columnList = "puuid"),
	@Index(name = "idx_name", unique = true, columnList = "name")
})
public class Summoner implements Persistable<String> {

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

	public void addMatchRecord(MatchRecord matchRecord) {
		this.match.add(matchRecord);
	}

}
