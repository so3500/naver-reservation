package com.nts.pjt3.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.review.dao.ReservationUserCommentImageDao;
import com.nts.pjt3.review.dto.ReservationUserCommentImage;

@Service
public class ReservationUserCommentImageService {

	@Autowired
	private ReservationUserCommentImageDao reservationUserCommentImageDao;

	public List<ReservationUserCommentImage> getAllByReservationUserCommentId(int reservationUserCommentId) {
		return reservationUserCommentImageDao.getAllByReservationUserCommentId(reservationUserCommentId);
	}
	
	public ReservationUserCommentImage getByReservationUserCommentImageId(int reservationUserCommentImageId) {
		return reservationUserCommentImageDao.getByReservationUserCommentImageId(reservationUserCommentImageId);
	}
	
	public int createCommentImage(ReservationUserCommentImage commentImage) {
		return reservationUserCommentImageDao.createCommentImage(commentImage);
	}
	

}
