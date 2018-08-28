package com.nts.pjt3.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationInfo {
	private int id;
	private int productId;
	private int displayInfoId;
	private int sumPrice;
	private int cancelFlag;
	private String productDescription;
	private String productContent;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;

	@JsonFormat(pattern = "yyyy.M.dd")
	private LocalDate reservationDate;

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime createDate;

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime modifyDate;

	private List<ReservationInfoPrice> reservationPrices;

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

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
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

	public List<ReservationInfoPrice> getReservationPrices() {
		return reservationPrices;
	}

	public void setReservationPrices(List<ReservationInfoPrice> reservationPrices) {
		this.reservationPrices = reservationPrices;
	}
	

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	@Override
	public String toString() {
		return String.format(
			"ReservationInfo [id=%s, productId=%s, displayInfoId=%s, sumPrice=%s, cancelFlag=%s, productDescription=%s, productContent=%s, reservationName=%s, reservationTel=%s, reservationEmail=%s, reservationDate=%s, createDate=%s, modifyDate=%s, reservationPrices=%s]",
			id, productId, displayInfoId, sumPrice, cancelFlag, productDescription, productContent, reservationName,
			reservationTel, reservationEmail, reservationDate, createDate, modifyDate, reservationPrices);
	}

}
