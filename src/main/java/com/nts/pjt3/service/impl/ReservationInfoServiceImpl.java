package com.nts.pjt3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.dao.ReservationInfoDao;
import com.nts.pjt3.dto.ReservationInfo;
import com.nts.pjt3.service.ReservationInfoService;

@Service
public class ReservationInfoServiceImpl implements ReservationInfoService {

	@Autowired
	private ReservationInfoDao reservationInfoDao;
	
	@Override
	public List<ReservationInfo> getAllByReservationEmail(String reservationEmail) {
		return reservationInfoDao.getAllByReservationEmail(reservationEmail);
	}

}
