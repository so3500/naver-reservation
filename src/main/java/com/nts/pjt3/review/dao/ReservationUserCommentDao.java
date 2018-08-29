package com.nts.pjt3.review.dao;

import java.util.List;

import com.nts.pjt3.review.dto.ReservationUserComment;

public interface ReservationUserCommentDao {
	public List<ReservationUserComment> getAllByProductId(int productId);
	public int createComment(ReservationUserComment comment);
}
