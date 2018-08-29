package com.nts.pjt3.reservation.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.pjt3.reservation.dao.ReservationInfoDao;
import com.nts.pjt3.reservation.dto.ReservationInfo;
import com.nts.pjt3.reservation.dto.ReservationInfoPrice;

@Service
public class ReservationInfoService {

	@Autowired
	private ReservationInfoDao reservationInfoDao;
	
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.dd");
	
	public List<ReservationInfo> getAllByReservationEmail(String reservationEmail) {
		return reservationInfoDao.getAllByReservationInfoEmail(reservationEmail);
	}

	@Transactional
	public int createReservationInfo(ReservationInfo reservationInfo) {
		reservationInfoDao.createReservationInfo(reservationInfo);
		
		int reservationInfoId = reservationInfo.getId();
		for (ReservationInfoPrice reservationInfoPrice : reservationInfo.getReservationPrices()) {
			reservationInfoPrice.setReservationInfoId(reservationInfoId);
		}
		reservationInfoDao.createReservationInfoPrice(reservationInfo.getReservationPrices());
		
		return reservationInfoId;
	}

	public ReservationInfo getByReservationInfoId(int reservationInfoId) {
		return reservationInfoDao.getByReservationInfoId(reservationInfoId);
	}
	
	@Transactional
	public int cancelByReservationInfoId(int reservationInfoId) {
		return reservationInfoDao.cancelByReservationInfoId(reservationInfoId);
	}
	
	public Map<String, Object> getClassifiedReservInfos(List<ReservationInfo> reservInfos) {
		HashMap<String, Object> classifiedReservInfoMap = new HashMap<>();
		
		List<ReservationInfo> confirmedReservInfos = new LinkedList<>();
		List<ReservationInfo> usedReservInfos = new LinkedList<>();
		List<ReservationInfo> canceldReservInfos = new LinkedList<>();
		final int CANCEL = 1;
		LocalDate nowDate = LocalDate.now();

		for (ReservationInfo reservInfo : reservInfos) {
			if (reservInfo.getCancelFlag() == CANCEL) {
				canceldReservInfos.add(reservInfo);
				continue;
			}

			LocalDate reserveDate = reservInfo.getReservationDate();
			if (reserveDate.isBefore(nowDate)) {
				usedReservInfos.add(reservInfo);
			} else {
				confirmedReservInfos.add(reservInfo);
			}
		}

		classifiedReservInfoMap.put("confirmedReservInfos", confirmedReservInfos);
		classifiedReservInfoMap.put("usedReservInfos", usedReservInfos);
		classifiedReservInfoMap.put("canceldReservInfos", canceldReservInfos);
		
		return classifiedReservInfoMap;
	}
	
	public String getReservDate() {
		Random random = new Random();
		String localDate = dateTimeFormatter.format(LocalDate.now().plusDays(random.nextInt(4) + 1));
		return localDate;
	}
	
	
}
