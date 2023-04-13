package com.geonwoo.solokill.domain.playermatchrecord.repository;

import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
class PlayerMatchRecordRepositoryTest {

	private PlayerMatchRecordRepository playerMatchRecordRepository;

	@Test
	@Sql(scripts = {"/sql/Dummy.sql"})
	@DisplayName("사용자의 puuid로 경기에서 사용한 챔피언의 이름을 조회한다.")
	void findChampionNameByPuuid() {

		//given
		String puuid = "puuid";

		//when
		Set<String> championNameByPuuid = playerMatchRecordRepository.findChampionNameByPuuid(puuid);

		//then
		Assertions.assertThat(championNameByPuuid).contains("Jayce");
	}
}