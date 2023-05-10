package com.geonwoo.solokill.domain.matchInfo.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geonwoo.solokill.domain.matchInfo.model.MatchInfo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MatchInfoJdbcRepository {

	private final JdbcTemplate jdbcTemplate;

	public void insertMatchInfoList(List<MatchInfo> matchInfoList) {
		jdbcTemplate.batchUpdate(
			"insert into match_info (match_info_id) values (?)",
			new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					MatchInfo matchInfo = matchInfoList.get(i);
					ps.setString(1, matchInfo.getMatchInfoId());
				}

				@Override
				public int getBatchSize() {
					return matchInfoList.size();
				}
			});
	}
}
