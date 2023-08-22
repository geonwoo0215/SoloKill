package com.geonwoo.solokill.global.client.feign.service;

import com.geonwoo.solokill.domain.match.repository.MatchRepository;
import com.geonwoo.solokill.domain.summoner.repository.SummonerRepository;
import com.geonwoo.solokill.global.client.feign.feignclient.RiotMatchOpenFeign;
import com.geonwoo.solokill.global.client.feign.feignclient.RiotSummonerOpenFeign;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FeignApiClientServiceTest {

    @InjectMocks
    private FeignApiClientService feignApiClientService;

    @Mock
    private RiotSummonerOpenFeign riotSummonerOpenFeign;

    @Mock
    private RiotMatchOpenFeign riotMatchOpenFeign;

    @Mock
    private SummonerRepository summonerRepository;

    @Mock
    private MatchRepository matchInfoRepository;

    @Mock
    private MatchRepository matchRecordRepository;

}