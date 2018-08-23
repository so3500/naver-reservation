package com.nts.pjt3.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

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
public class ReservationController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImageService productImageService;

	@Autowired
	private ProductPriceService productPriceService;
	
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.dd");
	
	@GetMapping(path = "/reserve")
	public String reserve(@RequestParam(name = "id") int displayInfoId, ModelMap model) {
		Product product = productService.getByDisplayInfoId(displayInfoId);
		ProductImage productImage = productImageService.getByDisplayInfoIdAndType(displayInfoId, "ma");
		List<ProductPrice> productPrices = productPriceService.getAllByDisplayInfoId(displayInfoId);

		model.put("product", product);
		model.put("productImage", productImage);
		model.put("productPrices", productPrices);
		model.put("reservationDate", getReservDate());
		model.put("displayInfoId", displayInfoId);
		
		return "reserve";
	}
	
	@GetMapping(path = "/myreservation")
	public String myreservation() {
		return "myreservation";
	}
	
	private String getReservDate() {
		Random random = new Random();
		String localDate = dateTimeFormatter.format(LocalDate.now().plusDays(random.nextInt(4)+1));
		return localDate;
	}
}
