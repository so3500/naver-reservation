package com.nts.pjt3.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3.dto.ReservationInfo;
import com.nts.pjt3.service.ReservationInfoService;

@RestController
@RequestMapping(path = "/api/reservationInfos")
public class ReservationInfoApiController {

	@Autowired
	private ReservationInfoService reservationInfoService;

	@GetMapping
	public Map<String, Object> getReservationInfos(
		@RequestParam(name = "reservationEmail", required = true) String reservationEmail) {

		List<ReservationInfo> reservInfos = reservationInfoService.getAllByReservationEmail(reservationEmail);

		Map<String, Object> map = new HashMap<>();
		map.put("reservationInfos", reservInfos);
		map.put("size", reservInfos.size());

		return map;
	}
}
