package com.geonwoo.solokill.domain.matchrecord.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;

public interface MatchRecordRepository
	extends JpaRepository<MatchRecord, Long> {

	@Query("""
		SELECT m.championName
		FROM MatchRecord m
		WHERE m.summoner.puuid = :puuid
		""")
	Set<String> findChampionNameByPuuid(@Param("puuid") String puuid);
}
