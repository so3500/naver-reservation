package com.nts.pjt3.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationInfo {
	private int id;
	private int productId;
	private int sumPrice;
	private int cancelFlag;
	private String productDescription;
	private String productContent;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime reservationDate;

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime createDate;

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime modifyDate;

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

	public int getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

	public int getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTel() {
		return reservationTel;
	}

	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
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

	@Override
	public String toString() {
		return String.format(
			"ReservationInfo [id=%s, productId=%s, sumPrice=%s, cancelFlag=%s, productDescription=%s, productContent=%s, reservationName=%s, reservationTel=%s, reservationEmail=%s, reservationDate=%s, createDate=%s, modifyDate=%s]",
			id, productId, sumPrice, cancelFlag, productDescription, productContent, reservationName, reservationTel,
			reservationEmail, reservationDate, createDate, modifyDate);
	}

}
