package com.nts.pjt3.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nts.pjt3.dto.ReservationUserComment;

public interface ReservationUserCommentService {
	List<ReservationUserComment> getAllByProductId(int productId);
	double getAvgScoreFromComments(List<ReservationUserComment> comments);
	ReservationUserComment createComment(ReservationUserComment review, MultipartFile reviewImage);
}
