package com.nts.pjt3.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.nts.pjt3.dto.ProductImage;
import com.nts.pjt3.service.ProductImageService;

@RestController
@RequestMapping(path = "/api/productImages")
public class ProductImageApiController {

	@Autowired
	private ProductImageService productImageService;

	/**
	 * url을 통해 이미지를 가져오기 어려울 때 사용
	 * 사용처: 메인페이지의 썸네일이미지(th)
	 */
	@GetMapping("/{displayInfoId}")
	public RedirectView getProductImageByDisplayInfoIdAndType(@PathVariable("displayInfoId") int displayInfoId,
		@RequestParam("type") String type, HttpServletRequest request) {
		ProductImage productImage = productImageService.getByDisplayInfoIdAndType(displayInfoId, type);
		String filePath = productImage.getSaveFileName();
		String filePathURL = request.getContextPath() + "/" + filePath;

		return new RedirectView(filePathURL);
	}

	@GetMapping("/{productId}/{productImageId}")
	public RedirectView getProductImageByProductIdAndProductImageId(
		@PathVariable(name = "productId") int productId, @PathVariable(name = "productImageId") int productImageId,
		HttpServletRequest request) {
		ProductImage productImage = productImageService.getByProductIdAndProductImageId(productId, productImageId);
		String filePath = productImage.getSaveFileName();
		String filePathURL = request.getContextPath() + "/" + filePath;
		
		return new RedirectView(filePathURL);
	}

}
