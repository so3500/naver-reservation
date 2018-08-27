package com.nts.pjt3.dao;

import java.util.List;

import com.nts.pjt3.dto.ReservationUserComment;

public interface ReservationUserCommentDao {
	public List<ReservationUserComment> getAllByProductId(int productId);
	public int createComment(ReservationUserComment comment);
}
