package com.nts.pjt3.product.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3.displayinfo.dto.DisplayInfoImage;
import com.nts.pjt3.displayinfo.service.DisplayInfoImageService;
import com.nts.pjt3.product.dto.Product;
import com.nts.pjt3.product.dto.ProductImage;
import com.nts.pjt3.product.dto.ProductPrice;
import com.nts.pjt3.product.service.ProductImageService;
import com.nts.pjt3.product.service.ProductPriceService;
import com.nts.pjt3.product.service.ProductService;
import com.nts.pjt3.review.dto.ReservationUserComment;
import com.nts.pjt3.review.service.ReservationUserCommentService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImageService productImageService;

	@Autowired
	private DisplayInfoImageService displayInfoImageService;

	@Autowired
	private ProductPriceService productPriceService;

	@Autowired
	private ReservationUserCommentService reservationUserCommentService;

	private final int ALL_CATEGORY = 0;

	@GetMapping
	public Map<String, Object> getProducts(
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId) {
		List<Product> products = Collections.emptyList();

		int productsCount = getProductsCountByCategoryId(categoryId);
		if (productsCount > 0) {
			products = getProductsByCategoryId(start, categoryId);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", productsCount);
		map.put("productsCount", products.size());
		map.put("products", products);

		return map;
	}

	@GetMapping("/{displayInfoId}")
	public Map<String, Object> getProduct(
		@PathVariable("displayInfoId") int displayInfoId) {

		// TODO: product EmptyResultDataAccessException 예외처리
		Product product = productService.getByDisplayInfoId(displayInfoId);
		List<ProductImage> productImages = getProductImagesByDisplayInfoId(displayInfoId);
		List<DisplayInfoImage> displayInfoImages = displayInfoImageService.getAllByDisplayInfoId(displayInfoId);
		List<ProductPrice> productPrices = productPriceService.getAllByDisplayInfoId(displayInfoId);
		List<ReservationUserComment> comments = reservationUserCommentService.getAllByProductId(product.getId());
		double avgScore = reservationUserCommentService.getAvgScoreFromComments(comments);

		Map<String, Object> map = new HashMap<>();
		map.put("product", product);
		map.put("productImages", productImages);
		map.put("avgScore", avgScore);
		map.put("comments", comments);
		map.put("displayInfoImages", displayInfoImages);
		map.put("productPrices", productPrices);
		map.put("displayInfoId", displayInfoId);

		return map;
	}

	private List<Product> getProductsByCategoryId(int start, int categoryId) {
		if (categoryId != ALL_CATEGORY) {
			return productService.getAllByCategoryId(start, categoryId);
		} else {
			return productService.getAll(start);
		}
	}

	private int getProductsCountByCategoryId(int categoryId) {
		if (categoryId != ALL_CATEGORY) {
			return productService.getAllCountByCategoryId(categoryId);
		} else {
			return productService.getAllCount();
		}
	}

	private List<ProductImage> getProductImagesByDisplayInfoId(int displayInfoId) {
		List<ProductImage> productImages = new LinkedList<>();
		ProductImage productMainImage = productImageService.getByDisplayInfoIdAndType(displayInfoId, "ma");
		List<ProductImage> productThumImages = productImageService.getAllByDisplayInfoIdAndType(displayInfoId,
			"et");
		productImages.add(productMainImage);
		productImages.addAll(productThumImages);

		return productImages;
	}

}
