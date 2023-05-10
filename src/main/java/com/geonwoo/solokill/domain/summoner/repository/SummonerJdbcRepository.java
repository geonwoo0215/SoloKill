package com.geonwoo.solokill.domain.summoner.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geonwoo.solokill.domain.summoner.model.Summoner;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SummonerJdbcRepository {

	private final JdbcTemplate jdbcTemplate;

	public void insertSummonerList(List<Summoner> summonerList) {
		jdbcTemplate.batchUpdate(
			"insert into summoner (name, profile_icon_id, puuid, summoner_id, summoner_level) values (?,?,?,?,?)",
			new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Summoner summoner = summonerList.get(i);
					ps.setString(1, summoner.getName());
					ps.setInt(2, summoner.getProfileIconId());
					ps.setString(3, summoner.getPuuid());
					ps.setString(4, summoner.getSummonerId());
					ps.setInt(5, summoner.getSummonerLevel());
				}

				@Override
				public int getBatchSize() {
					return summonerList.size();
				}
			});
	}
}
