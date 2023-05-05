package com.geonwoo.solokill.domain.matchrecord.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;
import com.geonwoo.solokill.domain.matchrecord.model.MatchRecordPk;

public interface MatchRecordRepository
	extends JpaRepository<MatchRecord, MatchRecordPk> {

	@Query("""
		SELECT m.championName
		FROM MatchRecord m
		WHERE m.summoner.puuid = :puuid
		""")
	Set<String> findChampionNameByPuuid(@Param("puuid") String puuid);

	@Query("""
		SELECT m
		FROM MatchRecord m
		WHERE m.summoner.puuid = :puuid AND m.championName = :championName
		""")
	List<MatchRecord> findAllByPuuidAndChampionName(@Param("puuid") String puuid,
		@Param("championName") String championName);
}
