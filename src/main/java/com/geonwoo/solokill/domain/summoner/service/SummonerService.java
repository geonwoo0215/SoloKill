package com.geonwoo.solokill.domain.summoner.service;

import com.geonwoo.solokill.domain.summoner.repository.SummonerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SummonerService {

    private final SummonerRepository summonerRepository;


}
