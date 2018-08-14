package com.nts.pjt3.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3.dto.ReservationUserComment;
import com.nts.pjt3.service.ReservationUserCommentService;

@RestController
@RequestMapping(path = "/api/reservationUserComments")
public class ReservationUserCommentServiceApiController {

	@Autowired
	ReservationUserCommentService reservationUserCommentService;

	@GetMapping("/{productId}")
	public Map<String, Object> getReservationUserComments(@PathVariable(name = "productId") int productId) {
		List<ReservationUserComment> comments = reservationUserCommentService
			.getReservationUserCommentsByProductId(productId);
		double avgScore = reservationUserCommentService.getAvgScoreFromReservationUserComments(comments);

		Map<String, Object> map = new HashMap<>();
		map.put("comments", comments);
		map.put("avgScore", avgScore);

		return map;
	}
}
