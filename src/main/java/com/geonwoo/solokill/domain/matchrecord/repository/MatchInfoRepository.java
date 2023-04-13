package com.geonwoo.solokill.domain.matchrecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geonwoo.solokill.domain.matchrecord.model.MatchInfo;

public interface MatchInfoRepository extends JpaRepository<MatchInfo, String> {
}
