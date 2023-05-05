package com.geonwoo.solokill.domain.matchrecord.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.geonwoo.solokill.domain.matchrecord.dto.PlayerChampionResponse;
import com.geonwoo.solokill.domain.matchrecord.dto.PlayerMatchUpResponse;
import com.geonwoo.solokill.domain.matchrecord.service.MatchRecordService;
import com.geonwoo.solokill.global.dto.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MatchRecordController {

	private final MatchRecordService matchRecordService;

	@GetMapping("/matchRecord/{summonerName}")
	public ResponseEntity<ApiResponse<List<String>>> getSummonerInfo(
		@PathVariable("summonerName") String name) {
		List<String> responses = matchRecordService.getPlayerChampionByName(name);
		return ResponseEntity.ok(new ApiResponse<>(responses));
	}

	@GetMapping("/matchRecord/{summonerName}/{championName}")
	public ResponseEntity<ApiResponse<List<PlayerMatchUpResponse>>> getMatchUp(
		@PathVariable("summonerName") String name,
		@PathVariable("championName") String championName) {
		List<PlayerMatchUpResponse> responses = matchRecordService.getPlayerMatchUpByChampionName(name,
			championName);
		return ResponseEntity.ok(new ApiResponse<>(responses));
	}
}
