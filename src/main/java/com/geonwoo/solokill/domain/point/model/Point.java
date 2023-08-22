package com.geonwoo.solokill.domain.point.model;

import com.geonwoo.solokill.domain.member.model.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long value;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Point(Long value) {
        this.value = value;
    }
}
