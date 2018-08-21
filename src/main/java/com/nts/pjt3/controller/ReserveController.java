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
public class ReserveController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImageService productImageService;

	@Autowired
	private ProductPriceService productPriceService;
	
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
	
	@GetMapping(path = "/myreservation")
	public String myreservation() {
		return "myreservation";
	}
}
