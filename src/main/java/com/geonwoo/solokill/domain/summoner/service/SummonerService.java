package com.geonwoo.solokill.domain.summoner.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.solokill.domain.summoner.converter.SummonerConverter;
import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;
import com.geonwoo.solokill.domain.summoner.model.Summoner;
import com.geonwoo.solokill.domain.summoner.repository.SummonerRepository;
import com.geonwoo.solokill.global.client.service.ApiClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SummonerService {

	private final SummonerRepository summonerRepository;
	private final ApiClientService apiClientService;

	@Transactional
	public SummonerInfoResponse getSummonerInfoByName(String name) {
		Summoner summoner = summonerRepository.findByName(name)
			.orElseGet(() -> SummonerConverter.toSummoner(apiClientService.getSummonerInfoByName(name)));
		return SummonerConverter.toSummonerInfoResponse(summoner);
	}

}
