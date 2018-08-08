package com.nts.pjt3.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.pjt3.dto.DisplayInfoImage;
import com.nts.pjt3.dto.Product;
import com.nts.pjt3.dto.ProductImage;
import com.nts.pjt3.dto.ProductPrice;
import com.nts.pjt3.service.DisplayInfoImageService;
import com.nts.pjt3.service.ProductImageService;
import com.nts.pjt3.service.ProductPriceService;
import com.nts.pjt3.service.ProductService;

@Controller
public class MainController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImageService productImageService;

	@Autowired
	private DisplayInfoImageService displayInfoImageService;

	@Autowired
	private ProductPriceService productPriceService;

	@GetMapping(value = {"/", "/mainpage"})
	public String main() {
		return "mainpage";
	}

	@GetMapping(path = "/detail")
	public String detail(@RequestParam(name = "id") int displayInfoId, ModelMap model) {

		// TODO: product EmptyResultDataAccessException 예외처리
		Product product = productService.getProductByDisplayInfoId(displayInfoId);
		List<ProductImage> productImages = productImageService.getProductImagesByDisplayInfoId(displayInfoId);
		List<DisplayInfoImage> displayInfoImages = displayInfoImageService
			.getDisplayInfoImagesByDisplayInfoId(displayInfoId);
		List<ProductPrice> productPrices = productPriceService.getProductPricesByDisplayInfoId(displayInfoId);
		// TODO comments, avgScore
		List<Object> comments = Collections.emptyList();
		double avgScore = 0.0;

		model.addAttribute("product", product);
		model.addAttribute("productImages", productImages);
		model.addAttribute("displayInfoImages", displayInfoImages);
		model.addAttribute("productPrices", productPrices);
		model.addAttribute("comments", comments);
		model.addAttribute("avgScore", avgScore);

		return "detail";
	}

	@GetMapping(path = "bookinglogin")
	public String bookinglogin() {
		return "bookinglogin";
	}

	@GetMapping(path = "myreservation")
	public String myreservation() {
		return "myreservation";
	}

	@GetMapping(path = "reserve")
	public String reserve() {
		return "reserve";
	}

	@GetMapping(path = "review")
	public String review() {
		return "review";
	}

	@GetMapping(path = "reviweWrite")
	public String reviweWrite() {
		return "reviweWrite";
	}
}
