package com.geonwoo.solokill.domain.matchrecord.dto;

import java.util.List;

public record MatchInfo(
	List<ParticipantResponse> participants
) {
}
