package com.geonwoo.solokill.global.client.service;

import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;

public interface ApiClientService {

	SummonerInfoResponse getSummonerInfoByName(String name);

	void getMatchInfoByPuuid(String puuid);
}
