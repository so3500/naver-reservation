package com.nts.pjt3.dao;

import java.util.List;

import com.nts.pjt3.dto.ReservationInfo;

public interface ReservationInfoDao {
	public List<ReservationInfo> getAllByReservationEmail(String reservationEmail);
}
