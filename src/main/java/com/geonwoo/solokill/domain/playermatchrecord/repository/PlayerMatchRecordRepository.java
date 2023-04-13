package com.geonwoo.solokill.domain.playermatchrecord.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerMatchRecordRepository
	extends JpaRepository<com.geonwoo.solokill.domain.playermatchrecord.model.PlayerMatchRecord, Long> {

	@Query("""
		SELECT m.championName
		FROM PlayerMatchRecord m
		WHERE m.summoner.puuid = :puuid
		""")
	Set<String> findChampionNameByPuuid(@Param("puuid") String puuid);
}
