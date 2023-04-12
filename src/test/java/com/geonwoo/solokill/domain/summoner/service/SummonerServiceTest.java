package com.geonwoo.solokill.domain.summoner.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;
import com.geonwoo.solokill.domain.summoner.model.Summoner;
import com.geonwoo.solokill.domain.summoner.repository.SummonerRepository;
import com.geonwoo.solokill.global.client.service.ApiClientService;

@ExtendWith(MockitoExtension.class)
class SummonerServiceTest {

	@Mock
	private ApiClientService apiClientService;

	@Mock
	private SummonerRepository summonerRepository;

	@InjectMocks
	private SummonerService summonerService;

	@Test
	@DisplayName("DB에서 소환사의 닉네임으로 소환사 정보를 가져오는데 성공한다.")
	@Sql(scripts = {"/sql/summoner_dummy.sql"})
	void getSummonerInfoByName_DB() {

		//given
		String name = "리거누";
		Summoner summoner = Summoner.builder()
			.id("id")
			.accountId("accountId")
			.puuid("puuid")
			.name("리거누")
			.profileIconId(123)
			.revisionDate(123456)
			.summonerLevel(344)
			.build();

		Optional<Summoner> optionalSummoner = Optional.of(summoner);
		when(summonerRepository.findByName(name)).thenReturn(optionalSummoner);

		//when
		SummonerInfoResponse summonerInfoByName = summonerService.getSummonerInfoByName(name);

		//then
		assertNotNull(summonerInfoByName);
		assertThat(summonerInfoByName)
			.hasFieldOrPropertyWithValue("id", summoner.getId())
			.hasFieldOrPropertyWithValue("accountId", summoner.getAccountId())
			.hasFieldOrPropertyWithValue("puuid", summoner.getPuuid())
			.hasFieldOrPropertyWithValue("profileIconId", summoner.getProfileIconId())
			.hasFieldOrPropertyWithValue("revisionDate", summoner.getRevisionDate())
			.hasFieldOrPropertyWithValue("summonerLevel", summoner.getSummonerLevel());

		verify(summonerRepository).findByName(name);
	}

	@Test
	@DisplayName("소환사 정보가 DB에 없으면 api를 호출하여 정보를 가져오는데 성공한다.")
	void getSummonerInfoByName_Client() {

		String name = "리거누";

		SummonerInfoResponse summonerInfoResponse = SummonerInfoResponse.builder()
			.id("id")
			.accountId("accountId")
			.puuid("puuid")
			.name("리거누")
			.profileIconId(123)
			.revisionDate(123456)
			.summonerLevel(344)
			.build();

		Optional<Summoner> optionalSummoner = Optional.empty();
		when(summonerRepository.findByName(name)).thenReturn(optionalSummoner);
		when(apiClientService.getSummonerInfoByName(name)).thenReturn(summonerInfoResponse);

		SummonerInfoResponse summonerInfoByName = summonerService.getSummonerInfoByName(name);
		assertNotNull(summonerInfoByName);

		assertThat(summonerInfoByName)
			.hasFieldOrPropertyWithValue("id", summonerInfoByName.id())
			.hasFieldOrPropertyWithValue("accountId", summonerInfoByName.accountId())
			.hasFieldOrPropertyWithValue("puuid", summonerInfoByName.puuid())
			.hasFieldOrPropertyWithValue("profileIconId", summonerInfoByName.profileIconId())
			.hasFieldOrPropertyWithValue("revisionDate", summonerInfoByName.revisionDate())
			.hasFieldOrPropertyWithValue("summonerLevel", summonerInfoByName.summonerLevel());

		verify(summonerRepository).findByName(name);
		verify(apiClientService).getSummonerInfoByName(name);

	}
}