package com.geonwoo.solokill.domain.match.model;

import com.geonwoo.solokill.domain.summoner.model.Summoner;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Summoner summoner;

    private String championName;

    private Integer visionWardsBoughtInGame;

    private Integer totalMinionsKilled;

    private Integer totalDamageDealtToChampions;

    private Integer goldEarned;

    private Integer kills;

    private Integer assists;

    private Integer deaths;

    private Boolean win;
    
}
