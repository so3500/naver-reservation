package com.nts.pjt3.controller.api;

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

import com.nts.pjt3.dto.DisplayInfoImage;
import com.nts.pjt3.dto.Product;
import com.nts.pjt3.dto.ProductImage;
import com.nts.pjt3.dto.ProductPrice;
import com.nts.pjt3.service.DisplayInfoImageService;
import com.nts.pjt3.service.ProductImageService;
import com.nts.pjt3.service.ProductPriceService;
import com.nts.pjt3.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImageService productImageService;

	@Autowired
	private DisplayInfoImageService displayInfoImageService;

	@Autowired
	private ProductPriceService productPriceService;

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
		@PathVariable(name = "displayInfoId") int displayInfoId) {

		// TODO: product EmptyResultDataAccessException 예외처리
		Product product = productService.getProductByDisplayInfoId(displayInfoId);
		List<ProductImage> productImages = getProductImagesByDisplayInfoId(displayInfoId);
		List<DisplayInfoImage> displayInfoImages = displayInfoImageService.getDisplayInfoImagesByDisplayInfoId(displayInfoId);
		List<ProductPrice> productPrices = productPriceService.getProductPricesByDisplayInfoId(displayInfoId);
		// TODO comments, avgScore
		List<Object> comments = Collections.emptyList();
		double avgScore = 0.0;

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
			return productService.getProductsByCategoryId(start, categoryId);
		} else {
			return productService.getProducts(start);
		}
	}

	private int getProductsCountByCategoryId(int categoryId) {
		if (categoryId != ALL_CATEGORY) {
			return productService.getProductsCountByCategoryId(categoryId);
		} else {
			return productService.getProductsCount();
		}
	}
	
	private List<ProductImage> getProductImagesByDisplayInfoId(int displayInfoId){
		List<ProductImage> productImages = new LinkedList<>();
		ProductImage productMainImage = productImageService.getProductImageByDisplayInfoIdAndType(displayInfoId, "ma");
		List<ProductImage> productThumImages = productImageService.getProductImagesByDisplayInfoIdAndType(displayInfoId, "et");
		productImages.add(productMainImage);
		productImages.addAll(productThumImages);
		
		return productImages;
	}

}
