package com.geonwoo.solokill.domain.summoner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.geonwoo.solokill.domain.summoner.model.Summoner;

public interface SummonerRepository extends JpaRepository<Summoner, Long> {

	Optional<Summoner> findByName(@Param("name") String name);

	Optional<Summoner> findBySummonerId(@Param("summonerId") String summonerId);
}
