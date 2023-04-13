package com.geonwoo.solokill.domain.playermatchrecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geonwoo.solokill.domain.playermatchrecord.model.MatchInfo;

public interface MatchInfoRepository extends JpaRepository<MatchInfo, String> {
}
