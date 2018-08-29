package com.nts.pjt3.review.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationUserComment {
	private int id;
	private int productId;
	private int reservationInfoId;
	private int score;
	private String comment;
	private String reservationEmail;
	
	@JsonFormat(pattern = "yyyy.MM.dd")
	private LocalDateTime reservationDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime  createDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime  modifyDate;
	
	private List<ReservationUserCommentImage> reservationUserCommentImages;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationName) {
		this.reservationEmail = reservationName;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<ReservationUserCommentImage> getReservationUserCommentImages() {
		return reservationUserCommentImages;
	}

	public void setReservationUserCommentImages(List<ReservationUserCommentImage> reservationUserCommentImages) {
		this.reservationUserCommentImages = reservationUserCommentImages;
	}

	@Override
	public String toString() {
		return String.format(
			"ReservationUserComment [id=%s, productId=%s, reservationInfoId=%s, score=%s, comment=%s, reservationEmail=%s, reservationDate=%s, createDate=%s, modifyDate=%s, reservationUserCommentImages=%s]",
			id, productId, reservationInfoId, score, comment, reservationEmail, reservationDate, createDate, modifyDate,
			reservationUserCommentImages);
	}

}
