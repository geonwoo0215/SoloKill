package com.geonwoo.solokill.domain.matchrecord.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geonwoo.solokill.domain.matchrecord.model.MatchRecord;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MatchRecordJdbcRepository {

	private final JdbcTemplate jdbcTemplate;

	public void insertMatchRecordList(List<MatchRecord> matchRecordList) {
		jdbcTemplate.batchUpdate(
			"insert into match_record (team_id, summoner_id, team_position, champion_id, champion_name, solo_kills, vision_score, vision_wards_bought_in_game, total_minions_killed, total_damage_dealt_to_champions, gold_earned, kills, deaths, assists, win, match_info_id) " +
				"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
			new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					MatchRecord matchRecord = matchRecordList.get(i);
					ps.setInt(1, matchRecord.getTeamId());
					ps.setString(2, matchRecord.getSummoner().getSummonerId());
					ps.setString(3, matchRecord.getTeamPosition());
					ps.setInt(4, matchRecord.getChampionId());
					ps.setString(5, matchRecord.getChampionName());
					ps.setInt(6, matchRecord.getSoloKills());
					ps.setInt(7, matchRecord.getVisionScore());
					ps.setInt(8, matchRecord.getVisionWardsBoughtInGame());
					ps.setInt(9, matchRecord.getTotalMinionsKilled());
					ps.setInt(10, matchRecord.getTotalDamageDealtToChampions());
					ps.setInt(11, matchRecord.getGoldEarned());
					ps.setInt(12, matchRecord.getKills());
					ps.setInt(13, matchRecord.getDeaths());
					ps.setInt(14, matchRecord.getAssists());
					ps.setBoolean(15, matchRecord.getWin());
					ps.setString(16, matchRecord.getMatchInfo().getMatchId());
				}

				@Override
				public int getBatchSize() {
					return matchRecordList.size();
				}
			});
	}
}
