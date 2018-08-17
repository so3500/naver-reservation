package com.nts.pjt3.dao;

import java.util.List;

import com.nts.pjt3.dto.ReservationUserCommentImage;

public interface ReservationUserCommentImageDao {
	public List<ReservationUserCommentImage> getAllByReservationUserCommentId(int reservationUserCommentId);
}
