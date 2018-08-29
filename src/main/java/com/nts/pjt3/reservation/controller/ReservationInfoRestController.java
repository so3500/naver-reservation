package com.nts.pjt3.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3.reservation.dto.ReservationInfo;
import com.nts.pjt3.reservation.service.ReservationInfoService;

@RestController
@RequestMapping(path = "/api/reservationInfos")
public class ReservationInfoRestController {

	@Autowired
	private ReservationInfoService reservationInfoService;

	@GetMapping
	public Map<String, Object> getReservationInfos(
		@RequestParam("reservationEmail") String reservationEmail) {

		List<ReservationInfo> reservInfos = reservationInfoService.getAllByReservationEmail(reservationEmail);

		Map<String, Object> map = new HashMap<>();
		map.put("reservationInfos", reservInfos);
		map.put("size", reservInfos.size());

		return map;
	}

	@PostMapping
	public ReservationInfo addReservationInfo(@RequestBody ReservationInfo reservationInfo, HttpSession session) {
		reservationInfoService.createReservationInfo(reservationInfo);
		session.setAttribute("loginEmail", reservationInfo.getReservationEmail());
		return reservationInfo;
	}
	
	@PutMapping
	public int cancelReservationInfo(@RequestParam("reservationId") int reservationInfoId) {
		return reservationInfoService.cancelByReservationInfoId(reservationInfoId);
	}
	
}
