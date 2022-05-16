package com.tuyano.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class HeloController {

	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index2");
		return mav;
	}
	@RequestMapping("/other1")
	public ModelAndView other() {
		 return new ModelAndView("redirect:/");
	}

	@RequestMapping("/home1")
	public ModelAndView home() {
		return new ModelAndView("forward:/");
	}

}