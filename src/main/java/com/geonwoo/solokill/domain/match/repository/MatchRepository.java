package com.geonwoo.solokill.domain.match.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geonwoo.solokill.domain.match.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
