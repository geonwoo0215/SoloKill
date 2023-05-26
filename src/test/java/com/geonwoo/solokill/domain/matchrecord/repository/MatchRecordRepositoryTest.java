package com.geonwoo.solokill.domain.matchrecord.repository;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.geonwoo.solokill.EnableQueryLog;

@DataJpaTest
@EnableQueryLog
@AutoConfigureTestDatabase(replace = NONE)
class MatchRecordRepositoryTest {

	@Autowired
	private MatchRecordRepository matchRecordRepository;

	@Test
	@Sql(scripts = {"/sql/dummy.sql"})
	@DisplayName("사용자의 id로 경기에서 사용한 챔피언의 이름을 조회한다.")
	void findChampionNameByPuuid() {

		//given
		String id = "swsVftNdzs0OKJqJpbKwEtICk9_5oImBLO2Rdms-Dhzkcg";

		//when
		Set<String> championNameById = matchRecordRepository.findChampionNameBySummonerId(id);

		//then
		assertThat(championNameById).contains("Jayce");
	}
}