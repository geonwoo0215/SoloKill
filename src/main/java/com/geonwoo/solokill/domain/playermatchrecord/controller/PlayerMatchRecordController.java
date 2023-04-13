package com.geonwoo.solokill.domain.playermatchrecord.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.geonwoo.solokill.domain.playermatchrecord.dto.PlayerChampionResponse;
import com.geonwoo.solokill.domain.playermatchrecord.service.PlayerMatchRecordService;
import com.geonwoo.solokill.global.dto.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlayerMatchRecordController {

	private final PlayerMatchRecordService playerMatchRecordService;

	@GetMapping("/playerMatchRecord/{summonerName}")
	public ResponseEntity<ApiResponse<List<PlayerChampionResponse>>> getSummonerInfo(
		@PathVariable("summonerName") String name) {
		List<PlayerChampionResponse> responses = playerMatchRecordService.getPlayerChampionByName(name);
		return ResponseEntity.ok(new ApiResponse<>(responses));
	}
}
