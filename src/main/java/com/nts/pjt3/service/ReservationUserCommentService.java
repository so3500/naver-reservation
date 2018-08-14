package com.nts.pjt3.service;

import java.util.List;

import com.nts.pjt3.dto.ReservationUserComment;

public interface ReservationUserCommentService {
	List<ReservationUserComment> getReservationUserCommentsByProductId(int productId);
	double getAvgScoreFromReservationUserComments(List<ReservationUserComment> comments);
}
