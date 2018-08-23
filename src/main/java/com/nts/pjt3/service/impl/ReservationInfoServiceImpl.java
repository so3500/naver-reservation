package com.nts.pjt3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.pjt3.dao.ReservationInfoDao;
import com.nts.pjt3.dto.ReservationInfo;
import com.nts.pjt3.dto.ReservationInfoPrice;
import com.nts.pjt3.service.ReservationInfoService;

@Service
public class ReservationInfoServiceImpl implements ReservationInfoService {

	@Autowired
	private ReservationInfoDao reservationInfoDao;
	
	@Override
	public List<ReservationInfo> getAllByReservationEmail(String reservationEmail) {
		return reservationInfoDao.getAllByReservationInfoEmail(reservationEmail);
	}

	@Transactional
	@Override
	public int createReservationInfo(ReservationInfo reservationInfo) {
		reservationInfoDao.createReservationInfo(reservationInfo);
		
		int reservationInfoId = reservationInfo.getId();
		for (ReservationInfoPrice reservationInfoPrice : reservationInfo.getReservationPrices()) {
			reservationInfoPrice.setReservationInfoId(reservationInfoId);
		}
		reservationInfoDao.createReservationInfoPrice(reservationInfo.getReservationPrices());
		
		return reservationInfoId;
	}

	@Override
	public ReservationInfo getByReservationInfoId(int reservationInfoId) {
		return reservationInfoDao.getByReservationInfoId(reservationInfoId);
	}
	
	@Transactional
	@Override
	public int cancelByReservationInfoId(int reservationInfoId) {
		return reservationInfoDao.cancelByReservationInfoId(reservationInfoId);
	}
}
