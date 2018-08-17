package com.nts.pjt3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.pjt3.dto.Product;
import com.nts.pjt3.dto.ProductImage;
import com.nts.pjt3.dto.ProductPrice;
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
	private ProductPriceService productPriceService;

	@GetMapping(value = {"/", "/mainpage"})
	public String main() {
		return "mainpage";
	}

	@GetMapping(path = "/detail")
	public String detail(@RequestParam(name = "id") int displayInfoId, ModelMap model) {
		// TODO: product EmptyResultDataAccessException 예외처리
		Product product = productService.getByDisplayInfoId(displayInfoId);

		model.put("product", product);
		model.put("displayInfoId", displayInfoId);

		return "detail";
	}

	@GetMapping(path = "/review")
	public String review(@RequestParam(name = "id") int displayInfoId, ModelMap model) {
		Product product = productService.getByDisplayInfoId(displayInfoId);

		model.put("product", product);

		return "review";
	}

	@GetMapping(path = "/bookinglogin")
	public String bookinglogin() {
		return "bookinglogin";
	}

	@GetMapping(path = "/myreservation")
	public String myreservation() {
		return "myreservation";
	}

	@GetMapping(path = "/reserve")
	public String reserve(@RequestParam(name = "id") int displayInfoId, ModelMap model) {
		Product product = productService.getByDisplayInfoId(displayInfoId);
		ProductImage productImage = productImageService.getByDisplayInfoIdAndType(displayInfoId, "ma");
		List<ProductPrice> productPrices = productPriceService.getAllByDisplayInfoId(displayInfoId);

		model.put("product", product);
		model.put("productImage", productImage);
		model.put("productPrices", productPrices);

		return "reserve";
	}

	@GetMapping(path = "/reviweWrite")
	public String reviweWrite() {
		return "reviweWrite";
	}
}
