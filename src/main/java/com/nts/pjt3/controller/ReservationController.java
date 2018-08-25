package com.nts.pjt3.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
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
import com.nts.pjt3.dto.ReservationInfo;
import com.nts.pjt3.service.ProductImageService;
import com.nts.pjt3.service.ProductPriceService;
import com.nts.pjt3.service.ProductService;
import com.nts.pjt3.service.ReservationInfoService;

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

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.dd");

	@GetMapping(path = "/reserve")
	public String reserve(@RequestParam("id") int displayInfoId, ModelMap model) {
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
	public String myreservation(@RequestParam("reservationEmail") String reservEmail, ModelMap model) {
		model.put("reservationEmail", reservEmail);

		List<ReservationInfo> reservInfos = reservationInfoService.getAllByReservationEmail(reservEmail);
		model.put("reservInfosCount", reservInfos.size());
		classifyReservInfos(reservInfos, model);

		return "myreservation";
	}

	private String getReservDate() {
		Random random = new Random();
		String localDate = dateTimeFormatter.format(LocalDate.now().plusDays(random.nextInt(4) + 1));
		return localDate;
	}

	private void classifyReservInfos(List<ReservationInfo> reservInfos, ModelMap model) {
		List<ReservationInfo> confirmedReservInfos = new LinkedList<>();
		List<ReservationInfo> usedReservInfos = new LinkedList<>();
		List<ReservationInfo> canceldReservInfos = new LinkedList<>();
		final int CANCEL = 1;
		LocalDate nowDate = LocalDate.now();

		for (ReservationInfo reservInfo : reservInfos) {
			if (reservInfo.getCancelFlag() == CANCEL) {
				canceldReservInfos.add(reservInfo);
				continue;
			}

			LocalDate reserveDate = reservInfo.getReservationDate();
			if (reserveDate.isBefore(nowDate)) {
				usedReservInfos.add(reservInfo);
			} else {
				confirmedReservInfos.add(reservInfo);
			}
		}

		model.put("confirmedReservInfos", confirmedReservInfos);
		model.put("usedReservInfos", usedReservInfos);
		model.put("canceldReservInfos", canceldReservInfos);
	}

}
