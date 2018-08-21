package com.nts.pjt3.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping(value = {"/", "/mainpage"})
	public String main() {
		return "mainpage";
	}

	@GetMapping(path = "/bookinglogin")
	public String bookinglogin() {
		return "bookinglogin";
	}

}
