package com.geonwoo.solokill.domain.matchInfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.geonwoo.solokill.domain.matchInfo.model.MatchInfo;

public interface MatchInfoRepository extends JpaRepository<MatchInfo, Long> {

	boolean existsByMatchId(@Param("matchId") String matchId);

}
