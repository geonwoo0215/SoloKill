package com.geonwoo.solokill.domain.payment.dto;

import java.time.LocalDateTime;

public record PayResponse(
	Long id,
	Long amount,
	LocalDateTime createAt
) {
}
