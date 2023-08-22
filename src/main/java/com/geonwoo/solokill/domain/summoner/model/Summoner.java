package com.geonwoo.solokill.domain.summoner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Summoner {

    @Id
    private String id;

    private String name;

    private Integer level;

    @Builder
    public Summoner(String name, Integer level) {
        this.name = name;
        this.level = level;
    }
}
