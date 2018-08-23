package com.nts.pjt3.dao;

import java.util.List;

import com.nts.pjt3.dto.ReservationInfo;
import com.nts.pjt3.dto.ReservationInfoPrice;

public interface ReservationInfoDao {
	public ReservationInfo getByReservationInfoId(int reservationId);
	public List<ReservationInfo> getAllByReservationInfoEmail(String reservationEmail);
	public int createReservationInfo(ReservationInfo reservationInfo);
	public int createReservationInfoPrice(List<ReservationInfoPrice> reservationInfoPrices);
}
