package com.geonwoo.solokill.domain.point.controller;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geonwoo.solokill.domain.member.dto.response.MemberDTO;
import com.geonwoo.solokill.domain.point.dto.ChargeRequest;
import com.geonwoo.solokill.domain.point.dto.ChargeResponse;
import com.geonwoo.solokill.domain.point.service.PointService;
import com.geonwoo.solokill.global.auth.annotation.LoginMember;
import com.geonwoo.solokill.global.dto.response.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PointController {

	private final PointService pointService;

	@PostMapping(value = "/point", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ApiResponse<ChargeResponse>> charge(HttpServletRequest request, @LoginMember MemberDTO memberDTO,
		@RequestBody ChargeRequest chargeRequest) {

		ChargeResponse chargeResponse = pointService.charge(memberDTO.email(), chargeRequest.token(),
			chargeRequest.chargeAmount());

		return ResponseEntity.created(URI.create(request.getRequestURI() + "/" + chargeResponse.paymentId()))
			.body(new ApiResponse<>(chargeResponse));
	}
}
