package com.geonwoo.solokill.domain.playermatchrecord.dto;

import java.util.List;

public record MatchInfo(
	List<ParticipantResponse> participants
) {
}
