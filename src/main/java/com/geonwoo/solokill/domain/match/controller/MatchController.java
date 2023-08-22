package com.geonwoo.solokill.domain.match.controller;

import com.geonwoo.solokill.domain.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

}
