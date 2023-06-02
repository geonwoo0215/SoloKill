package com.geonwoo.solokill.domain.payment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.geonwoo.solokill.domain.member.model.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Member member;

	private BigDecimal value;

	private LocalDateTime createAt;

	public Payment(Member member, BigDecimal value) {
		this.member = member;
		this.value = value;
	}
}
