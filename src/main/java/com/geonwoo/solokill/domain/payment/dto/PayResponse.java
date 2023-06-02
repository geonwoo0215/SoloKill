package com.geonwoo.solokill.domain.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PayResponse(
	BigDecimal amount,
	LocalDateTime createAt
) {
}
