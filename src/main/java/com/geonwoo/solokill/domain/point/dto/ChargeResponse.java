package com.geonwoo.solokill.domain.point.dto;

public record ChargeResponse(
	Long paymentId,
	Long chargeAmount,
	Long currentPoint
) {
}
