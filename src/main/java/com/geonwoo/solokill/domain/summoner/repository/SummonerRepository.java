package com.geonwoo.solokill.domain.summoner.repository;

import com.geonwoo.solokill.domain.summoner.model.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SummonerRepository extends JpaRepository<Summoner, Long> {

    Optional<Summoner> findByName(@Param("name") String name);
}
