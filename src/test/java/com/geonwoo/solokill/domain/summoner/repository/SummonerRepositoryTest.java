package com.geonwoo.solokill.domain.summoner.repository;

import com.geonwoo.solokill.EnableQueryLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@EnableQueryLog
@AutoConfigureTestDatabase(replace = NONE)
class SummonerRepositoryTest {

    @Autowired
    private SummonerRepository summonerRepository;


}