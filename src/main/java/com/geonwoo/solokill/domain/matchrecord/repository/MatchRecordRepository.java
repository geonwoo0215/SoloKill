package com.geonwoo.solokill.domain.matchrecord.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geonwoo.solokill.domain.matchrecord.dto.PlayerMatchUpResponse;
import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;
import com.geonwoo.solokill.domain.matchrecord.model.MatchRecordPk;

public interface MatchRecordRepository extends JpaRepository<MatchRecord, MatchRecordPk> {

	@Query("""
		SELECT m.championName
		FROM MatchRecord m
		WHERE m.matchRecordPk.summonerId = :id
		""")
	Set<String> findChampionNameBySummonerId(@Param("id") String id);

	@Query("""
		SELECT new com.geonwoo.solokill.domain.matchrecord.dto.PlayerMatchUpResponse(
		mr.championName,
		avg(mr.soloKills),
		avg(mr.visionScore),
		avg(mr.visionWardsBoughtInGame),
		avg(mr.totalMinionsKilled),
		avg(mr.totalDamageDealtToChampions),
		avg(mr.goldEarned),
		avg(mr.kills),
		avg(mr.deaths),
		avg(mr.assists),
		opponent.championName,
		avg(opponent.soloKills),
		avg(opponent.visionScore),
		avg(opponent.visionWardsBoughtInGame),
		avg(opponent.totalMinionsKilled),
		avg(opponent.totalDamageDealtToChampions),
		avg(opponent.kills),
		avg(opponent.deaths),
		avg(opponent.assists),
		avg(opponent.goldEarned),
		sum(case when mr.win = true then 1 else 0 end),
		sum(case when mr.win = false then 1 else 0 end))
		FROM MatchRecord mr
		JOIN MatchRecord opponent ON mr.matchInfo = opponent.matchInfo
		WHERE mr.summoner.id = :id
		AND mr.championName = :championName
		AND mr.teamPosition = opponent.teamPosition
		AND mr.teamId != opponent.teamId
		GROUP BY opponent.championName
		""")
	List<PlayerMatchUpResponse> findAllByIdAndChampionName(@Param("id") String id,
		@Param("championName") String championName);
}
