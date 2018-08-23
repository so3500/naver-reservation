package com.nts.pjt3.service;

import java.util.List;

import com.nts.pjt3.dto.ReservationInfo;

public interface ReservationInfoService {
	public List<ReservationInfo> getAllByReservationEmail(String reservationEmail);
	public int createReservationInfo(ReservationInfo reservationInfo);
	public ReservationInfo getByReservationInfoId(int reservationInfoId);
	int cancelByReservationInfoId(int reservationInfoId);
}
