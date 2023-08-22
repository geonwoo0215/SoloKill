package com.geonwoo.solokill.global.client.service;

import com.geonwoo.solokill.domain.summoner.dto.SummonerResponse;

public interface ApiClientService {

    SummonerResponse getSummonerInfoByName(String name);

    void getMatchInfoByPuuid(String puuid);
}
