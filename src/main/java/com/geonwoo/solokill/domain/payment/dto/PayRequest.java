package com.geonwoo.solokill.domain.payment.dto;

import java.math.BigDecimal;

public record PayRequest(
	BigDecimal amount
) {
}
