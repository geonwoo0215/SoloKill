package com.geonwoo.solokill.domain.summoner.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.geonwoo.solokill.EnableQueryLog;
import com.geonwoo.solokill.domain.summoner.model.Summoner;

@DataJpaTest
@EnableQueryLog
@AutoConfigureTestDatabase(replace = NONE)
class SummonerRepositoryTest {

	@Autowired
	private SummonerRepository summonerRepository;

	@Test
	@DisplayName("소환사 이름으로 소환사 정보를 조회한다.")
	@Sql(scripts = {"/sql/dummy.sql"})
	void findByName() {

		//given
		String name = "리거누";

		//when
		Optional<Summoner> optionalSummoner = summonerRepository.findByName(name);

		//then
		assertTrue(optionalSummoner.isPresent());
		Summoner summoner = optionalSummoner.get();
		assertEquals(name, summoner.getName());

	}
}