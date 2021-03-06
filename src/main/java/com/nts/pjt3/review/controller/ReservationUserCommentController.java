package com.nts.pjt3.review.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.pjt3.product.dto.Product;
import com.nts.pjt3.product.service.ProductService;

@Controller
public class ReservationUserCommentController {

	@Autowired
	private ProductService productService;

	@GetMapping("/review")
	public String review(@RequestParam("id") int displayInfoId, ModelMap model) {
		Product product = productService.getByDisplayInfoId(displayInfoId);

		model.put("product", product);

		return "review";
	}

	@GetMapping("/reviewWrite")
	public String reviweWrite(@RequestParam("displayInfoId") int displayInfoId,
		@RequestParam("reservInfoId") int reservInfoId,
		ModelMap model, HttpSession session) {
		Product product = productService.getByDisplayInfoId(displayInfoId);
		
		model.put("productId", product.getId());
		model.put("displayInfoId", displayInfoId);
		model.put("description", product.getDescription());
		model.put("reservInfoId", reservInfoId);
		model.put("loginEmail", session.getAttribute("loginEmail"));

		return "reviewWrite";
	}

}
