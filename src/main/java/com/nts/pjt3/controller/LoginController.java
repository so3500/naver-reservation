package com.nts.pjt3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String bookinglogin() {
		return "bookinglogin";
	}

	@PostMapping("/login")
	public String loginByEmail(@RequestParam("reservationEmail") String reservEmail, HttpSession session) {
		// TODO email validation check, error page
		session.setAttribute("loginEmail", reservEmail);
		return "redirect:/myreservation?reservationEmail=" + reservEmail ;
	}
}
