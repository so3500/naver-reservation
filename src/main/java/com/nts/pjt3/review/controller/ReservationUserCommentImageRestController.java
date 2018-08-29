package com.nts.pjt3.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3.review.dto.ReservationUserCommentImage;
import com.nts.pjt3.review.service.ReservationUserCommentImageService;
import com.nts.pjt3.util.ImageUtil;

@RestController
@RequestMapping("/api/commentImage")
public class ReservationUserCommentImageRestController {

	@Autowired
	private ReservationUserCommentImageService reservationUserCommentImageService;

	@GetMapping(value = "/{commentImageId}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getByReservationUserCommentImageId(@PathVariable("commentImageId") int commentImageId) {
		ReservationUserCommentImage reservationUserCommentImage = reservationUserCommentImageService
			.getByReservationUserCommentImageId(commentImageId);
		String filePath = reservationUserCommentImage.getSaveFileName();

		return ImageUtil.getInstance().getImageByteArray(filePath);
	}

}
