package com.nts.pjt3.displayinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3.displayinfo.dto.DisplayInfoImage;
import com.nts.pjt3.displayinfo.service.DisplayInfoImageService;
import com.nts.pjt3.util.ImageUtil;

@RestController
@RequestMapping("/api/displayInfoImages")
public class DisplayInfoImageRestController {

	@Autowired
	private DisplayInfoImageService displayInfoImageService;

	/**
	 * @description 상세 페이지에서 사용하는 지도 이미지 출력
	 * @param displayInfoId
	 * @return 지도 이미지(map)
	 */
	@GetMapping(value = "/{displayInfoId}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getMapImageByDisplayInfoId(@PathVariable("displayInfoId") int displayInfoId) {
		DisplayInfoImage displayInfoImage = displayInfoImageService.getByDisplayInfoId(displayInfoId);
		String filePath = displayInfoImage.getSaveFileName();

		return ImageUtil.getInstance().getImageByteArray(filePath);
	}
}
