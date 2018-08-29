package com.nts.pjt3.reservation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.pjt3.product.dto.Product;
import com.nts.pjt3.product.dto.ProductImage;
import com.nts.pjt3.product.dto.ProductPrice;
import com.nts.pjt3.product.service.ProductImageService;
import com.nts.pjt3.product.service.ProductPriceService;
import com.nts.pjt3.product.service.ProductService;
import com.nts.pjt3.reservation.dto.ReservationInfo;
import com.nts.pjt3.reservation.service.ReservationInfoService;

@Controller
public class ReservationController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImageService productImageService;

	@Autowired
	private ProductPriceService productPriceService;

	@Autowired
	private ReservationInfoService reservationInfoService;

	@GetMapping("/reserve")
	public String reserve(@RequestParam("id") int displayInfoId, ModelMap model) {
		Product product = productService.getByDisplayInfoId(displayInfoId);
		ProductImage productImage = productImageService.getByDisplayInfoIdAndType(displayInfoId, "ma");
		List<ProductPrice> productPrices = productPriceService.getAllByDisplayInfoId(displayInfoId);

		model.put("product", product);
		model.put("productImage", productImage);
		model.put("productPrices", productPrices);
		model.put("reservationDate", reservationInfoService.getReservDate());
		model.put("displayInfoId", displayInfoId);

		return "reserve";
	}

	@GetMapping("/myreservation")
	public String myreservation(@RequestParam("reservationEmail") String reservEmail, ModelMap model) {
		model.put("reservationEmail", reservEmail);

		List<ReservationInfo> reservInfos = reservationInfoService.getAllByReservationEmail(reservEmail);
		model.put("reservInfosCount", reservInfos.size());
		
		Map<String, Object>  classifiedReservInfoMap = reservationInfoService.getClassifiedReservInfos(reservInfos);
		model.put("confirmedReservInfos", classifiedReservInfoMap.get("confirmedReservInfos"));
		model.put("usedReservInfos", classifiedReservInfoMap.get("usedReservInfos"));
		model.put("canceldReservInfos", classifiedReservInfoMap.get("canceldReservInfos"));

		return "myreservation";
	}

}
