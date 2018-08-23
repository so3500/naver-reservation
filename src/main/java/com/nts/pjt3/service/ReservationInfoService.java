package com.nts.pjt3.service;

import java.util.List;

import com.nts.pjt3.dto.ReservationInfo;

public interface ReservationInfoService {
	public List<ReservationInfo> getAllByReservationEmail(String reservationEmail);
	int createReservationInfo(ReservationInfo reservationInfo);
	ReservationInfo getByReservationInfoId(int reservationInfoId);
}
