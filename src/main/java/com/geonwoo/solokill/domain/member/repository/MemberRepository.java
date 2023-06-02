package com.geonwoo.solokill.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.geonwoo.solokill.domain.member.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	boolean existsByEmail(@Param("loginEmail") String loginEmail);

	boolean existsByNickname(@Param("nickname") String nickname);

	Optional<Member> findByEmail(String email);

}
