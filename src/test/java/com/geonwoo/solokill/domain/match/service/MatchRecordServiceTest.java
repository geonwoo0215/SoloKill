package com.geonwoo.solokill.domain.match.service;

import com.geonwoo.solokill.domain.match.repository.MatchRepository;
import com.geonwoo.solokill.domain.summoner.service.SummonerService;
import com.geonwoo.solokill.global.client.service.ApiClientService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MatchRecordServiceTest {

    @Mock
    private SummonerService summonerService;

    @Mock
    private ApiClientService apiClientService;

    @Mock
    private MatchRepository matchRecordRepository;

    @InjectMocks
    private MatchService matchRecordService;

}