package com.geonwoo.solokill.domain.summoner.service;

import com.geonwoo.solokill.domain.summoner.repository.SummonerRepository;
import com.geonwoo.solokill.global.client.service.ApiClientService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SummonerServiceTest {

    @Mock
    private ApiClientService apiClientService;

    @Mock
    private SummonerRepository summonerRepository;

    @InjectMocks
    private SummonerService summonerService;


}