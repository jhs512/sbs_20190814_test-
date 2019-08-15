package com.sbs.cuni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	@RequestMapping("/")
	public String showMain() {
		return "home/main";
	}

	@RequestMapping("/home/main")
	public String showMain2() {
		return "home/main";
	}
}