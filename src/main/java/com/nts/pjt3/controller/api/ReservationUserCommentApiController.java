package com.nts.pjt3.controller.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nts.pjt3.dto.ReservationUserComment;
import com.nts.pjt3.service.ReservationUserCommentService;

@RestController
@RequestMapping(path = "/api/reservationUserComments")
public class ReservationUserCommentApiController {

	@Autowired
	private ReservationUserCommentService reservationUserCommentService;

	@GetMapping("/{productId}")
	public Map<String, Object> getReservationUserComments(@PathVariable("productId") int productId) {
		List<ReservationUserComment> comments = reservationUserCommentService.getAllByProductId(productId);
		double avgScore = reservationUserCommentService.getAvgScoreFromComments(comments);

		Map<String, Object> map = new HashMap<>();
		map.put("comments", comments);
		map.put("avgScore", avgScore);

		return map;
	}
	
	@PostMapping
	public Map<String, Object> createComment(
			@RequestParam(value = "reviewImage", required = false) MultipartFile reviewImage,
			@RequestParam("productId") int productId,
			@RequestParam("reservationInfoId") int reservationInfoId,
			@RequestParam("score") int score,
			@RequestParam("comment") String comment) {
		ReservationUserComment review = new ReservationUserComment();
		review.setProductId(productId);
		review.setReservationInfoId(reservationInfoId);
		review.setScore(score);
		review.setComment(comment);
		
		ReservationUserComment reservationUserComment = reservationUserCommentService.createComment(review, reviewImage);
		return Collections.singletonMap("reservationUserComment", reservationUserComment);
	}

}
