package com.geonwoo.solokill.domain.matchrecord.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class MatchRecordPk implements Serializable {

	@Column(name = "summoner_id")
	private String summonerId;

	@Column(name = "match_info_id")
	private String matchInfoId;

}
