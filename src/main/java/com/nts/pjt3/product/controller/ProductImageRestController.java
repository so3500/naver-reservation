package com.nts.pjt3.product.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3.product.dto.ProductImage;
import com.nts.pjt3.product.service.ProductImageService;
import com.nts.pjt3.util.ImageUtil;

@RestController
@RequestMapping(path = "/api/productImages")
public class ProductImageRestController {

	@Autowired
	private ProductImageService productImageService;

	/**
	 * url을 통해 이미지를 가져오기 어려울 때 사용
	 * 사용처: 메인페이지의 썸네일이미지(th)
	 */
	@GetMapping(value = "/{displayInfoId}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getProductImageByDisplayInfoIdAndType(@PathVariable("displayInfoId") int displayInfoId,
		@RequestParam("type") String type, HttpServletRequest request) {
		ProductImage productImage = productImageService.getByDisplayInfoIdAndType(displayInfoId, type);
		String filePath = productImage.getSaveFileName();

		return ImageUtil.getInstance().getImageByteArray(filePath);
	}

	@GetMapping(value = "/{productId}/{productImageId}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getProductImageByProductIdAndProductImageId(
		@PathVariable(name = "productId") int productId, @PathVariable(name = "productImageId") int productImageId,
		HttpServletRequest request) {
		ProductImage productImage = productImageService.getByProductIdAndProductImageId(productId, productImageId);
		String filePath = productImage.getSaveFileName();

		return ImageUtil.getInstance().getImageByteArray(filePath);
	}

}
