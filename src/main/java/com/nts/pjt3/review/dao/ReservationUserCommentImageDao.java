package com.nts.pjt3.review.dao;

import java.util.List;

import com.nts.pjt3.review.dto.ReservationUserCommentImage;

public interface ReservationUserCommentImageDao {
	public List<ReservationUserCommentImage> getAllByReservationUserCommentId(int reservationUserCommentId);
	public int createCommentImage(ReservationUserCommentImage commentImage);
}
