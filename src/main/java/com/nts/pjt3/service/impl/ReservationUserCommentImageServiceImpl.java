package com.nts.pjt3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.dao.ReservationUserCommentImageDao;
import com.nts.pjt3.dto.ReservationUserCommentImage;
import com.nts.pjt3.service.ReservationUserCommentImageService;

@Service
public class ReservationUserCommentImageServiceImpl implements ReservationUserCommentImageService {

	@Autowired
	private ReservationUserCommentImageDao reservationUserCommentImageDao;

	@Override
	public List<ReservationUserCommentImage> getAllByReservationUserCommentId(int reservationUserCommentId) {
		return reservationUserCommentImageDao.getAllByReservationUserCommentId(reservationUserCommentId);
	}

}
