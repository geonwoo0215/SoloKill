package com.geonwoo.solokill.global.client.feign.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.geonwoo.solokill.domain.summoner.dto.SummonerInfoResponse;

@FeignClient(name = "RiotSummonerOpenFeign", url = "${riot-api.url.kr}")
public interface RiotSummonerOpenFeign {

	@GetMapping(value = "/lol/summoner/v4/summoners/by-name/{summonerName}")
	SummonerInfoResponse getSummonerInfoByName(@PathVariable("summonerName") String summonerName);

	@GetMapping(value = "/lol/summoner/v4/summoners/by-puuid/{encryptedPUUID}")
	SummonerInfoResponse getSummonerInfoByPuuid(@PathVariable("encryptedPUUID") String puuid);

}
