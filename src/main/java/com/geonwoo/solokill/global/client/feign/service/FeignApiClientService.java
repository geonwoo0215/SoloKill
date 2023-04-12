package com.geonwoo.solokill.global.client.feign.service;

import org.springframework.stereotype.Service;

import com.geonwoo.solokill.domain.summoner.converter.SummonerConverter;
import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;
import com.geonwoo.solokill.domain.summoner.model.Summoner;
import com.geonwoo.solokill.domain.summoner.repository.SummonerRepository;
import com.geonwoo.solokill.global.client.feign.feignclient.RiotSummonerOpenFeign;
import com.geonwoo.solokill.global.client.service.ApiClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeignApiClientService implements ApiClientService {

	private final RiotSummonerOpenFeign riotSummonerOpenFeign;
	private final SummonerRepository summonerRepository;

	@Override
	public SummonerInfoResponse getSummonerInfoByName(String name) {
		SummonerInfoResponse summonerInfoResponse = riotSummonerOpenFeign.getSummonerInfoByName(name);
		Summoner summoner = SummonerConverter.toSummoner(summonerInfoResponse);
		summonerRepository.save(summoner);
		return summonerInfoResponse;
	}
}
