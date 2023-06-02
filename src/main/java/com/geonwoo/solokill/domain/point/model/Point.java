package com.geonwoo.solokill.domain.point.model;

import java.time.LocalDateTime;

import com.geonwoo.solokill.domain.member.model.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Point {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	private Long value;

	private LocalDateTime createAt;

	public Point(Member member, Long value) {
		this.member = member;
		this.value = value;
		this.createAt = LocalDateTime.now();
	}
}
