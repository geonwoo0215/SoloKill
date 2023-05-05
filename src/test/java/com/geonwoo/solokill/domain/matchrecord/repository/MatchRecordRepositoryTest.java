package com.geonwoo.solokill.domain.matchrecord.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
class MatchRecordRepositoryTest {

	@Autowired
	private MatchRecordRepository matchRecordRepository;

	@Test
	@Sql(scripts = {"/sql/dummy.sql"})
	@DisplayName("사용자의 puuid로 경기에서 사용한 챔피언의 이름을 조회한다.")
	void findChampionNameByPuuid() {

		//given
		String puuid = "puuid";

		//when
		Set<String> championNameByPuuid = matchRecordRepository.findChampionNameByPuuid(puuid);

		//then
		assertThat(championNameByPuuid).contains("Jayce");
	}
}