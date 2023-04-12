package com.geonwoo.solokill.domain.match.dto;

import java.util.List;

public record MatchInfo(
	List<ParticipantResponse> participants
) {
}
