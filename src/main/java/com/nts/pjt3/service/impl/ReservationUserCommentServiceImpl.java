package com.nts.pjt3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.dao.ReservationUserCommentDao;
import com.nts.pjt3.dao.ReservationUserCommentImageDao;
import com.nts.pjt3.dto.ReservationUserComment;
import com.nts.pjt3.service.ReservationUserCommentService;

@Service
public class ReservationUserCommentServiceImpl implements ReservationUserCommentService {

	@Autowired
	private ReservationUserCommentDao reservationUserCommentDao;

	@Autowired
	private ReservationUserCommentImageDao reservationUserCommentImageDao;

	@Override
	public List<ReservationUserComment> getAllByProductId(int productId) {
		List<ReservationUserComment> comments = reservationUserCommentDao.getAllByProductId(productId);
		for (ReservationUserComment comment : comments) {
			comment.setReservationUserCommentImages(
				reservationUserCommentImageDao.getAllByReservationUserCommentId(comment.getId()));

			String modifiredReservationEmail = getModifidReservationEmail(comment.getReservationEmail());
			comment.setReservationEmail(modifiredReservationEmail);
		}
		return comments;
	}

	@Override
	public double getAvgScoreFromComments(List<ReservationUserComment> comments) {
		double sumScore = 0;
		for (ReservationUserComment comment : comments) {
			sumScore += comment.getScore();
		}
		double avgScore = Math.round(sumScore / comments.size() * 10d) / 10d;
		return avgScore;
	}

	private String getModifidReservationEmail(String reservationEmail) {
		return reservationEmail.substring(0, 4).concat("****");
	}
}
