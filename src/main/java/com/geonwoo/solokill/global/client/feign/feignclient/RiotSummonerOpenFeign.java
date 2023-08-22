package com.geonwoo.solokill.global.client.feign.feignclient;

import com.geonwoo.solokill.domain.summoner.dto.SummonerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RiotSummonerOpenFeign", url = "${riot-api.url.kr}")
public interface RiotSummonerOpenFeign {

    @GetMapping(value = "/lol/summoner/v4/summoners/by-name/{summonerName}")
    SummonerResponse getSummonerInfoByName(@PathVariable("summonerName") String summonerName);

}
