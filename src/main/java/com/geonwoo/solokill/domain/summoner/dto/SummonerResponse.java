package com.geonwoo.solokill.domain.summoner.dto;

public record SummonerResponse(
        String id,
        String name,
        Integer level
) {

    public SummonerResponse(String id, String name, Integer level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }
}
