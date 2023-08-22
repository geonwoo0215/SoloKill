package com.geonwoo.solokill.global.client.feign.feignclient;

import com.geonwoo.solokill.domain.match.dto.MatchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "RiotMatchOpenFeign", url = "${riot-api.url.asia}")
public interface RiotMatchOpenFeign {

    @GetMapping(value = "/lol/match/v5/matches/by-puuid/{puuid}/ids")
    List<String> getMatchId(@PathVariable("puuid") String puuid
            , @RequestParam("type") String type, @RequestParam("count") Integer count);

    @GetMapping(value = "/lol/match/v5/matches/{matchId}")
    MatchResponse getMatchByMatchId(@PathVariable("matchId") String matchId);
}
