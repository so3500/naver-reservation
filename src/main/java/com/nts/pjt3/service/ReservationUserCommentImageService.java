package com.nts.pjt3.service;

import java.util.List;

import com.nts.pjt3.dto.ReservationUserCommentImage;

public interface ReservationUserCommentImageService {
	List<ReservationUserCommentImage> getAllByReservationUserCommentId(int reservationUserCommentId);
	int createCommentImage(ReservationUserCommentImage commentImage);
}
