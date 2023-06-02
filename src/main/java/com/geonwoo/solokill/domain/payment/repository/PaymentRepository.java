package com.geonwoo.solokill.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geonwoo.solokill.domain.payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
