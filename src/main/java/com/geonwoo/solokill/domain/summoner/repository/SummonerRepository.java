package com.geonwoo.solokill.domain.summoner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geonwoo.solokill.domain.summoner.model.Summoner;

public interface SummonerRepository extends JpaRepository<Summoner, String> {
}
