package com.nts.pjt3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.pjt3.dto.Product;
import com.nts.pjt3.service.ProductService;

@Controller
public class MainController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = {"/", "/mainpage"})
	public String main() {
		return "mainpage";
	}

	@GetMapping(path = "/detail")
	public String detail(@RequestParam(name = "id") int displayInfoId, ModelMap model) {

		// TODO: product EmptyResultDataAccessException 예외처리
		Product product = productService.getProductByDisplayInfoId(displayInfoId);

		model.put("product", product);
		model.put("displayInfoId", displayInfoId);

		return "detail";
	}

	@GetMapping(path = "/review")
	public String review(@RequestParam(name = "id") int displayInfoId, ModelMap model) {
		
		Product product = productService.getProductByDisplayInfoId(displayInfoId);
		
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
	public String reserve() {
		return "reserve";
	}


	@GetMapping(path = "/reviweWrite")
	public String reviweWrite() {
		return "reviweWrite";
	}
}
